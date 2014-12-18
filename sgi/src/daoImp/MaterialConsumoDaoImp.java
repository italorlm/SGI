package daoImp;

import java.util.ArrayList;
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
	public List<MaterialConsumo> findByExample(MaterialConsumo filtro) {
		List<MaterialConsumo> lista = new ArrayList<MaterialConsumo>();
		Criteria c = criaCriteria();
		
		if(filtro.getCodigo() != null && !(filtro.getCodigo().isEmpty())){
			c.add(Restrictions.ilike("codigo", filtro.getCodigo(), MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;
	}

}
