package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import model.Contrato;
import model.ContratoParcela;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.ContratoDao;
import dao.ContratoParcelaDao;

@Component
@Scope("globalSession")
public class ContratoController extends GenericController<Contrato, ContratoDao> {

	List<SelectItem> selectItems;
	List<ContratoParcela> parcelasExcluidas, parcelas;
	
	boolean mostrarModalParcela;
	
	ContratoParcela contratoParcela;
	
	@Resource
	ContratoParcelaDao contratoParcelaDao;
	
	final static String DAO_CONCRETO = "contratoDaoImp";

	public ContratoController(){
		injetaDao();		
		filtro = new Contrato();
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		parcelas = new ArrayList<ContratoParcela>();
		parcelasExcluidas = new ArrayList<ContratoParcela>();
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
		
		return retorno;
	}
	
	@Override
	public String editar() {
		parcelas = contratoParcelaDao.findByParcela(objeto);
		return super.editar();
	}
	
	public void mostrarModalParcela(ActionEvent event) {
		contratoParcela = new ContratoParcela();
		mostrarModalParcela = true;
	}
	
	public void adicionarParcela() {
		String msgErro = null;
		if(contratoParcela.getId()==null) {
			if(objeto.getParcelas() == null) {
				msgErro = "Informe a quantidade de parcelas!";
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
				mostrarModalParcela = false;
			} else if(parcelas.size() < objeto.getParcelas()) {
				parcelas.add(contratoParcela);
				mostrarModalParcela = false;
			} else {
				msgErro = "Quantidade máxima de parcelas já cadastrada. Aumente a quantidade de parcelas caso seja necessário.";
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
		
}

