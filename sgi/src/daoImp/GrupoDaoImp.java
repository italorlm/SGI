package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.EntidadeCargo;
import model.Grupo;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.EntidadeCargoDao;
import dao.GrupoDao;

@Component
public class GrupoDaoImp extends GenericDaoImp<Grupo,Long>
implements GrupoDao{

	@Override
	@Transactional
	public List<Grupo> findByGrupo(Grupo filtro) {
		List<Grupo> lista = new ArrayList<Grupo>();
		Criteria c = criaCriteria();
		
		if(StringUtils.isValid(filtro.getNome())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}