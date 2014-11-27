package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.Programa;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.ProgramaDao;

@Component
public class ProgramaDaoImp extends GenericDaoImp<Programa,Long>
implements ProgramaDao{

	@Override
	@Transactional
	public List<Programa> findByPrograma(Programa filtro) {
		List<Programa> lista = new ArrayList<Programa>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}