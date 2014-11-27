package dao;

import java.util.List;

import model.Cidadao;

public interface CidadaoDao extends GenericDao<Cidadao, Long>{
	
	public List<Cidadao> findByNome(String nome);
	public List<Cidadao> findByCpf(String cpf);
	
}
