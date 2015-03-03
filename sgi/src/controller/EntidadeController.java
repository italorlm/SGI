package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import model.Entidade;
import model.EntidadeCargo;
import model.ProgramaMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.EntidadeCargoDao;
import dao.EntidadeDao;

@Component
@Scope("globalSession")
public class EntidadeController extends GenericController<Entidade, EntidadeDao> {

	String valorSalario;
	
	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "entidadeDaoImp";
	
	EntidadeCargo entidadeCargo;
	
	List<EntidadeCargo> entidadeCargos, entidadeCargoExcluidos;
	
	boolean mostrarModalCargo;
	
	@Resource
	EntidadeCargoDao entidadeCargoDao;

	public EntidadeController(){
		injetaDao();		
		filtro = new Entidade();
		trazerTodos = true;
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarEntidade";
		CADASTRO = "cadastrarEntidade";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (EntidadeDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		entidadeCargos = new ArrayList<EntidadeCargo>();
		entidadeCargoExcluidos = new ArrayList<EntidadeCargo>();		
		super.limpar();
	}
	
	public void filtrar() {
		trazerTodos = false;		
	}
	
	public List<SelectItem> getSelectItems() {		
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Entidade e : getListagem()){
				selectItems.add(new SelectItem(e,e.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	@Override
	public String salvar() {
		String retorno;
		
		try {
			retorno = super.salvar();
			
			if(!entidadeCargos.isEmpty()) {
				for(EntidadeCargo entidadeCargo : entidadeCargos) {
					if(objeto.getId()!=null) {
						entidadeCargo.setEntidade(objeto);
						if(entidadeCargo.isEditado()==true)
							entidadeCargoDao.salvarOuAtualizar(entidadeCargo);
					}				
				}
			}
			
			if(!entidadeCargoExcluidos.isEmpty()) {
				for(EntidadeCargo entidadeCargo : entidadeCargoExcluidos) {
					if(entidadeCargo.getId()!=null) {
						entidadeCargoDao.excluir(entidadeCargo);
					}
				}
			}
		} catch(Exception e) {
			retorno = null;
		}
		
		return retorno;		
	}
	
	public void adicionarCargo() {
		if(entidadeCargo.getId()!=null)
			entidadeCargos.remove(entidadeCargo);
		if(!entidadeCargos.contains(entidadeCargo)) {
			entidadeCargo.setSalario(Double.parseDouble(valorSalario.replaceAll("\\.","").replace(",",".")));
			entidadeCargo.editado();			
			entidadeCargos.add(entidadeCargo);
		}		
		mostrarModalCargo = false;
	}
	
	public void excluirCargo() {
		if(!entidadeCargos.isEmpty()) {
			for(Iterator<EntidadeCargo> i = entidadeCargos.iterator(); i.hasNext();) {
				if(i.next().equals(entidadeCargo))
					i.remove();
			}
		}
		
		entidadeCargoExcluidos.add(entidadeCargo);
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Entidade entidade : getListagem()) {
			if(entidade.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(entidade))
					suggestions.add(entidade);
		}
	}
	
	public void mostrarModalCargo(ActionEvent event) {
		entidadeCargo = new EntidadeCargo();
		mostrarModalCargo = true;
	}

	public EntidadeCargo getEntidadeCargo() {
		return entidadeCargo;
	}

	public void setEntidadeCargo(EntidadeCargo entidadeCargo) {
		this.entidadeCargo = entidadeCargo;
	}

	public List<EntidadeCargo> getEntidadeCargos() {
		return entidadeCargos;
	}

	public void setEntidadeCargos(List<EntidadeCargo> entidadeCargos) {
		this.entidadeCargos = entidadeCargos;
	}
	
	public List<EntidadeCargo> getEntidadeCargoExcluidos() {
		return entidadeCargoExcluidos;
	}

	public void setEntidadeCargoExcluidos(List<EntidadeCargo> entidadeCargoExcluidos) {
		this.entidadeCargoExcluidos = entidadeCargoExcluidos;
	}

	public boolean isMostrarModalCargo() {
		return mostrarModalCargo;
	}

	public void setMostrarModalCargo(boolean mostrarModalCargo) {
		this.mostrarModalCargo = mostrarModalCargo;
	}

	public String getValorSalario() {
		return valorSalario;
	}

	public void setValorSalario(String valorSalario) {
		this.valorSalario = valorSalario;
	}

}

