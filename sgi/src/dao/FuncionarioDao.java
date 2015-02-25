package dao;

import java.util.List;

import model.Funcionario;

public interface FuncionarioDao extends GenericDao<Funcionario,Long> {
	
	public List<Funcionario> findByFuncionario(Funcionario filtro);
	public Funcionario findByMatricula(Funcionario filtro);
}
