package controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import model.Contrato;
import model.ContratoArquivo;
import model.ContratoParcela;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.ContratoArquivoDao;
import dao.ContratoDao;
import dao.ContratoParcelaDao;

@Component
@Scope("globalSession")
public class ContratoController extends GenericController<Contrato, ContratoDao> {

	List<SelectItem> selectItems;
	List<ContratoParcela> parcelasExcluidas, parcelas;	
	List<ContratoArquivo> arquivosExcluidos, arquivos;
	List<UploadItem> uploadItems;
	
	private boolean autoUpload = false;
	private int uploadsAvailable = 10;
	
	boolean mostrarModalParcela;
	
	ContratoParcela contratoParcela;
	ContratoArquivo contratoArquivo;
	
	@Resource
	ContratoParcelaDao contratoParcelaDao;
	
	@Resource
	ContratoArquivoDao contratoArquivoDao;
	
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
		uploadItems = new ArrayList<UploadItem>();
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
				selectItems.add(new SelectItem(c,c.getContrato()));
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
			
			if(!uploadItems.isEmpty()) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ServletContext servletContext = (ServletContext) 
						facesContext.getExternalContext().getContext();
				String realTargetPath = servletContext.getRealPath("/");
				for(UploadItem item : uploadItems) {
					String filePathName = item.getFileName();
					String fileName = "";
					StringTokenizer st = new StringTokenizer(filePathName, "\\");
					while(st.hasMoreElements()) {
						fileName = st.nextToken();
					}
					realTargetPath = realTargetPath + "uploads\\" + fileName;
					OutputStream out = new FileOutputStream(realTargetPath);
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
						contratoArquivoDao.excluir(contratoArquivo);
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
		parcelas = contratoParcelaDao.findByParcela(objeto);
		arquivos = contratoArquivoDao.findByArquivo(objeto);
		return super.editar();
	}
	
	public void mostrarModalParcela(ActionEvent event) {
		contratoParcela = new ContratoParcela();
		mostrarModalParcela = true;
	}
	
	public void adicionarParcela() {
		String msgErro = "Quantidade máxima de parcelas já cadastrada. Aumente a quantidade de parcelas caso for necessário.";
		if(contratoParcela.getId()==null) {
			if(parcelas.size() < objeto.getParcelas()) {
				parcelas.add(contratoParcela);
				mostrarModalParcela = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
		        mostrarModalParcela = false;
			}
		}		
	}
	
	public void excluirParcela() {
		if(!parcelas.isEmpty()) {
			for(ContratoParcela contratoParcela : parcelas) {
				if(contratoParcela.equals(this.contratoParcela)) {
					parcelas.remove(contratoParcela);
				}
			}
		} 
		
		parcelasExcluidas.add(contratoParcela);
	}
	
	public void excluirArquivo() {
		if(!arquivos.isEmpty()) {
			for(ContratoArquivo contratoArquivo : arquivos) {
				if(contratoArquivo.equals(this.contratoArquivo)) {
					arquivos.remove(contratoArquivo);
				}
			}
		}
		
		arquivosExcluidos.add(contratoArquivo);
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Contrato contrato : getListagem()) {
			if(contrato.getExecutor().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(contrato))
					suggestions.add(contrato);
			if(contrato.getContrato().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(contrato))
					suggestions.add(contrato);
		}
	}
	
	public void listener(UploadEvent event) throws Exception{
        UploadItem item = event.getUploadItem();
        uploadItems.add(item);
        uploadsAvailable--;
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
	
	
}

