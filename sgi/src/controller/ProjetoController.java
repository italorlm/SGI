package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.Contrato;
import model.Programa;
import model.Projeto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ContratoDao;
import dao.ProgramaDao;
import dao.ProjetoDao;

@Component
@Scope("globalSession")
public class ProjetoController extends GenericController<Projeto, ProjetoDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "projetoDaoImp";

	public ProjetoController(){
		injetaDao();		
		filtro = new Projeto();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarProjeto";
		CADASTRO = "cadastrarProjeto";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (ProjetoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByProjeto(filtro);
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Projeto p : getListagem()){
				selectItems.add(new SelectItem(p,p.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	
}

