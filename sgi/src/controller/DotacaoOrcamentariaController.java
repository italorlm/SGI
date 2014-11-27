package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.DotacaoOrcamentaria;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.DotacaoOrcamentariaDao;

@Component
@Scope("globalSession")
public class DotacaoOrcamentariaController extends GenericController<DotacaoOrcamentaria,DotacaoOrcamentariaDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "dotacaoOrcamentariaDaoImp";

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarDotacaoOrcamentaria";
		CADASTRO = "cadastrarDotacaoOrcamentaria";
	}

	public DotacaoOrcamentariaController(){
		injetaDao();
		filtro = new DotacaoOrcamentaria();
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (DotacaoOrcamentariaDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByDotacaoOrcamentaria(filtro);
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(DotacaoOrcamentaria d : getListagem()){
				selectItems.add(new SelectItem(d,d.getCodigo().toString()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	
}
