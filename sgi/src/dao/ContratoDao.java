package dao;

import java.util.List;

import model.Contrato;

public interface ContratoDao extends GenericDao<Contrato,Long> {
	
	public List<Contrato> findByContrato(Contrato filtro);

}
