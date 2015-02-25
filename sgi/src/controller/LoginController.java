package controller;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Acesso;
import model.Usuario;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.AcessoDao;
import dao.UsuarioDao;
import util.FacesUtils;

@Component
@Scope("globalSession")
public class LoginController {

	private boolean logado = false;
	String login,senha;
	
	@Resource
	UsuarioDao usuarioDao;
	
	@Resource
	AcessoDao acessoDao;
	
	boolean erroLogin;
	Usuario usuario;

	public  static boolean mostrarRodape = true;

	public  boolean isMostrarRodape() {
		return mostrarRodape;
	}
	public void setMostrarRodape(boolean mostrarRodape) {
		LoginController.mostrarRodape = mostrarRodape;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {this.login = login;}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {this.senha = senha;}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isLogado() {return logado;}
	public void setLogado(boolean logado) {this.logado = logado;}

	//	public UsuarioRest getUsuario() {
	//		return usuario;
	//	}
	//	public void setUsuario(UsuarioRest usuario) {
	//		this.usuario = usuario;
	//	}

	public String logar(){
				
		logado=false;
		usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario = usuarioDao.login(usuario); 
		String resultado = "";
		if(usuario!=null){
			HttpSession session = FacesUtils.getSession();
			session.setAttribute("usuario", usuario);
			logado = true; 
			erroLogin =false;
			resultado = "sucessoLogin";	
			
			Acesso acesso = new Acesso(usuario.getLogin(), new Date());
			acessoDao.salvar(acesso);
		}else{
			erroLogin = true;
			resultado = "erroLogin";
		}

		return resultado;
	}


	public String logout(){
		mostrarRodape();
		reset();
		FacesContext fc = FacesContext.getCurrentInstance();  
		FacesUtils.getSession().removeAttribute("usuario");
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);  
		//expira a sessão  
		session.invalidate();  
		return "paginaLogin";
	}

	public void reset(){
		login = "";
		senha = "";
		logado = false;
		erroLogin = false;
	}

	public static void mostrarRodape(){mostrarRodape = true;}
	public static void esconderRodape(){mostrarRodape = false;}
	public boolean isErroLogin() {
		return erroLogin;
	}
	public void setErroLogin(boolean erroLogin) {
		this.erroLogin = erroLogin;
	}
	
	
}

