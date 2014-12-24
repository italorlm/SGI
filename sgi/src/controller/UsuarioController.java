package controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import model.Usuario;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.RelatorioGenerico;
import dao.UsuarioDao;

@Component
@Scope("globalSession")
public class UsuarioController extends GenericController<Usuario, UsuarioDao> {

	List<SelectItem> selectItems;
	final static String DAO_CONCRETO = "usuarioDaoImp";

	public UsuarioController(){
		injetaDao();
		filtro = new Usuario();
	}

	@Override
	public void setaNavegacao() {
		LISTAGEM = "listarUsuario";
		CADASTRO = "cadastrarUsuario";

	}

	@Override
	public void injetaDao() {
		try{
			super.dao = (UsuarioDao) getApplicationContext().getBean(DAO_CONCRETO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void imprimir(){
		trazerTodos = true;
		ServletContext ctx = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		Map parameters = new HashMap();

		String caminhoRelatorio = "/relatorios/usuarios.jasper";
		Connection conn = null;
		List<Usuario> lista = getListagem();
		JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lista);
		new RelatorioGenerico().imprimir(ctx,parameters, caminhoRelatorio, ds, "Usuarios.pdf");
	}

	public List<SelectItem> getSelectItems() {
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
