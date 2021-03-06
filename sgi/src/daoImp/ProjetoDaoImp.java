package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.EntidadeCargo;
import model.Programa;
import model.Projeto;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.EntidadeCargoDao;
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
		
		if(StringUtils.isValid(filtro.getNome())){
			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		}
		
		lista = c.list();
		return lista;		
	}



}