package daoImp;

import java.util.List;

import model.Contrato;
import model.ContratoFonteRecurso;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ContratoFonteRecursoDao;

@Component
public class ContratoFonteRecursoDaoImp extends GenericDaoImp<ContratoFonteRecurso,Long>
implements ContratoFonteRecursoDao{
	
	@Override
	@Transactional
	public List<ContratoFonteRecurso> findByContrato(Contrato filtro) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("contrato",filtro)).list();		
	}
}
