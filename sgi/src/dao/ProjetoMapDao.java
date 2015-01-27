package dao;

import java.util.List;

import model.Programa;
import model.ProgramaMap;
import model.Projeto;
import model.ProjetoMap;

public interface ProjetoMapDao extends GenericDao<ProjetoMap, Long>{	
		
	public List<ProjetoMap> findByProjeto(Projeto projeto);
	public ProjetoMap findByExample(ProjetoMap filtro);
	
}
