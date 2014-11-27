package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
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

}
