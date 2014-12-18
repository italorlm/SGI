package dao;

import java.util.List;

import model.Cidadao;

public interface CidadaoDao extends GenericDao<Cidadao, Long>{
	
	public List<Cidadao> findByExample(Cidadao cidadao);	
	
}
