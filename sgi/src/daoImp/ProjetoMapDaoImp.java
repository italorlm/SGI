package daoImp;


import java.util.List;

import model.Projeto;
import model.ProjetoMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjetoMapDao;

@Component
public class ProjetoMapDaoImp extends GenericDaoImp<ProjetoMap,Long>
implements ProjetoMapDao{
	
	@Override
	@Transactional
	public ProjetoMap findByExample(ProjetoMap filtro) {		
		Criteria c = criaCriteria();
		
		if(filtro.getProgramaMap()!=null) {
			c.add(Restrictions.eq("programaMap", filtro.getProgramaMap()));
		}
		
		if(filtro.getProjeto()!=null) {
			c.add(Restrictions.eq("projeto", filtro.getProjeto()));
		}
		
		return (ProjetoMap) c.uniqueResult();
	}

	@Override
	public List<ProjetoMap> findByProjeto(Projeto projeto) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		return c.add(Restrictions.eq("projeto",projeto)).list();
	}

	
}