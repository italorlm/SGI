package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.Etnia;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.EtniaDao;

@Component
public class EtniaDaoImp extends GenericDaoImp<Etnia,Long>
implements EtniaDao{

	@Override
	@Transactional
	public List<Etnia> findByEtnia(Etnia filtro) {
		List<Etnia> lista = new ArrayList<Etnia>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}