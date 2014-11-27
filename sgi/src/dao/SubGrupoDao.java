package dao;

import java.util.List;

import model.Grupo;
import model.SubGrupo;

public interface SubGrupoDao extends GenericDao<SubGrupo, Long>{
	
	public List<SubGrupo> findBySubGrupo(Grupo filtro);	
	
}
