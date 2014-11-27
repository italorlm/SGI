package dao;

import java.util.List;

import model.Grupo;

public interface GrupoDao extends GenericDao<Grupo, Long>{
	
	public List<Grupo> findByGrupo(Grupo filtro);	
	
}
