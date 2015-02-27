package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Entidade;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.EntidadeDao;

@Component
@Scope("globalSession")
public class EntidadeController extends GenericController<Entidade, EntidadeDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "entidadeDaoImp";

	public EntidadeController(){
		injetaDao();		
		filtro = new Entidade();
		trazerTodos = true;
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarEntidade";
		CADASTRO = "cadastrarEntidade";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (EntidadeDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Entidade e : getListagem()){
				selectItems.add(new SelectItem(e,e.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Entidade entidade : getListagem()) {
			if(entidade.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(entidade))
					suggestions.add(entidade);
		}
	}
}

