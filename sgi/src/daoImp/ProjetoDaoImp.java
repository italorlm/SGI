package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.Programa;
import model.Projeto;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.CargoDao;
import dao.ProgramaDao;
import dao.ProjetoDao;

@Component
public class ProjetoDaoImp extends GenericDaoImp<Projeto,Long>
implements ProjetoDao{

	@Override
	@Transactional
	public List<Projeto> findByProjeto(Projeto filtro) {
		List<Projeto> lista = new ArrayList<Projeto>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}