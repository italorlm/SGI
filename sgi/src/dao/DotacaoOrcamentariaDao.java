package dao;

import java.util.List;

import model.DotacaoOrcamentaria;

public interface DotacaoOrcamentariaDao extends GenericDao<DotacaoOrcamentaria,Long> {
	
	public List<DotacaoOrcamentaria> findByDotacaoOrcamentaria(DotacaoOrcamentaria filtro);
}
