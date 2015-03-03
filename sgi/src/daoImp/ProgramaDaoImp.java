package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.EntidadeCargo;
import model.Programa;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.EntidadeCargoDao;
import dao.ProgramaDao;

@Component
public class ProgramaDaoImp extends GenericDaoImp<Programa,Long>
implements ProgramaDao{

	@Override
	@Transactional
	public List<Programa> findByPrograma(Programa filtro) {
		List<Programa> lista = new ArrayList<Programa>();
		Criteria c = criaCriteria();
		
		if(StringUtils.isValid(filtro.getNome())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}