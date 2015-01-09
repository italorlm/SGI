package daoImp;

import java.util.List;

import model.Contrato;
import model.ContratoArquivo;
import model.Projeto;
import model.ProjetoArquivo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.ContratoArquivoDao;
import dao.ProjetoArquivoDao;

@Component
public class ProjetoArquivoDaoImp extends GenericDaoImp<ProjetoArquivo,Long>
implements ProjetoArquivoDao{

	@Override
	@Transactional
	public List<ProjetoArquivo> findByArquivo(Projeto filtro) {
		Criteria c = criaCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.add(Restrictions.eq("projeto",filtro)).list();		
	}

	@Override
	@Transactional
	public ProjetoArquivo findByNomeArquivo(String filtro) {
		try{
			return (ProjetoArquivo) getEntityManager().createQuery("select pa from ProjetoArquivo pa " +
					"where pa.arquivo=:arquivo")
				.setParameter("arquivo", filtro)
				.getSingleResult();
		} catch(Exception e) {
			return null;
		}
				
	}

}
