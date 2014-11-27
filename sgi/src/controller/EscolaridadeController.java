package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.Contrato;
import model.Escolaridade;
import model.Perfil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ContratoDao;
import dao.EscolaridadeDao;

@Component
@Scope("globalSession")
public class EscolaridadeController extends GenericController<Escolaridade, EscolaridadeDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "escolaridadeDaoImp";

	public EscolaridadeController(){
		injetaDao();		
		filtro = new Escolaridade();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarEscolaridade";
		CADASTRO = "cadastrarEscolaridade";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (EscolaridadeDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByEscolaridade(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Escolaridade e : getListagem()){
				selectItems.add(new SelectItem(e,e.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	
}

