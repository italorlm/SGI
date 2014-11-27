package daoImp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.GenericDao;
import org.hibernate.criterion.Projections;

public class GenericDaoImp<T,ID extends Serializable> implements GenericDao<T, ID> {
	@PersistenceContext
	private EntityManager entityManager;
	private final Class<T> classePersistente;

	public GenericDaoImp(){
		this.classePersistente = (Class<T>)
		((ParameterizedType)getClass().getGenericSuperclass())
		.getActualTypeArguments()[0];
	}

	public Class<T> getClassePersistente() {return classePersistente;}
	
	protected final Criteria criaCriteria() {
		return criaSession().createCriteria(getClassePersistente());
	}

	public final Session criaSession() {
		return  (Session)getEntityManager().getDelegate();
	}
	public EntityManager getEntityManager() {return entityManager;}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Class<T> getObjectClass() {return this.classePersistente;}

	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public T salvar(T object)  {
		
		try {
			getEntityManager().persist(object);
			//getEntityManager().clear();
		}catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}
	
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public T salvarOuAtualizar(T object)  {
		
		try {
			criaSession().saveOrUpdate(object);
		}catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public List<T> todos(String ordem){ 
		StringBuffer queryS = new StringBuffer("SELECT obj FROM "+classePersistente.getSimpleName()+" obj "); 
		if(ordem!=null){
			queryS.append("order by "+ordem);
		}
		Query query = getEntityManager().createQuery(queryS.toString()); 
		return query.getResultList(); 
	}

	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public T atualizar(T object) {
		getEntityManager().merge(object);
		return null;
	}

	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public T excluir(T object) {
		try {
			object = getEntityManager().merge(object);
			getEntityManager().remove(object);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public T findById(ID id) {
		return getEntityManager().find(getClassePersistente(), id);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public List<T> listByCriteriaDemanda(Integer startPage, Integer maxPage){

        List<T> list = new ArrayList<T>();

        try {

                // getHibernate().beginTransaction();

                 Criteria criteria = criaSession().createCriteria(getClassePersistente());

                 criteria.setFirstResult(startPage);

                 criteria.setMaxResults(maxPage);

                 list = criteria.list();

                // getHibernate().commit();

        } catch (Exception e) {

               //  getHibernate().rollback();

                 e.printStackTrace();

         } finally {

                // getHibernate.close();

        }       

        return list;

}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public Integer count(){

        Integer size = 0;

        try {

                  //getHibernate().beginTransaction();
                  Criteria criteria = criaSession().createCriteria(getClassePersistente());
                  criteria.setProjection(Projections.rowCount());
                  size = (Integer) criteria.uniqueResult();
                //  getHibernate().commit();

        } catch (Exception e) {

                 // getHibernate().rollback();

                  e.printStackTrace();

        } finally {

                 // getHibernate().close();

        }

                  return size;

}

	

}
