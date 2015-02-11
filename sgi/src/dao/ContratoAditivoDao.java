package dao;

import java.util.List;

import model.Contrato;
import model.ContratoAditivo;
import model.ContratoParcela;

public interface ContratoAditivoDao extends GenericDao<ContratoAditivo, Long>{
	
	public List<ContratoAditivo> findByContrato(Contrato filtro);
}
