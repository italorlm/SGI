package dao;

import java.util.List;

import model.Cargo;
import model.ProgramaGovernamental;

public interface ProgramaGovernamentalDao extends GenericDao<ProgramaGovernamental, Long>{
	
	public List<ProgramaGovernamental> findByProgramaGovernamental(ProgramaGovernamental filtro);	
	
}
