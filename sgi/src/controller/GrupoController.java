package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import model.Cargo;
import model.Grupo;
import model.MaterialConsumo;
import model.SubGrupo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.GrupoDao;
import dao.SubGrupoDao;

@Component
@Scope("globalSession")
public class GrupoController extends GenericController<Grupo, GrupoDao> {

	List<SelectItem> selectItems;
	List<SubGrupo> subGruposExcluidos, subGrupos;
	
	boolean mostrarModalSubGrupo;
	
	SubGrupo subGrupo;
	
	@Resource
	SubGrupoDao subGrupoDao;
	
	final static String DAO_CONCRETO = "grupoDaoImp";	

	public GrupoController() throws InstantiationException, IllegalAccessException{
		injetaDao();		
		filtro = new Grupo();		
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {		
		subGruposExcluidos = new ArrayList<SubGrupo>();
		subGrupos = new ArrayList<SubGrupo>();	
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarGrupo";
		CADASTRO = "cadastrarGrupo";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (GrupoDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByGrupo(filtro);
	}

	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Grupo g : getListagem()){
				selectItems.add(new SelectItem(g,g.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	@Override
	public String editar() {
		subGrupos = subGrupoDao.findBySubGrupo(objeto);
		return super.editar();
	}
	
	@Override
	public String salvar() {
		String retorno = null;
		retorno = super.salvar();
		
		if(!subGrupos.isEmpty()) {
			for(SubGrupo subGrupo : subGrupos) {
				if(subGrupo.getGrupo()==null && objeto.getId()!=null) {
					subGrupo.setGrupo(objeto);
					subGrupoDao.salvarOuAtualizar(subGrupo);
				}				
			}
		}
		
		if(!subGruposExcluidos.isEmpty()) {
			for(SubGrupo subGrupo : subGruposExcluidos) {
				if(subGrupo.getId()!=null)				
					subGrupoDao.excluir(subGrupo);
			}
		}
		
		return retorno;
	}
	
	public void mostrarModalSubGrupo(ActionEvent event) {
		subGrupo = new SubGrupo();
		mostrarModalSubGrupo = true;
	}
	
	public void adicionarSubGrupo() {
		if(subGrupo.getId()==null) {
			subGrupos.add(subGrupo);
			mostrarModalSubGrupo = false;
		}			
	}
	
	public void excluirSubGrupo() {
		if(!subGrupos.isEmpty()) {
			for(SubGrupo subGrupo : subGrupos) {
				if(subGrupo.equals(this.subGrupo)) {
					subGrupos.remove(subGrupo);
				}
			}
		} 
		
		subGruposExcluidos.add(subGrupo);		
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Grupo grupo : getListagem()) {
			if(grupo.getNome().toLowerCase().startsWith(userInput.toLowerCase()))
				if(!suggestions.contains(grupo))
					suggestions.add(grupo);
		}
	}
	
	public List<SubGrupo> getSubGruposExcluidos() {
		return subGruposExcluidos;
	}

	public void setSubGruposExcluidos(List<SubGrupo> subGruposExcluidos) {
		this.subGruposExcluidos = subGruposExcluidos;
	}

	public List<SubGrupo> getSubGrupos() {
		return subGrupos;
	}

	public void setSubGrupos(List<SubGrupo> subGrupos) {
		this.subGrupos = subGrupos;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public boolean isMostrarModalSubGrupo() {
		return mostrarModalSubGrupo;
	}

	public void setMostrarModalSubGrupo(boolean mostrarModalSubGrupo) {
		this.mostrarModalSubGrupo = mostrarModalSubGrupo;
	}	
}

