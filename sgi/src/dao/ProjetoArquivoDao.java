package dao;

import java.util.List;

import model.Projeto;
import model.ProjetoArquivo;

public interface ProjetoArquivoDao extends GenericDao<ProjetoArquivo, Long>{	
	
	public List<ProjetoArquivo> findByArquivo(Projeto filtro);
	public ProjetoArquivo findByNomeArquivo(String filtro);
}
