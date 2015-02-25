package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Setor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.DaoCrp;
import dao.SetorDao;

@Component
@Scope("globalSession")
public class SetorController extends GenericController<Setor,SetorDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "setorDaoImp";
	
	DaoCrp daoCrp = new DaoCrp();

	public SetorController(){
		injetaDao();
		filtro = new Setor();		
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarSetor";
		CADASTRO = "cadastrarSetor";

	}
	
	public void filtrar(){
		trazerTodos = false;
		listagem = dao.findBySetor(filtro);		
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (SetorDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Setor s : getListagem()){
				selectItems.add(new SelectItem(s,s.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {				
		try {
			List<Setor> lista = daoCrp.listaSetores();
			for(Setor setor : lista) {
				if(setor.getNome().toLowerCase().contains(userInput.toLowerCase()))
					if(!suggestions.contains(setor))
						suggestions.add(setor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

