package dao;

import java.util.List;

import model.MaterialConsumo;


public interface MaterialConsumoDao extends GenericDao<MaterialConsumo, Long> {
	public List<MaterialConsumo> findByExample(MaterialConsumo exemplo);
}
