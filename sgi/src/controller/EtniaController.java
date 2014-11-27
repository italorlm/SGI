package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Etnia;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.EtniaDao;

@Component
@Scope("globalSession")
public class EtniaController extends GenericController<Etnia, EtniaDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "etniaDaoImp";

	public EtniaController(){
		injetaDao();		
		filtro = new Etnia();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarEtnia";
		CADASTRO = "cadastrarEtnia";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (EtniaDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByEtnia(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Etnia e : getListagem()){
				selectItems.add(new SelectItem(e,e.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	
}

