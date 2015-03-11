package daoImp;


import java.util.List;

import model.Entidade;
import model.EntidadeCargo;
import model.Projeto;
import model.ProjetoMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.EntidadeCargoDao;

@Component
public class EntidadeCargoDaoImp extends GenericDaoImp<EntidadeCargo,Long>
implements EntidadeCargoDao{

	@Override
	@Transactional
	public List<EntidadeCargo> findByEntidade(Entidade entidade) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		return c.add(Restrictions.eq("entidade",entidade)).list();
	}

}