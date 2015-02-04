package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import model.Cidadao;
import model.Municipio;

import org.dom4j.Element;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Years;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CpfValidator;
import ws.CepWS;
import dao.CidadaoDao;

@Component
@Scope("globalSession")
public class CidadaoController extends GenericController<Cidadao, CidadaoDao> {


	List<SelectItem> selectItems, itemsMunicipios, itemsMunicipiosResidencial;
	final static String DAO_CONCRETO = "cidadaoDaoImp";
	
	private String cpfBackup;	
		
	@Resource
	MunicipioUfController municipioUfController;
	
	public CidadaoController(){
		injetaDao();
		filtro = new Cidadao();
	}
		
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {		
		try {
			itemsMunicipios = new ArrayList<SelectItem>();
			itemsMunicipiosResidencial = municipioUfController.getMunicipios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		cpfBackup = objeto.getCpf();
		return super.editar();	
	}
	
	public String validaInformacao(Cidadao cidadao) {
		if(cidadao.getNome()==null || cidadao.getNome().isEmpty())
			return "O campo Nome é obrigatório!";
		if(cidadao.getDataNascimento()==null)
			return "O campo Data de Nascimento é obrigatório!";
		if(cidadao.getCpf()==null || cidadao.getCpf().isEmpty())
			return "O campo CPF é obrigatório!";		
		if(!CpfValidator.validaCPF(cidadao.getCpf()))
			return "CPF Inválido!";
		if(cidadao.getId()==null) {
			if(dao.findByCpf(cidadao.getCpf())!=null)
				return "Cidadão já cadastrado! O CPF informado já existe na Base de Dados.";
		} else {
			if(!cidadao.getCpf().equals(cpfBackup))
				if(dao.findByCpf(cidadao.getCpf())!=null)
					return "Cidadão já cadastrado! O CPF informado já existe na Base de Dados.";
		}
		if(cidadao.getPispasep()==null || cidadao.getPispasep().isEmpty() && 
				Years.yearsBetween(new DateTime(cidadao.getDataNascimento().getTime()),
						new DateTime(new Date().getTime())).getYears() >= 18)
			return "O campo PIS/PASEP é obrigatório para maior de 18 anos!";			
		
		return null;
	}
	
	//Carrega dados via WebService (XML) - viacep.com.br
	public void carregarDadosEndereco() throws NumberFormatException, SQLException {
		try {
			String msgErro = "CEP Invalido!";
			objeto.setCep(objeto.getCep().replace("_", ""));
			if(objeto.getCep()!=null && objeto.getCep().length()==9) {
				for (Iterator i = CepWS.buscarCep(objeto.getCep()).elementIterator(); i.hasNext();) {
					List<Municipio> municipios = new ArrayList<Municipio>();
					Element element = (Element) i.next();
		            
		            if (!element.getQualifiedName().equals("erro")) {	
		            	objeto.setCepValido(true);
		            	
		            	if (element.getQualifiedName().equals("ibge")) 
		            		municipios = municipioUfController.buscarMunicipioPorCodigoIbge(element.getText());
		            	
		            	if(municipios.size()==1) {
		            		objeto.setUf(municipios.get(0).getIdEstado());
		            		buscarCidadesResidencial();
		            		objeto.setMunicipio(municipios.get(0).getId());	            		
		            	}
		            	
		                if (element.getQualifiedName().equals("bairro"))
		                    objeto.setBairro(element.getText());

		                if (element.getQualifiedName().equals("logradouro"))
		                    objeto.setEndereco(element.getText());
		            } else if(element.getQualifiedName().equals("erro")){
		            	itemsMunicipiosResidencial.clear();
		            	objeto.setCepValido(false);
		            	objeto.setUf(null);
		            	objeto.setMunicipio(null);
		            	objeto.setBairro("");
		            	objeto.setEndereco("");	            	
		            } 
		        }
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));				
			}
		} catch(Exception e) {
			itemsMunicipiosResidencial.clear();
        	objeto.setCepValido(false);
        	objeto.setUf(null);
        	objeto.setMunicipio(null);
        	objeto.setBairro("");
        	objeto.setEndereco("");
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
		List<Municipio> municipios = new ArrayList<Municipio>();		
		
		if(objeto.getUf()!=null)
			municipios = municipioUfController.buscarMunicipioPorEstado(objeto.getUf());
				
		if(municipios.size()!=0) {
			itemsMunicipiosResidencial.clear();	
			for(Municipio municipio : municipios) {
				itemsMunicipiosResidencial.add(new SelectItem(municipio, municipio.getNome().toUpperCase()));
			}
		} 
		
//		itemsMunicipiosResidencial.clear();		
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
		return itemsMunicipiosResidencial;
	}

	public void setItemsMunicipiosResidencial(List<SelectItem> itemsMunicipiosResidencial) {
		this.itemsMunicipiosResidencial = itemsMunicipiosResidencial;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Cidadao cidadao : getListagem()) {
			if(cidadao.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(cidadao))
					suggestions.add(cidadao);
		}
	}

	public String getCpfBackup() {
		return cpfBackup;
	}

	public void setCpfBackup(String cpfBackup) {
		this.cpfBackup = cpfBackup;
	}
}	


