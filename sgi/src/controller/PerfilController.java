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

}
