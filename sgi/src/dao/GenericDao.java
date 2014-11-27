package dao;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Session;

public interface GenericDao <T,ID extends Serializable> {

	public Class<T>getObjectClass();
	public T findById(ID id);
	public T salvar(T object) ;
	public T atualizar(T object);
	public T excluir(T object);
	public List<T> todos(String ordem);
	public T salvarOuAtualizar(T object);
	public Session criaSession();
	public List<T> listByCriteriaDemanda(Integer startPage, Integer maxPage);
	public Integer count();
	
	
}
