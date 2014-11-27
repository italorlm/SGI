package dao;

import model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario,Long> {

	 Usuario login(Usuario usuario);
}
