package dao;

import java.util.List;

import model.Cargo;
import model.TipoMaterialPermanente;

public interface TipoMaterialPermanenteDao extends GenericDao<TipoMaterialPermanente, Long>{
	
	public List<TipoMaterialPermanente> findByTipoMaterialPermanente(TipoMaterialPermanente filtro);	
	
}
