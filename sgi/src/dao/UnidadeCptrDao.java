package dao;

import java.util.List;

import model.UnidadeCptr;

public interface UnidadeCptrDao extends GenericDao<UnidadeCptr,Long> {

	public List<UnidadeCptr> findByExample(UnidadeCptr filtro);
	
}
