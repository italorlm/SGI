package daoImp;

import java.util.List;

import model.Contrato;
import model.ContratoParcela;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ContratoParcelaDao;

@Component
public class ContratoParcelaDaoImp extends GenericDaoImp<ContratoParcela,Long>
implements ContratoParcelaDao{

	@Override
	@Transactional
	public List<ContratoParcela> findByParcela(Contrato filtro) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("contrato",filtro)).list();		
	}	

}
