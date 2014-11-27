package daoImp;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioDao;

@Component
public class UsuarioDaoImp extends GenericDaoImp<Usuario,Long>
implements UsuarioDao{

	@Override
	@Transactional
	public Usuario login(Usuario usuario) {
		Usuario usuarioLogado = null;
		Criteria c = criaCriteria();
		c.add(Restrictions.eq("login",usuario.getLogin()));
		c.add(Restrictions.eq("senha",usuario.getSenha()));
		usuarioLogado = (Usuario)c.uniqueResult();
		return usuarioLogado;
	}

//	@Override
//	@Transactional
//	public List<Usuario> findByExample(Usuario filtro) {
//		List<Usuario> lista = new ArrayList<Usuario>();
//		Criteria c = criaCriteria();
//		if(filtro.getNome()!=null && !(filtro.getNome().isEmpty())){
//			c.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
//		}
//		
//		if(filtro.getPerfil()!=null){
//			c.add(Restrictions.eq("perfil",filtro.getPerfil()));
//		}
//		
//		if(filtro.getPolo()!=null){
//			c.add(Restrictions.eq("polo",filtro.getPolo()));
//		}
//		
//		lista = c.addOrder(Order.asc("nome")).list();
//		return lista;
//	}
	
	

}
