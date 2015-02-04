package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.StringUtils;
import dao.FuncionarioDao;

@Component
public class FuncionarioDaoImp extends GenericDaoImp<Funcionario,Long>
implements FuncionarioDao{

	@Override
	@Transactional
	public List<Funcionario> findByFuncionario(Funcionario filtro) {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		Criteria c = criaCriteria();
		
		if(StringUtils.isValid(filtro.getNome())) {
			c.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getSituacao()!=null) {
			c.add(Restrictions.eq("situacao", filtro.getSituacao()));
		}
		
		lista = c.list();
		return lista;
	}

}
