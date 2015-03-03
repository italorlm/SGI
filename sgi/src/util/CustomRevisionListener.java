package util;

import java.util.Date;

import model.CustomRevisionEntity;
import model.Usuario;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		CustomRevisionEntity revision = (CustomRevisionEntity) revisionEntity;
		Usuario usuario = (Usuario) FacesUtils.getSession().getAttribute("usuario");
		revision.setUsuario(usuario.getLogin());
		revision.setNome(usuario.getFuncionario().getNome());
		revision.setData(new Date());
	}

}
