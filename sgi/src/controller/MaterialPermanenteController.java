package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.MaterialPermanente;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.MaterialPermanenteDao;

@Component
@Scope("globalSession")
public class MaterialPermanenteController extends GenericController<MaterialPermanente, MaterialPermanenteDao> {


	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "materialPermanenteDaoImp";

	public MaterialPermanenteController(){
		injetaDao();
		filtro = new MaterialPermanente();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarMaterialPermanente";
		CADASTRO = "cadastrarMaterialPermanente";

	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (MaterialPermanenteDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(MaterialPermanente m : getListagem()){
				selectItems.add(new SelectItem(m,m.getDescricao()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(MaterialPermanente materialPermanente : getListagem()) {
			if(materialPermanente.getDescricao().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(materialPermanente))
					suggestions.add(materialPermanente);
		}
	}
}

