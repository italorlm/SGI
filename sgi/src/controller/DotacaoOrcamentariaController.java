package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.EntidadeCargo;
import model.Cidadao;
import model.DotacaoOrcamentaria;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.DotacaoOrcamentariaDao;

@Component
@Scope("globalSession")
public class DotacaoOrcamentariaController extends GenericController<DotacaoOrcamentaria,DotacaoOrcamentariaDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "dotacaoOrcamentariaDaoImp";
	
	//Codigo da Entidade STDS na Dotação Orçamentaria
	final static String STDS_CODIGO = "47000000";

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

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(DotacaoOrcamentaria dotacaoOrcamentaria : getListagem()) {
			if(dotacaoOrcamentaria.getCodigo().toString().contains(userInput))
				if(!suggestions.contains(dotacaoOrcamentaria))
					suggestions.add(dotacaoOrcamentaria);
		}
	}
}
