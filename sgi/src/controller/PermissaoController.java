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
	
	Map<String,List<String>> restricoes = new HashMap<String, List<String>>();

	public static String ADMINISTRADOR = "ADMINISTRADOR";
	public static String ADMINISTRADOR_FINANCEIRO = "ADMINISTRADOR FINANCEIRO"; 
	public static String COORDENADOR = "COORDENADOR";
	public static String SECRETARIO = "SECRETARIO";
	public static String USUARIO = "USUARIO";

	void montaPermissoes(){
		//Usuários
		restricoes.put("menuUsuario", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO));
		
		//Unidades
		restricoes.put("excluirUnidade", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO));		
		restricoes.put("salvarUnidade", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO));
		
		//Funcionarios
		restricoes.put("excluirFuncionario", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO));		
		restricoes.put("salvarFuncionario", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO));
		
		//Programas
		restricoes.put("excluirPrograma", Arrays.asList(USUARIO));		
		restricoes.put("salvarPrograma", Arrays.asList(USUARIO));
		
		//Projetos
		restricoes.put("excluirPrograma", Arrays.asList(USUARIO));		
		restricoes.put("salvarPrograma", Arrays.asList(USUARIO));
		
		//Financeiro
		restricoes.put("menuFinanceiro", Arrays.asList(USUARIO));
		
		//Paineis
		restricoes.put("menuPainel", Arrays.asList(USUARIO, ADMINISTRADOR_FINANCEIRO, ADMINISTRADOR));
		
	}

	public boolean temPermissao(String acao){
		boolean acesso = true;
		Usuario u = (Usuario)FacesUtils.getSession().getAttribute("usuario");
		String permissao = u.getPerfil().getNome();
		if(restricoes.get(acao).contains(permissao)){
			acesso = false;
		}
		return acesso;
	}
	
//	public static boolean usuarioAdministrador(){
//		Usuario u = (Usuario)FacesUtils.getSession().getAttribute("usuario");
//		return u.getPerfil().getNome().equals(ADMINISTRADOR);
//	}
		
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
