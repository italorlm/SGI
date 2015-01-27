package daoImp;


import java.util.List;

import model.Programa;
import model.ProgramaMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ProgramaMapDao;

@Component
public class ProgramaMapDaoImp extends GenericDaoImp<ProgramaMap,Long>
implements ProgramaMapDao{

	@Override
	@Transactional
	public List<ProgramaMap> findByPrograma(Programa programa) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		return c.add(Restrictions.eq("programa",programa)).list();
	}
}