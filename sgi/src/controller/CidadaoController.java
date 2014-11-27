package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import model.Cargo;
import model.Cidadao;
import model.Municipio;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.MessageUtil;
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
		listagem = dao.findByNome(filtro.getNome());
	}

	@Override
	public String salvar(){
		String msgErro = null;
		String retorno = null;
		
		if(objeto.getNome()==null || objeto.getNome()=="")
			msgErro = "O campo Nome é obrigatório!";
				
		if(msgErro=="" || msgErro==null) {
			retorno = super.salvar();
			return retorno;
		} else {			
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
			return null;
		}
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

}

