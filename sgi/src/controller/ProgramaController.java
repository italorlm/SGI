package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.Cidadao;
import model.Contrato;
import model.MaterialPermanente;
import model.Programa;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ContratoDao;
import dao.ProgramaDao;

@Component
@Scope("globalSession")
public class ProgramaController extends GenericController<Programa, ProgramaDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "programaDaoImp";

	public ProgramaController(){
		injetaDao();		
		filtro = new Programa();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarPrograma";
		CADASTRO = "cadastrarPrograma";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (ProgramaDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByPrograma(filtro);
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Programa p : getListagem()){
				selectItems.add(new SelectItem(p,p.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Programa programa : getListagem()) {
			if(programa.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(programa))
					suggestions.add(programa);
		}
	}
}

