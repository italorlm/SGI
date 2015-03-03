package dao;

import java.util.List;

import model.Entidade;
import model.EntidadeCargo;

public interface EntidadeCargoDao extends GenericDao<EntidadeCargo, Long>{
	
	public List<EntidadeCargo> findByEntidade(Entidade entidade);
	
}
