package dao;

import java.util.List;

import model.Etnia;

public interface EtniaDao extends GenericDao<Etnia, Long>{
	
	public List<Etnia> findByEtnia(Etnia filtro);	
	
}
