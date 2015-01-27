package dao;

import java.util.List;

import model.Programa;
import model.ProgramaMap;

public interface ProgramaMapDao extends GenericDao<ProgramaMap, Long>{	
		
	public List<ProgramaMap> findByPrograma(Programa programa);
	
}
