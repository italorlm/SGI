package dao;

import java.util.List;

import model.UnidadeExterna;

public interface UnidadeExternaDao extends GenericDao<UnidadeExterna,Long> {

	public List<UnidadeExterna> findByExample(UnidadeExterna filtro);
	
}
