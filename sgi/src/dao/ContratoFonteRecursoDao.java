package dao;

import java.util.List;

import model.Contrato;
import model.ContratoFonteRecurso;

public interface ContratoFonteRecursoDao extends GenericDao<ContratoFonteRecurso, Long>{
	
	public List<ContratoFonteRecurso> findByContrato(Contrato filtro);
}
