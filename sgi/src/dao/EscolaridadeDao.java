package dao;

import java.util.List;

import model.Escolaridade;

public interface EscolaridadeDao extends GenericDao<Escolaridade, Long>{
	
	public List<Escolaridade> findByEscolaridade(Escolaridade filtro);	
	
}
