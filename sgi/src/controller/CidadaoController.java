package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import model.Cidadao;
import model.Municipio;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CpfValidator;
import dao.CidadaoDao;

@Component
@Scope("globalSession")
public class CidadaoController extends GenericController<Cidadao, CidadaoDao> {


	List<SelectItem> selectItems, itemsMunicipios, itemsMunicipiosResidencial;
	final static String DAO_CONCRETO = "cidadaoDaoImp";
	
	@Resource
	MunicipioUfController municipioUfController;

	public CidadaoController(){
		injetaDao();
		filtro = new Cidadao();
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		itemsMunicipios = new ArrayList<SelectItem>();
		itemsMunicipiosResidencial = new ArrayList<SelectItem>();
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarCidadao";
		CADASTRO = "cadastrarCidadao";

	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (CidadaoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void filtrar(){
		trazerTodos = false;
		listagem = dao.findByExample(filtro);
	}

	@Override
	public String salvar(){
		String msgErro = null;		
		
		msgErro = validaInformacao(objeto);
				
		if(msgErro==null) {
			return super.salvar();			
		} else {			
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
			return null;
		}
	}
	
	@Override
	public String editar() {
		String msgErro = null;
		
		msgErro = validaInformacao(objeto);
				
		if(msgErro==null) {
			return super.editar();			
		} else {			
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
			return null;
		}
	}
	
	public String validaInformacao(Cidadao cidadao) {
		if(cidadao.getNome()==null || cidadao.getNome().isEmpty())
			return "O campo Nome é obrigatório!";		
		else if(!CpfValidator.validaCPF(cidadao.getCpf()))
			return "CPF Inválido!";
		
		return null;
	}
	
	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Cidadao c : getListagem()){
				selectItems.add(new SelectItem(c,c.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	public void buscarCidades() throws SQLException {
		itemsMunicipios.clear();		
	}
	
	public void buscarCidadesResidencial() throws SQLException {
		itemsMunicipiosResidencial.clear();		
	}

	public List<SelectItem> getItemsMunicipios() throws SQLException {
		List<Municipio> municipios = new ArrayList<Municipio>();
		
		if(objeto.getUfNascimento()!=null)
			municipios = municipioUfController.buscarMunicipioPorEstado(objeto.getUfNascimento());
		
		if(municipios.size()!=0) {
			for(Municipio municipio : municipios) {
				itemsMunicipios.add(new SelectItem(municipio, municipio.getNome().toUpperCase()));
			}
		}
		
		return itemsMunicipios;
	}

	public void setItemsMunicipios(List<SelectItem> itemsMunicipios) {
		this.itemsMunicipios = itemsMunicipios;
	}

	public List<SelectItem> getItemsMunicipiosResidencial() throws SQLException {
		List<Municipio> municipios = new ArrayList<Municipio>();
		
		if(objeto.getUf()!=null)
			municipios = municipioUfController.buscarMunicipioPorEstado(objeto.getUf());
		
		if(municipios.size()!=0) {
			for(Municipio municipio : municipios) {
				itemsMunicipiosResidencial.add(new SelectItem(municipio, municipio.getNome().toUpperCase()));
			}
		}
		
		return itemsMunicipiosResidencial;
	}

	public void setItemsMunicipiosResidencial(List<SelectItem> itemsMunicipiosResidencial) {
		this.itemsMunicipiosResidencial = itemsMunicipiosResidencial;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Cidadao cidadao : getListagem()) {
			if(cidadao.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(cidadao))
					suggestions.add(cidadao);
		}
	}	
}

