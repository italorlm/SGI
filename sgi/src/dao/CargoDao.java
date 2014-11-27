package dao;

import java.util.List;

import model.Cargo;

public interface CargoDao extends GenericDao<Cargo, Long>{
	
	public List<Cargo> findByCargo(Cargo filtro);	
	
}
