package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.TipoMaterialPermanente;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.TipoMaterialPermanenteDao;

@Component
public class TipoMaterialPermanenteDaoImp extends GenericDaoImp<TipoMaterialPermanente,Long>
implements TipoMaterialPermanenteDao{

	@Override
	@Transactional
	public List<TipoMaterialPermanente> findByTipoMaterialPermanente(TipoMaterialPermanente filtro) {
		List<TipoMaterialPermanente> lista = new ArrayList<TipoMaterialPermanente>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}