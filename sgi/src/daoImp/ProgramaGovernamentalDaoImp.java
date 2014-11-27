package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.ProgramaGovernamental;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.ProgramaGovernamentalDao;

@Component
public class ProgramaGovernamentalDaoImp extends GenericDaoImp<ProgramaGovernamental,Long>
implements ProgramaGovernamentalDao{

	@Override
	@Transactional
	public List<ProgramaGovernamental> findByProgramaGovernamental(ProgramaGovernamental filtro) {
		List<ProgramaGovernamental> lista = new ArrayList<ProgramaGovernamental>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}