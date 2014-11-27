package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.Grupo;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.GrupoDao;

@Component
public class GrupoDaoImp extends GenericDaoImp<Grupo,Long>
implements GrupoDao{

	@Override
	@Transactional
	public List<Grupo> findByGrupo(Grupo filtro) {
		List<Grupo> lista = new ArrayList<Grupo>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}