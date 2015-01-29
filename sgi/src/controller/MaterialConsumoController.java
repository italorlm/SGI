package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import model.MaterialConsumo;
import model.SubGrupo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.MaterialConsumoDao;
import dao.SubGrupoDao;

@Component
@Scope("globalSession")
public class MaterialConsumoController extends GenericController<MaterialConsumo, MaterialConsumoDao> {

	List<SelectItem> selectItems, itemsSubGrupos;
	
	List<SubGrupo> subGrupos;
	
	@Resource
	SubGrupoDao subGrupoDao;
	
	final static String DAO_CONCRETO = "materialConsumoDaoImp";

	public MaterialConsumoController(){
		injetaDao();
		filtro = new MaterialConsumo();
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		subGrupos = new ArrayList<SubGrupo>();
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarMaterialConsumo";
		CADASTRO = "cadastrarMaterialConsumo";

	}

	public void filtrar(){
		trazerTodos = false;
		listagem = dao.findByExample(filtro);
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (MaterialConsumoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(MaterialConsumo m : getListagem()){
				selectItems.add(new SelectItem(m,m.getDescricao()));
			}
		}
		return selectItems;
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(MaterialConsumo materialConsumo : getListagem()) {
			if(materialConsumo.getCodigo().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(materialConsumo))
					suggestions.add(materialConsumo);
			if(materialConsumo.getDescricao().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(materialConsumo))
					suggestions.add(materialConsumo);
		}
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	public List<SelectItem> buscarSubGrupos() {
		return getItemsSubGrupos();
	}

	public List<SelectItem> getItemsSubGrupos() {
		itemsSubGrupos = new ArrayList<SelectItem>();		
		
		subGrupos = subGrupoDao.findBySubGrupo(objeto.getGrupo());		
		if(subGrupos.size()!=0) {
			for(SubGrupo subGrupo : subGrupos) {
				itemsSubGrupos.add(new SelectItem(subGrupo, subGrupo.getNome()));
			}
		}
		return itemsSubGrupos;
	}

	public void setItemsSubGrupos(List<SelectItem> itemsSubGrupos) {
		this.itemsSubGrupos = itemsSubGrupos;
	}

	public List<SubGrupo> getSubGrupos() {
		return subGrupos;
	}

	public void setSubGrupos(List<SubGrupo> subGrupos) {
		this.subGrupos = subGrupos;
	}	
}

