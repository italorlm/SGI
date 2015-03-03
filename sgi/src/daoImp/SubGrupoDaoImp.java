package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.EntidadeCargo;
import model.Grupo;
import model.SubGrupo;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.EntidadeCargoDao;
import dao.GrupoDao;
import dao.SubGrupoDao;

@Component
public class SubGrupoDaoImp extends GenericDaoImp<SubGrupo,Long>
implements SubGrupoDao{

	@Override
	@Transactional
	public List<SubGrupo> findBySubGrupo(Grupo filtro) {		
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("grupo",filtro)).list();
	}
}