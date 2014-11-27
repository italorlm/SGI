package dao;

import java.util.List;

import model.Contrato;
import model.ContratoParcela;

public interface ContratoParcelaDao extends GenericDao<ContratoParcela, Long>{
	
	public List<ContratoParcela> findByParcela(Contrato filtro);
}
