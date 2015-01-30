package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.ProgramaGovernamental;
import model.Projeto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ProgramaGovernamentalDao;

@Component
@Scope("globalSession")
public class ProgramaGovernamentalController extends GenericController<ProgramaGovernamental, ProgramaGovernamentalDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "programaGovernamentalDaoImp";

	public ProgramaGovernamentalController(){
		injetaDao();		
		filtro = new ProgramaGovernamental();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarProgramaGovernamental";
		CADASTRO = "cadastrarProgramaGovernamental";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (ProgramaGovernamentalDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByProgramaGovernamental(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(ProgramaGovernamental p : getListagem()){
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
		for(ProgramaGovernamental programaGovernamental : getListagem()) {
			if(programaGovernamental.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(programaGovernamental))
					suggestions.add(programaGovernamental);
		}
	}
}

