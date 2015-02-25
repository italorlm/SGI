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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import model.Contrato;
import model.ContratoAditivo;
import model.ContratoArquivo;
import model.ContratoFonteRecurso;
import model.ContratoParcela;
import model.FonteRecurso;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.ContratoAditivoDao;
import dao.ContratoArquivoDao;
import dao.ContratoDao;
import dao.ContratoFonteRecursoDao;
import dao.ContratoParcelaDao;

@Component
@Scope("globalSession")
public class ContratoController extends GenericController<Contrato, ContratoDao> {
//	private String pastaUpload = "C:/Apache/apache-tomcat-6.0.41/uploads/sgi/contrato"; //HomeLocal
//	private String pastaUpload = "C:/apache-tomcat-6.0/uploads/sgi/contrato"; //JobLocal
	private String pastaUpload = "E:/Tomcat 6.0/uploads/sgi/contrato"; //Proteus
	
	List<SelectItem> selectItems;
	List<ContratoParcela> parcelasExcluidas, parcelas;	
	List<ContratoArquivo> arquivosExcluidos, arquivos;
	List<ContratoAditivo> aditivosExcluidos, aditivos;
	List<ContratoFonteRecurso> fonteRecursosExcluidos, fonteRecursos;
	List<UploadItem> uploadItems;
	
	private boolean autoUpload = false;
	private int uploadsAvailable = 10;	
	private int statusArquivo = 1;
		
	boolean mostrarModalParcela;
	boolean mostrarModalFonteRecurso;
	
	ContratoParcela contratoParcela;
	ContratoArquivo contratoArquivo;
	ContratoFonteRecurso contratoFonteRecurso;
	ContratoAditivo contratoAditivo;
	
	FonteRecurso fonteRecursoTemp;
	
	@Resource
	ContratoParcelaDao contratoParcelaDao;
	
	@Resource
	ContratoArquivoDao contratoArquivoDao;
	
	@Resource
	ContratoFonteRecursoDao contratoFonteRecursoDao;
	
	@Resource
	ContratoAditivoDao contratoAditivoDao;
	
	final static String DAO_CONCRETO = "contratoDaoImp";

	public ContratoController(){
		injetaDao();		
		filtro = new Contrato();
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		parcelas = new ArrayList<ContratoParcela>();
		parcelasExcluidas = new ArrayList<ContratoParcela>();
		arquivos = new ArrayList<ContratoArquivo>();
		arquivosExcluidos = new ArrayList<ContratoArquivo>();
		fonteRecursos = new ArrayList<ContratoFonteRecurso>();
		fonteRecursosExcluidos = new ArrayList<ContratoFonteRecurso>();
		aditivos = new ArrayList<ContratoAditivo>();
		aditivosExcluidos = new ArrayList<ContratoAditivo>();
		uploadItems = new ArrayList<UploadItem>();
		contratoAditivo = new ContratoAditivo();
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarContrato";
		CADASTRO = "cadastrarContrato";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (ContratoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByContrato(filtro);
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Contrato c : getListagem()){
				selectItems.add(new SelectItem(c,c.getNumero()));
			}
		}
		return selectItems;
	}
	
	@Override
	public String salvar() {
		String retorno = null;
		try {
			retorno = super.salvar();
			
			if(!parcelas.isEmpty()) {
				for(ContratoParcela contratoParcela : parcelas) {
					if(objeto.getId()!=null) {
						contratoParcela.setContrato(objeto);
						if(contratoParcela.isEditado()==true)
							contratoParcelaDao.salvarOuAtualizar(contratoParcela);
					}				
				}
			}
			
			if(!parcelasExcluidas.isEmpty()) {
				for(ContratoParcela contratoParcela : parcelasExcluidas) {
					if(contratoParcela.getId()!=null) {
						contratoParcelaDao.excluir(contratoParcela);
					}
				}
			}
			
			if(!fonteRecursos.isEmpty()) {
				for(ContratoFonteRecurso contratoFonteRecurso : fonteRecursos) {
					if(objeto.getId()!=null) {
						contratoFonteRecurso.setContrato(objeto);
						if(contratoFonteRecurso.isEditado()==true)
							contratoFonteRecursoDao.salvarOuAtualizar(contratoFonteRecurso);
					}				
				}
			}
			
			if(!fonteRecursosExcluidos.isEmpty()) {
				for(ContratoFonteRecurso contratoFonteRecurso : fonteRecursosExcluidos) {
					if(contratoFonteRecurso.getId()!=null) {
						contratoFonteRecursoDao.excluir(contratoFonteRecurso);
					}
				}
			}
			
			if(!aditivos.isEmpty()) {
				for(ContratoAditivo contratoAditivo : aditivos) {
					contratoAditivo.setContrato(objeto);
					if(contratoAditivo.isEditado()==true)
						contratoAditivoDao.salvarOuAtualizar(contratoAditivo);									
				}
			}
			
			if(!aditivosExcluidos.isEmpty()) {
				for(ContratoAditivo contratoAditivo : aditivosExcluidos) {
					if(contratoAditivo.getId()!=null) {
						contratoAditivoDao.excluir(contratoAditivo);
					}
				}
			}
			
			if(!uploadItems.isEmpty()) {
				for(UploadItem item : uploadItems) {					
					String fileName = item.getFileName();
					OutputStream out = new FileOutputStream(pastaUpload + "/" + fileName);
					out.write(item.getData());
					out.close();					
			        
					contratoArquivo = new ContratoArquivo();
					contratoArquivo.setContrato(objeto);
					contratoArquivo.setArquivo(item.getFileName());
					contratoArquivoDao.salvarOuAtualizar(contratoArquivo);
				}
			}
			
			if(!arquivosExcluidos.isEmpty()) {
				for(ContratoArquivo contratoArquivo : arquivosExcluidos) {
					if(contratoArquivo.getId()!=null) {
						File file = new File(pastaUpload + "/" + contratoArquivo.getArquivo());
						file.delete();						
						contratoArquivoDao.excluir(contratoArquivo);
					}
				}
			}
			
			limpar();
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		return retorno;
	}
	
	@Override
	public String editar() {
		parcelas = contratoParcelaDao.findByContrato(objeto);
		arquivos = contratoArquivoDao.findByContrato(objeto);
		fonteRecursos = contratoFonteRecursoDao.findByContrato(objeto);
		aditivos = contratoAditivoDao.findByContrato(objeto);
		return super.editar();
	}
	
	public void mostrarModalParcela(ActionEvent event) {
		contratoParcela = new ContratoParcela();		
		mostrarModalParcela = true;
	}
	
	public void mostrarModalFonteRecurso(ActionEvent event) {
		contratoFonteRecurso = new ContratoFonteRecurso();
		mostrarModalFonteRecurso = true;
	}
	
	public void adicionarParcela() {
		String msgErro = null;		
		if(objeto.getParcelas()!=null) {
			if(contratoParcela.getId()!=null)
				parcelas.remove(contratoParcela);
			if(parcelas.size() < objeto.getParcelas() && !parcelas.contains(contratoParcela)) {
				contratoParcela.editado();
				parcelas.add(contratoParcela);
				mostrarModalParcela = false;
			} else if(parcelas.size() > objeto.getParcelas()){
				msgErro = "Quantidade máxima de parcelas já cadastrada. "
						+ "Aumente a quantidade de parcelas caso for necessário.";
			}
		} else {
			msgErro = "Informe a quantidade de parcelas.";
		}
		
		if(msgErro!=null) {					
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
		    mostrarModalParcela = false;
		}				
	}
	
	public void excluirParcela() {
		if(!parcelas.isEmpty()) {
			for(Iterator<ContratoParcela> i = parcelas.iterator(); i.hasNext();) {
				if(i.next().equals(contratoParcela))
					i.remove();
			}
		}
		
		parcelasExcluidas.add(contratoParcela);
	}
	
	public void adicionarFonteRecurso() {
		String msgErro = "Selecione uma Fonte de Recurso ao Adicionar.";
		if(fonteRecursoTemp!=null) {
			contratoFonteRecurso.editado();
			contratoFonteRecurso.setFonteRecurso(fonteRecursoTemp);
			if(contratoFonteRecurso.getId()!=null)
				fonteRecursos.remove(contratoFonteRecurso);
			if(!fonteRecursos.contains(contratoFonteRecurso))
				fonteRecursos.add(contratoFonteRecurso);
			mostrarModalFonteRecurso = false;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
	        mostrarModalParcela = false;
		}				
	}
	
	public void excluirFonteRecurso() {
		if(!fonteRecursos.isEmpty()) {
			for(Iterator<ContratoFonteRecurso> i = fonteRecursos.iterator(); i.hasNext();) {
				if(i.next().equals(contratoFonteRecurso))
					i.remove();
			}
		}
		
		fonteRecursosExcluidos.add(contratoFonteRecurso);
	}
	
	public void adicionarAditivo() {		
		if(contratoAditivo!=null) {
			contratoAditivo.editado();
			if(contratoAditivo.getId()!=null)
				aditivos.remove(contratoAditivo);
			if(!aditivos.contains(contratoAditivo))
				aditivos.add(contratoAditivo);			
		} 		
		contratoAditivo = new ContratoAditivo();
	}
	
	public void excluirAditivo() {
		if(!aditivos.isEmpty()) {
			for(Iterator<ContratoAditivo> i = aditivos.iterator(); i.hasNext();) {
				if(i.next().equals(contratoAditivo))
					i.remove();
			}
		}
		
		aditivosExcluidos.add(contratoAditivo);
	}
	
	public void excluirArquivo() {
		if(!arquivos.isEmpty()) {
			for(Iterator<ContratoArquivo> i = arquivos.iterator(); i.hasNext();) {
				if(i.next().equals(contratoArquivo))
					i.remove();
			}
		}
		
		arquivosExcluidos.add(contratoArquivo);
	}
	
	public void validaQtdParcela() {
		if(objeto.getParcelas() < parcelas.size())
			objeto.setParcelas(parcelas.size());		
	}
	
	public void limparAditivo() {
		contratoAditivo = new ContratoAditivo();
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Contrato contrato : getListagem()) {
			if(contrato.getExecutor().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(contrato))
					suggestions.add(contrato);			
		}
	}
	
	public void listener(UploadEvent event) throws Exception{
		statusArquivo = 0; //status de erro na transferencia do arquivo;
        UploadItem item = event.getUploadItem();
		ContratoArquivo verificaArquivo = contratoArquivoDao.findByNomeArquivo(item.getFileName());
		if (verificaArquivo == null) {
			uploadItems.add(item);
			uploadsAvailable--;
		} else {
			statusArquivo = 1; //status de arquivo já existente no diretorio.			
			throw new Exception();
		}

    }
	
	public void downloadArquivo() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) context.getContext();
		// Obtem o caminho para o arquivo e efetua a leitura
		byte[] arquivo = readFile(new File(pastaUpload + "/" + contratoArquivo.getArquivo()));
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		// configura o arquivo que vai voltar para o usuario.
		response.setHeader("Content-Disposition", 
				"attachment;filename=\"" + contratoArquivo.getArquivo() + "\"");
		response.setContentLength(arquivo.length);
		// isso faz abrir a janela de download
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
	
	public String retornaTipoAditivo(String string) {	
		switch (Integer.parseInt(string)) {
		case 1:				
			return "Recurso";
		case 2:				
			return "Tempo";
		default:
			return null;		
		}
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	public List<ContratoParcela> getParcelasExcluidas() {
		return parcelasExcluidas;
	}

	public void setParcelasExcluidas(List<ContratoParcela> parcelasExcluidas) {
		this.parcelasExcluidas = parcelasExcluidas;
	}

	public List<ContratoParcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ContratoParcela> parcelas) {
		this.parcelas = parcelas;
	}

	public ContratoParcela getContratoParcela() {
		return contratoParcela;
	}

	public void setContratoParcela(ContratoParcela contratoParcela) {
		this.contratoParcela = contratoParcela;
	}

	public boolean isMostrarModalParcela() {
		return mostrarModalParcela;
	}

	public void setMostrarModalParcela(boolean mostrarModalParcela) {
		this.mostrarModalParcela = mostrarModalParcela;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public List<ContratoArquivo> getArquivosExcluidos() {
		return arquivosExcluidos;
	}

	public void setArquivosExcluidos(List<ContratoArquivo> arquivosExcluidos) {
		this.arquivosExcluidos = arquivosExcluidos;
	}

	public List<ContratoArquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ContratoArquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public List<UploadItem> getUploadItems() {
		return uploadItems;
	}

	public void setUploadItems(List<UploadItem> uploadItems) {
		this.uploadItems = uploadItems;
	}

	public ContratoArquivo getContratoArquivo() {
		return contratoArquivo;
	}

	public void setContratoArquivo(ContratoArquivo contratoArquivo) {
		this.contratoArquivo = contratoArquivo;
	}

	public String getTransferError() {
		if(statusArquivo == 0) 
			return "Erro ao Transferir o arquivo.";
		else
			return "Já existe um arquivo com este nome. Altere o nome.";		 
	}

	public int getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(int statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public String getPastaUpload() {
		return pastaUpload;
	}

	public boolean isMostrarModalFonteRecurso() {
		return mostrarModalFonteRecurso;
	}

	public void setMostrarModalFonteRecurso(boolean mostrarModalFonteRecurso) {
		this.mostrarModalFonteRecurso = mostrarModalFonteRecurso;
	}

	public ContratoFonteRecurso getContratoFonteRecurso() {
		return contratoFonteRecurso;
	}

	public void setContratoFonteRecurso(ContratoFonteRecurso contratoFonteRecurso) {
		this.contratoFonteRecurso = contratoFonteRecurso;
	}

	public List<ContratoFonteRecurso> getFonteRecursosExcluidos() {
		return fonteRecursosExcluidos;
	}

	public void setFonteRecursosExcluidos(
			List<ContratoFonteRecurso> fonteRecursosExcluidos) {
		this.fonteRecursosExcluidos = fonteRecursosExcluidos;
	}

	public List<ContratoFonteRecurso> getFonteRecursos() {
		return fonteRecursos;
	}

	public void setFonteRecursos(List<ContratoFonteRecurso> fonteRecursos) {
		this.fonteRecursos = fonteRecursos;
	}

	public FonteRecurso getFonteRecursoTemp() {
		return fonteRecursoTemp;
	}

	public void setFonteRecursoTemp(FonteRecurso fonteRecursoTemp) {
		this.fonteRecursoTemp = fonteRecursoTemp;
	}

	public ContratoAditivo getContratoAditivo() {
		return contratoAditivo;
	}

	public void setContratoAditivo(ContratoAditivo contratoAditivo) {
		this.contratoAditivo = contratoAditivo;
	}

	public List<ContratoAditivo> getAditivosExcluidos() {
		return aditivosExcluidos;
	}

	public void setAditivosExcluidos(List<ContratoAditivo> aditivosExcluidos) {
		this.aditivosExcluidos = aditivosExcluidos;
	}

	public List<ContratoAditivo> getAditivos() {
		return aditivos;
	}

	public void setAditivos(List<ContratoAditivo> aditivos) {
		this.aditivos = aditivos;
	}	
	
}

