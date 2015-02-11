package daoImp;

import java.util.List;

import model.Contrato;
import model.ContratoAditivo;
import model.ContratoParcela;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ContratoAditivoDao;
import dao.ContratoParcelaDao;

@Component
public class ContratoAditivoDaoImp extends GenericDaoImp<ContratoAditivo,Long>
implements ContratoAditivoDao{

	@Override
	@Transactional
	public List<ContratoAditivo> findByContrato(Contrato filtro) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("contrato",filtro)).list();		
	}	

}
