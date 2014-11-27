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
	public List<Cidadao> findByNome(String nome) {
		List<Cidadao> lista = new ArrayList<Cidadao>();
		Criteria c = criaCriteria();
		if(StringUtils.isValid(nome)){
			lista = (List<Cidadao>)c.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE))
					.addOrder(Order.asc("nome"))
					.list();
		}

		return lista;
	}

	@Override
	@Transactional
	public List<Cidadao> findByCpf(String cpf) {
		List<Cidadao> lista = new ArrayList<Cidadao>();
		Criteria c = criaCriteria();
		if(StringUtils.isValid(cpf)){
			lista = (List<Cidadao>)c.add(Restrictions.eq("cpf", cpf))
					.addOrder(Order.asc("nome"))
					.list();
		}

		return lista;
	}



}