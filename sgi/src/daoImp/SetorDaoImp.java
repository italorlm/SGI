package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.Setor;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.SetorDao;

@Component
@Transactional
public class SetorDaoImp extends GenericDaoImp<Setor,Long>
implements SetorDao{

	@Override
	public List<Setor> findBySetor(Setor filtro) {
		List<Setor> lista = new ArrayList<Setor>();
		Criteria c = criaCriteria();
		
		if(StringUtils.isValid(filtro.getNome())){
			c.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		c.addOrder(Order.asc("nome"));
		lista = c.list();
		return lista;
	}

}
