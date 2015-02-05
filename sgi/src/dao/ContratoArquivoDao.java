package dao;

import java.util.List;

import model.Contrato;
import model.ContratoArquivo;

public interface ContratoArquivoDao extends GenericDao<ContratoArquivo, Long>{	
	
	public List<ContratoArquivo> findByContrato(Contrato filtro);
	public ContratoArquivo findByNomeArquivo(String filtro);
}
