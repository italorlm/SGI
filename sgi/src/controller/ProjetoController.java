package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import model.Cargo;
import model.Cidadao;
import model.Contrato;
import model.ContratoArquivo;
import model.Programa;
import model.ProgramaMap;
import model.Projeto;
import model.ProjetoArquivo;
import model.ProjetoMap;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ContratoDao;
import dao.ProgramaDao;
import dao.ProgramaMapDao;
import dao.ProjetoArquivoDao;
import dao.ProjetoDao;
import dao.ProjetoMapDao;

@Component
@Scope("globalSession")
public class ProjetoController extends GenericController<Projeto, ProjetoDao> {
	// private String pastaUpload = "C:/Apache/apache-tomcat-6.0.41/uploads/sgi/projeto"; //HomeLocal
	// private String pastaUpload = "C:/apache-tomcat-6.0/uploads/sgi/projeto"; //JobLocal
	private String pastaUpload = "E:/Tomcat 6.0/uploads/sgi/projeto"; // Proteus

	List<SelectItem> selectItems;
	List<UploadItem> uploadItems;
	List<ProjetoArquivo> arquivosExcluidos, arquivos;
	List<ProgramaMap> programaMaps;

	private boolean autoUpload = false;
	private int uploadsAvailable = 10;
	private int statusArquivo = 1;

	ProjetoArquivo projetoArquivo;
	ProjetoMap projetoMap;

	@Resource
	ProjetoArquivoDao projetoArquivoDao;

	@Resource
	ProgramaMapDao programaMapDao;

	@Resource
	ProjetoMapDao projetoMapDao;

	final static String DAO_CONCRETO = "projetoDaoImp";

	public ProjetoController() {
		injetaDao();
		filtro = new Projeto();
	}

	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		arquivosExcluidos = new ArrayList<ProjetoArquivo>();
		arquivos = new ArrayList<ProjetoArquivo>();
		uploadItems = new ArrayList<UploadItem>();
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarProjeto";
		CADASTRO = "cadastrarProjeto";
	}

	@Override
	public void injetaDao() {
		try {
			super.dao = (ProjetoDao) getApplicationContext().getBean(
					DAO_CONCRETO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByProjeto(filtro);
	}

	public void buscarMap() {
		programaMaps = programaMapDao.findByPrograma(objeto.getPrograma());
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if (selectItems.size() == 0) {
			for (Projeto p : getListagem()) {
				selectItems.add(new SelectItem(p, p.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public String salvar() {
		List<ProjetoMap> projetoMaps = new ArrayList<ProjetoMap>();
		String retorno = null;
		try {
			retorno = super.salvar();
			
			if(!uploadItems.isEmpty()) {
				for(UploadItem item : uploadItems) {					
					String fileName = item.getFileName();
					OutputStream out = new FileOutputStream(pastaUpload + "/" + fileName);
					out.write(item.getData());
					out.close();					
			        
					projetoArquivo = new ProjetoArquivo();
					projetoArquivo.setProjeto(objeto);
					projetoArquivo.setArquivo(item.getFileName());
					projetoArquivoDao.salvarOuAtualizar(projetoArquivo);
				}
			}
			
			if(!arquivosExcluidos.isEmpty()) {
				for(ProjetoArquivo projetoArquivo : arquivosExcluidos) {
					if(projetoArquivo.getId()!=null) {
						File file = new File(pastaUpload + "/" + projetoArquivo.getArquivo());
						file.delete();						
						projetoArquivoDao.excluir(projetoArquivo);
					}
				}
			}
			
			if(!programaMaps.isEmpty()) {				
				for(ProgramaMap programaMap : programaMaps) {
					ProjetoMap projetoMap = new ProjetoMap();
					projetoMap.setProjeto(objeto);
					projetoMap.setProgramaMap(programaMap);
					
					if(programaMap.isCheck() && 
							projetoMapDao.findByExample(projetoMap)==null) {						
						projetoMapDao.salvarOuAtualizar(projetoMap);						
					} else if(!programaMap.isCheck()) { 
						projetoMap = projetoMapDao.findByExample(projetoMap);
						if(projetoMap!=null && projetoMap.getId()!=null)
							projetoMapDao.excluir(projetoMap);
					}
				}
			}
				
			uploadItems.clear();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		return retorno;
	}

	@Override
	public String editar() {
		arquivos = projetoArquivoDao.findByArquivo(objeto);
		programaMaps = programaMapDao.findByPrograma(objeto.getPrograma());
		
		for(ProgramaMap programaMap : programaMaps) {
			ProjetoMap projetoMap = new ProjetoMap();
			projetoMap.setProjeto(objeto);
			projetoMap.setProgramaMap(programaMap);
			
			if(projetoMapDao.findByExample(projetoMap)!=null)
				programaMap.setCheck(true);
		}
		
		return super.editar();
	}

	public void excluirArquivo() {
		if (!arquivos.isEmpty()) {
			for (Iterator<ProjetoArquivo> i = arquivos.iterator(); i.hasNext();) {
				if (i.next().equals(projetoArquivo))
					i.remove();
			}
		}
		arquivosExcluidos.add(projetoArquivo);
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for (Projeto projeto : getListagem()) {
			if (projeto.getNome().toLowerCase()
					.contains(userInput.toLowerCase()))
				if (!suggestions.contains(projeto))
					suggestions.add(projeto);
		}
	}

	public void downloadArquivo() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext servletContext = (ServletContext) context.getContext();
		// Obtem o caminho para o arquivo e efetua a leitura
		byte[] arquivo = readFile(new File(pastaUpload + "/"
				+ projetoArquivo.getArquivo()));
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		// configura o arquivo que vai voltar para o usuario.
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ projetoArquivo.getArquivo() + "\"");
		response.setContentLength(arquivo.length);
		// isso faz abrir a janelinha de download
		response.setContentType("application/download");
		// envia o arquivo de volta
		try {
			OutputStream out = response.getOutputStream();
			out.write(arquivo);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			System.out.print("Erro no envio do arquivo");
			e.printStackTrace();
		}
	}

	// efetua a leitura do arquivo
	public static byte[] readFile(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);

		} catch (FileNotFoundException e) {
			System.out.print("Arquivo não encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("Erro na leitura do arquivo");
			e.printStackTrace();
		}
		return sendBuf;
	}

	public void listener(UploadEvent event) throws Exception {
		statusArquivo = 0; // status de erro na transferencia do arquivo;
		UploadItem item = event.getUploadItem();
		ProjetoArquivo verificaArquivo = projetoArquivoDao
				.findByNomeArquivo(item.getFileName());
		if (verificaArquivo == null) {
			uploadItems.add(item);
			uploadsAvailable--;
		} else {
			statusArquivo = 1; // status de arquivo já existente no diretorio.
			throw new Exception();
		}
	}

	public List<ProjetoArquivo> getArquivosExcluidos() {
		return arquivosExcluidos;
	}

	public void setArquivosExcluidos(List<ProjetoArquivo> arquivosExcluidos) {
		this.arquivosExcluidos = arquivosExcluidos;
	}

	public List<ProjetoArquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ProjetoArquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public int getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(int statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public ProjetoArquivo getProjetoArquivo() {
		return projetoArquivo;
	}

	public void setProjetoArquivo(ProjetoArquivo projetoArquivo) {
		this.projetoArquivo = projetoArquivo;
	}

	public String getPastaUpload() {
		return pastaUpload;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public String getTransferError() {
		if (statusArquivo == 0)
			return "Erro ao Transferir o arquivo.";
		else
			return "Já existe um arquivo com este nome. Altere o nome.";
	}

	public List<UploadItem> getUploadItems() {
		return uploadItems;
	}

	public void setUploadItems(List<UploadItem> uploadItems) {
		this.uploadItems = uploadItems;
	}

	public List<ProgramaMap> getProgramaMaps() {
		return programaMaps;
	}

	public void setProgramaMaps(List<ProgramaMap> programaMaps) {
		this.programaMaps = programaMaps;
	}

	public ProjetoMap getProjetoMap() {
		return projetoMap;
	}

	public void setProjetoMap(ProjetoMap projetoMap) {
		this.projetoMap = projetoMap;
	}

}