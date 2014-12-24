package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.TipoMaterialPermanente;
import model.UnidadeCptr;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.TipoMaterialPermanenteDao;

@Component
@Scope("globalSession")
public class TipoMaterialPermanenteController extends GenericController<TipoMaterialPermanente, TipoMaterialPermanenteDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "tipoMaterialPermanenteDaoImp";

	public TipoMaterialPermanenteController(){
		injetaDao();		
		filtro = new TipoMaterialPermanente();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarTipoMaterialPermanente";
		CADASTRO = "cadastrarTipoMaterialPermanente";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (TipoMaterialPermanenteDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByTipoMaterialPermanente(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(TipoMaterialPermanente t : getListagem()){
				selectItems.add(new SelectItem(t,t.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(TipoMaterialPermanente tipoMaterialPermanente : getListagem()) {
			if(tipoMaterialPermanente.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(tipoMaterialPermanente))
					suggestions.add(tipoMaterialPermanente);
		}
	}
}

