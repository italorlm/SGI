package daoImp;

import java.util.List;

import model.Contrato;
import model.ContratoArquivo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ContratoArquivoDao;

@Component
public class ContratoArquivoDaoImp extends GenericDaoImp<ContratoArquivo,Long>
implements ContratoArquivoDao{

	@Override
	@Transactional
	public List<ContratoArquivo> findByContrato(Contrato filtro) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("contrato",filtro)).list();		
	}

	@Override
	@Transactional
	public ContratoArquivo findByNomeArquivo(String filtro) {
		try{
			return (ContratoArquivo) getEntityManager().createQuery("select ca from ContratoArquivo ca " +
					"where ca.arquivo=:arquivo")
				.setParameter("arquivo", filtro)
				.getSingleResult();
		} catch(Exception e) {
			return null;
		}
				
	}

}
