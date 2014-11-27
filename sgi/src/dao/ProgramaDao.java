package dao;

import java.util.List;

import model.Programa;

public interface ProgramaDao extends GenericDao<Programa, Long>{
	
	public List<Programa> findByPrograma(Programa filtro);	
	
}
