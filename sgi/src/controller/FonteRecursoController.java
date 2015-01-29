package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.FonteRecurso;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.FonteRecursoDao;

@Component
@Scope("globalSession")
public class FonteRecursoController extends GenericController<FonteRecurso,FonteRecursoDao> {
	
	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "fonteRecursoDaoImp";
	
	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarFonteRecurso";
		CADASTRO = "cadastrarFonteRecurso";

	}
	
	public FonteRecursoController(){
		injetaDao();
		filtro = new FonteRecurso();
		trazerTodos = true;
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (FonteRecursoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(FonteRecurso f : getListagem()){
				selectItems.add(new SelectItem(f,f.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(FonteRecurso fonteRecurso : getListagem()) {
			if(fonteRecurso.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(fonteRecurso))
					suggestions.add(fonteRecurso);
		}
	}
}
