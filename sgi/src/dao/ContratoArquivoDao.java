package dao;

import java.util.List;

import model.Contrato;
import model.ContratoArquivo;

public interface ContratoArquivoDao extends GenericDao<ContratoArquivo, Long>{	
	
	public List<ContratoArquivo> findByArquivo(Contrato filtro);
}
