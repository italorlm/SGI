package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import model.Perfil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.PerfilDao;

@Component
@Scope("globalSession")
public class PerfilController extends GenericController<Perfil, PerfilDao> {


	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "perfilDaoImp";

	public PerfilController(){
		injetaDao();
		filtro = new Perfil();
		trazerTodos = true;
	}
	
	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarPerfil";
		CADASTRO = "cadastrarPerfil";
	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (PerfilDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		if(selectItems.size()==0){
			for(Perfil p : getListagem()){
				selectItems.add(new SelectItem(p,p.getNome()));
			}
		}
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	@Override
	public void filtrarSuggestionBox(String userInput) {
		// TODO Auto-generated method stub		
	}	
}
