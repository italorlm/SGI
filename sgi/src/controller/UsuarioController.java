package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
	
	public void imprimirManual() throws IOException{
		try {
			ServletContext ctx = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			File f = new File(ctx.getRealPath("/publicacoes/MANUAL_SGI.pdf"));
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("content-disposition",
					"attachment;filename=Manual_SGI.pdf");
			byte[]bytes = bytesFromFile(f);
			response.setContentLength(bytes.length);
			ServletOutputStream servletStream = response.getOutputStream();
			servletStream.write(bytes, 0, bytes.length);
			servletStream.flush();
			servletStream.close();
			FacesContext.getCurrentInstance().responseComplete();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static byte[] bytesFromFile(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			System.out.println("Sorry! Your given file is too large.");
			System.exit(0);
		}

		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead=is.read(bytes, 
				offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
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
