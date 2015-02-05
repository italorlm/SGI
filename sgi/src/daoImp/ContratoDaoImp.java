package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.Contrato;
import model.Projeto;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.ContratoDao;

@Component
public class ContratoDaoImp extends GenericDaoImp<Contrato,Long>
implements ContratoDao{

	@Override
	@Transactional
	public List<Contrato> findByContrato(Contrato filtro) {
		List<Contrato> lista = new ArrayList<Contrato>();
		Criteria c = criaCriteria();
		
		if(StringUtils.isValid(filtro.getExecutor())){
			c.add(Restrictions.ilike("executor", filtro.getExecutor(),MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isValid(filtro.getContrato())){
			c.add(Restrictions.ilike("contrato", filtro.getContrato(),MatchMode.ANYWHERE));
		}		
						
		lista = c.list();
		return lista;
	}

}
