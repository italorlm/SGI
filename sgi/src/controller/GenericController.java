package controller;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import model.BaseModel;
import model.Cidadao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.MessageUtil;
import context.AppContext;
import dao.GenericDao;

public abstract class GenericController<T extends BaseModel,Y extends GenericDao<T, Long>> {

	public T objeto,filtro;
	List<T> listagem;
	List<T> suggestions;
	private final Class<T> classeModel;
	boolean trazerTodos;
	public Y dao;
	public String LISTAGEM = "";
	public String CADASTRO ="";
	public String TELA_INSCRITOS ="";
	public String orderBy = null;
	
	public GenericController(){
		setaNavegacao();
		this.classeModel = (Class<T>)
		((ParameterizedType)getClass().getGenericSuperclass())
		.getActualTypeArguments()[0];
	}

	public void limpar() throws InstantiationException, IllegalAccessException{
		listagem = new ArrayList<T>();
		objeto = classeModel.newInstance();
		filtro = classeModel.newInstance();
		trazerTodos = true;
	}

	public T getObjeto() {
		return objeto;
	}
	
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}
    

	public void setFiltro(T filtro) {
		this.filtro = filtro;
	}

	String redirecionar(String pagina) throws InstantiationException, IllegalAccessException{
		limpar();
		return pagina;
	}
	
	public String novo() throws InstantiationException, IllegalAccessException{
		return redirecionar(CADASTRO);
	}
	
	public String listar() throws InstantiationException, IllegalAccessException{
		return redirecionar(LISTAGEM);
	}
	
	public String salvar(){
		String retorno = LISTAGEM;
		try{
			dao.salvarOuAtualizar(objeto);
			MessageUtil.mostraMensagemSucesso();
		}catch(Exception e){
			e.printStackTrace();
			retorno = "";
			MessageUtil.mostraMensagemErro();
		}
		return retorno;
	}

	public String editar(){
		return CADASTRO;
	}
	
	public void excluir(){
		String msgErro = "Problema ao excluir registro";
		try {
			dao.excluir(objeto);
			listagem.clear();
			MessageUtil.mostraMensagemSucesso();
		}catch(Exception e){
			e.printStackTrace();
			MessageUtil.addMessage(msgErro, FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<T> suggestionBox(Object input) {
		String userInput = (String) input;				
		suggestions = new ArrayList<T>();
		
		filtrarSuggestionBox(userInput);
		
		return suggestions;
	}

	public List<T> getListagem() {
		if(listagem==null) listagem = new ArrayList<T>();
		if(listagem.size()==0 && trazerTodos) {
			listagem = dao.todos(orderBy);
		}
		return listagem;
	}

	public void setListagem(List<T> listagem) {
		this.listagem = listagem;
	}
		
	public void mostrarRodape(){
		LoginController.mostrarRodape();
	}
	
	public void esconderRodape(){
		LoginController.esconderRodape();
	}
		
    public ApplicationContext getApplicationContext(){
        ApplicationContext ctx = AppContext.getApplicationContext();
    	return ctx;
    }

	public T getFiltro() {
		return filtro;
	}
		
	public List<T> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<T> suggestions) {
		this.suggestions = suggestions;
	}
	
	public abstract void filtrarSuggestionBox(String userInput);
	public abstract void setaNavegacao();
	public abstract void injetaDao();
}
