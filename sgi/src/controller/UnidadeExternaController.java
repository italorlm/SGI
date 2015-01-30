package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Programa;
import model.UnidadeCptr;
import model.UnidadeExterna;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.UnidadeCptrDao;
import dao.UnidadeExternaDao;

@Component
@Scope("globalSession")
public class UnidadeExternaController extends GenericController<UnidadeExterna,UnidadeExternaDao> {


	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "unidadeExternaDaoImp";

	public UnidadeExternaController(){
		injetaDao();
		filtro = new UnidadeExterna();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarUnidadeExterna";
		CADASTRO = "cadastrarUnidadeExterna";

	}
	
	public void filtrar(){
		trazerTodos = false;
		listagem = dao.findByExample(filtro);
		System.out.println("");
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (UnidadeExternaDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(UnidadeExterna u : getListagem()){
				selectItems.add(new SelectItem(u,u.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(UnidadeExterna unidadeExterna : getListagem()) {
			if(unidadeExterna.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(unidadeExterna))
					suggestions.add(unidadeExterna);
		}
	}
}

