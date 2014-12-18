package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.UnidadeCptr;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.RESyntaxException;

import dao.UnidadeCptrDao;

@Component
@Transactional
public class UnidadeCptrDaoImp extends GenericDaoImp<UnidadeCptr,Long>
implements UnidadeCptrDao{

	@Override
	public List<UnidadeCptr> findByExample(UnidadeCptr filtro) {
		List<UnidadeCptr> lista = new ArrayList<UnidadeCptr>();
		Criteria c = criaCriteria();
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		c.addOrder(Order.asc("nome"));
		lista = c.list();
		return lista;
	}

}
