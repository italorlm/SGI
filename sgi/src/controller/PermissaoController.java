package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import util.FacesUtils;

@Component
@Scope("globalSession")
public class PermissaoController {

	public PermissaoController(){
		montaPermissoes();
	}

	public static String ADMINISTRADOR = "Administrador";
	
	Map<String,List<String>> permissoes  = new HashMap<String, List<String>>() ;

	void montaPermissoes(){
		permissoes.put("menuAdministrativo",Arrays.asList(ADMINISTRADOR));
	
	}

	public boolean temPermissao(String acao){
		boolean acesso = false;
		Usuario u = (Usuario)FacesUtils.getSession().getAttribute("usuario");
		String permissaoUsuario = u.getPerfil().getNome();
		if(permissoes.get(acao).contains(permissaoUsuario)){
			acesso = true;
		}
		return acesso;
	}
	
	public static boolean usuarioAdministrador(){
		Usuario u = (Usuario)FacesUtils.getSession().getAttribute("usuario");
		return u.getPerfil().getNome().equals(ADMINISTRADOR);
	}
		
//	public static boolean perfilCorrespondente(List<String> perfis){
//		boolean temAcesso = false;
//		Usuario u = (Usuario)FacesUtils.getSession().getAttribute("usuario");
//		
//		for(String perfil : perfis){
//			if (u.getPerfil().getDescricao().equals(perfil)){
//				temAcesso = true;
//				break;
//			}
//		}
//		return temAcesso;
//	 }

}
