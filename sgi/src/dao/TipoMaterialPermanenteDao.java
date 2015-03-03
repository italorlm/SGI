package dao;

import java.util.List;

import model.EntidadeCargo;
import model.TipoMaterialPermanente;

public interface TipoMaterialPermanenteDao extends GenericDao<TipoMaterialPermanente, Long>{
	
	public List<TipoMaterialPermanente> findByTipoMaterialPermanente(TipoMaterialPermanente filtro);	
	
}
