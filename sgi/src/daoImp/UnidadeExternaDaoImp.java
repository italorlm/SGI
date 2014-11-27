package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.UnidadeExterna;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.UnidadeExternaDao;

@Component
public class UnidadeExternaDaoImp extends GenericDaoImp<UnidadeExterna,Long>
implements UnidadeExternaDao{

	@Override
	@Transactional
	public List<UnidadeExterna> findByExample(UnidadeExterna exemplo) {
		List<UnidadeExterna> lista = new ArrayList<UnidadeExterna>();
		Criteria c = criaCriteria();
		if(exemplo.getNome()!=null){
			c.add(Restrictions.ilike("nome",exemplo.getNome(),MatchMode.ANYWHERE));
		}
		c.addOrder(Order.asc("nome"));
		lista = c.list();
		return lista;
	}

}
