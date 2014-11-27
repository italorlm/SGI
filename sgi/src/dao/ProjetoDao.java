package dao;

import java.util.List;

import model.Projeto;

public interface ProjetoDao extends GenericDao<Projeto, Long>{
	
	public List<Projeto> findByProjeto(Projeto filtro);	
	
}
