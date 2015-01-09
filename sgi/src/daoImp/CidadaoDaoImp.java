package daoImp;


import java.util.ArrayList;
import java.util.List;

import model.Cidadao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.CidadaoDao;

@Component
public class CidadaoDaoImp extends GenericDaoImp<Cidadao,Long>
implements CidadaoDao{

	@Override
	@Transactional
	public List<Cidadao> findByExample(Cidadao filtro) {
		List<Cidadao> lista = new ArrayList<Cidadao>();
		Criteria c = criaCriteria();
		
		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
			c.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));					
		}
		
		if(filtro.getCpf()!=null && !(filtro.getCpf().isEmpty())){
			c.add(Restrictions.eq("cpf", filtro.getCpf()));					
		}
		
		lista = c.list();
		return lista;
	}
	
	@Override
	@Transactional
	public Cidadao findByCpf(String filtro) {
		Cidadao cidadao = new Cidadao();
		Criteria c = criaCriteria();
		
		if(filtro!=null) {
			c.add(Restrictions.eq("cpf", filtro));
		}
		
		cidadao = (Cidadao) c.uniqueResult();
		
		if(cidadao!=null)
			return cidadao;
		else
			return null;
	}
}