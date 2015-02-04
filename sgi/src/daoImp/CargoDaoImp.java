package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.CargoDao;

@Component
public class CargoDaoImp extends GenericDaoImp<Cargo,Long>
implements CargoDao{

	@Override
	@Transactional
	public List<Cargo> findByCargo(Cargo filtro) {
		List<Cargo> lista = new ArrayList<Cargo>();
		Criteria c = criaCriteria();
				
		if(StringUtils.isValid(filtro.getNome())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}