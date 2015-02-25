package dao;

import java.util.List;

import model.Projeto;
import model.Setor;

public interface SetorDao extends GenericDao<Setor, Long>{
	
	public List<Setor> findBySetor(Setor filtro);	
	
}
