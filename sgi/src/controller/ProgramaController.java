package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import model.Cargo;
import model.Cidadao;
import model.Contrato;
import model.ContratoParcela;
import model.MaterialPermanente;
import model.Programa;
import model.ProgramaMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.CargoDao;
import dao.ContratoDao;
import dao.ProgramaDao;
import dao.ProgramaMapDao;

@Component
@Scope("globalSession")
public class ProgramaController extends GenericController<Programa, ProgramaDao> {
	
	final static String DAO_CONCRETO = "programaDaoImp";
	
	ProgramaMap programaMap;
	
	boolean mostrarModalMap;
	
	List<ProgramaMap> programaMaps, programaMapExcluidos;
	List<SelectItem> selectItems;
	
	@Resource
	ProgramaMapDao programaMapDao;

	public ProgramaController(){
		injetaDao();		
		filtro = new Programa();
		trazerTodos = true;
	}
	
	@Override
	public void limpar() throws InstantiationException, IllegalAccessException {
		selectItems = new ArrayList<SelectItem>();
		programaMaps = new ArrayList<ProgramaMap>();
		programaMapExcluidos = new ArrayList<ProgramaMap>();		
		super.limpar();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarPrograma";
		CADASTRO = "cadastrarPrograma";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (ProgramaDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByPrograma(filtro);
	}
	
	@Override
	public String salvar() {
		String retorno;
		
		try {
			retorno = super.salvar();
			
			if(!programaMaps.isEmpty()) {
				for(ProgramaMap programaMap : programaMaps) {
					if(objeto.getId()!=null) {
						programaMap.setPrograma(objeto);
						programaMapDao.salvarOuAtualizar(programaMap);
					}				
				}
			}
			
			if(!programaMapExcluidos.isEmpty()) {
				for(ProgramaMap programaMap : programaMapExcluidos) {
					if(programaMap.getId()!=null) {
						programaMapDao.excluir(programaMap);
					}
				}
			}
		} catch(Exception e) {
			retorno = null;
		}
		
		return retorno;
	}
	
	@Override
	public String editar() {
		programaMaps = programaMapDao.findByPrograma(objeto);
		return super.editar();
	}
	
	public void mostrarModalMap(ActionEvent event) {
		programaMap = new ProgramaMap();
		mostrarModalMap = true;
	}
	
	public void adicionarMap() {
		programaMaps.add(programaMap);
		mostrarModalMap = false;
	}
	
	public void excluirMap() {
		if(!programaMaps.isEmpty()) {
			for(Iterator<ProgramaMap> i = programaMaps.iterator(); i.hasNext();) {
				if(i.next().equals(programaMap))
					i.remove();
			}
		}
		
		programaMapExcluidos.add(programaMap);
	}
	
	@Override
	public void filtrarSuggestionBox(String userInput) {
		for(Programa programa : getListagem()) {
			if(programa.getNome().toLowerCase().contains(userInput.toLowerCase()))
				if(!suggestions.contains(programa))
					suggestions.add(programa);
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Programa p : getListagem()){
				selectItems.add(new SelectItem(p,p.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	public ProgramaMap getProgramaMap() {
		return programaMap;
	}

	public void setProgramaMap(ProgramaMap programaMap) {
		this.programaMap = programaMap;
	}

	public List<ProgramaMap> getProgramaMaps() {
		return programaMaps;
	}

	public void setProgramaMaps(List<ProgramaMap> programaMaps) {
		this.programaMaps = programaMaps;
	}

	public boolean isMostrarModalMap() {
		return mostrarModalMap;
	}

	public void setMostrarModalMap(boolean mostrarModalMap) {
		this.mostrarModalMap = mostrarModalMap;
	}

	public List<ProgramaMap> getProgramaMapExcluidos() {
		return programaMapExcluidos;
	}

	public void setProgramaMapExcluidos(List<ProgramaMap> programaMapExcluidos) {
		this.programaMapExcluidos = programaMapExcluidos;
	}	
	
	
}

