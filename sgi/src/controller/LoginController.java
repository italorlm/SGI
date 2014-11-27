package controller;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Usuario;
import model.UsuarioAcesso;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.UsuarioAcessoDao;
import dao.UsuarioDao;
import util.FacesUtils;

@Component
@Scope("globalSession")
public class LoginController {

	private boolean logado;
	String login,senha;
	boolean erroLogin;
	Usuario usuario;
	UsuarioAcesso usuarioAcesso;
	
	@Resource
	UsuarioDao usuarioDao;
	
	@Resource
	UsuarioAcessoDao usuarioAcessoDao;
	
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
	public UsuarioAcesso getUsuarioAcesso() {
		return usuarioAcesso;
	}
	public void setUsuarioAcesso(UsuarioAcesso usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
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
		usuario  = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario = usuarioDao.login(usuario); 
		String resultado = "";
		if(usuario!=null){
			HttpSession session = FacesUtils.getSession();
			session.setAttribute("usuario", usuario);
			logado = true; 
			erroLogin =false;
			resultado = registraAcesso();	
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
	
	public String registraAcesso() {
		usuarioAcesso = new UsuarioAcesso(usuario);
		usuarioAcessoDao.salvar(usuarioAcesso);
		return "sucessoLogin";
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

