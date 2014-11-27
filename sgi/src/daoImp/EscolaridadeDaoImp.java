package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.Escolaridade;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.EscolaridadeDao;

@Component
public class EscolaridadeDaoImp extends GenericDaoImp<Escolaridade,Long>
implements EscolaridadeDao{

	@Override
	@Transactional
	public List<Escolaridade> findByEscolaridade(Escolaridade filtro) {
		List<Escolaridade> lista = new ArrayList<Escolaridade>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}