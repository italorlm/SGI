package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.DotacaoOrcamentaria;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.DotacaoOrcamentariaDao;

@Component
public class DotacaoOrcamentariaDaoImp extends GenericDaoImp<DotacaoOrcamentaria,Long>
implements DotacaoOrcamentariaDao{

	@Override
	@Transactional
	public List<DotacaoOrcamentaria> findByDotacaoOrcamentaria(
			DotacaoOrcamentaria filtro) {
		List<DotacaoOrcamentaria> lista = new ArrayList<DotacaoOrcamentaria>();
		Criteria c = criaCriteria();
		
		if(filtro.getCodigo()!=null) {
			c.add(Restrictions.eq("codigo", filtro.getCodigo()));
		}
		
		if(filtro.getPrograma()!=null) {
			c.add(Restrictions.eq("programa", filtro.getPrograma()));
		}
		
		if(filtro.getProjeto()!=null) {
			c.add(Restrictions.eq("projeto", filtro.getProjeto()));
		}
		
		lista = c.list();
		return lista;
	}

}
