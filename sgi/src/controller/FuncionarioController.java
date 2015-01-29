package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Cargo;
import model.Cidadao;
import model.Funcionario;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.DaoBdDirhu;
import dao.FuncionarioDao;

@Component
@Scope("globalSession")
public class FuncionarioController extends GenericController<Funcionario, FuncionarioDao> {


	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "funcionarioDaoImp";
	
	DaoBdDirhu daoBdDirhu = new DaoBdDirhu();

	public FuncionarioController(){
		injetaDao();
		filtro = new Funcionario();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarFuncionario";
		CADASTRO = "cadastrarFuncionario";

	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (FuncionarioDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void filtrar() {
		trazerTodos = false;
		listagem = dao.findByFuncionario(filtro);
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Funcionario f : getListagem()){
				selectItems.add(new SelectItem(f,f.getNome()));
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
			List<Funcionario> lista = daoBdDirhu.buscarFuncionarios();
			for(Funcionario funcionario : lista) {
				if(funcionario.getNome().toLowerCase().contains(userInput.toLowerCase()))
					if(!suggestions.contains(funcionario))
						suggestions.add(funcionario);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
		
}

