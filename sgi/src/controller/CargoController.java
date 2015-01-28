package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;

@Component
@Scope("globalSession")
public class CargoController extends GenericController<Cargo, CargoDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "cargoDaoImp";

	public CargoController(){
		injetaDao();		
		filtro = new Cargo();
		trazerTodos = true;
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarCargo";
		CADASTRO = "cadastrarCargo";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (CargoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByCargo(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Cargo c : getListagem()){
				selectItems.add(new SelectItem(c,c.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Cargo cargo : getListagem()) {
			if(cargo.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(cargo))
					suggestions.add(cargo);
		}
	}
}

