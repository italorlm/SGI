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

@Component
@Scope("globalSession")
public class UnidadeCptrController extends GenericController<UnidadeCptr,UnidadeCptrDao> {


	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "unidadeCptrDaoImp";

	public UnidadeCptrController(){
		injetaDao();
		filtro = new UnidadeCptr();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarUnidadeCptr";
		CADASTRO = "cadastrarUnidadeCptr";

	}
	
	public void filtrar(){
		trazerTodos = false;
		listagem = dao.findByExample(filtro);
		System.out.println("");
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (UnidadeCptrDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(UnidadeCptr u : getListagem()){
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
		for(UnidadeCptr unidadeCptr : getListagem()) {
			if(unidadeCptr.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(unidadeCptr))
					suggestions.add(unidadeCptr);
		}
	}
}

