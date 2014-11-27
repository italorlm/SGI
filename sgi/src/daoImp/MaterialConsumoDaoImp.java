package daoImp;

import java.util.List;

import model.MaterialConsumo;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.MaterialConsumoDao;
@Component
@Transactional
public class MaterialConsumoDaoImp extends GenericDaoImp<MaterialConsumo, Long>
implements MaterialConsumoDao{

	@Override
	public List<MaterialConsumo> findByExample(MaterialConsumo exemplo) {
		List<MaterialConsumo> lista = null;
		Criteria c = criaCriteria();
		if(StringUtils.isValid(exemplo.getCodigo())){
			c.add(Restrictions.ilike("codigo",exemplo.getCodigo(),MatchMode.ANYWHERE));
		}
		lista = c.list();
		return lista;
	}

}
