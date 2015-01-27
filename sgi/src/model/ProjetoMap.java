package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="tb_projeto_map")
public class ProjetoMap extends BaseModel {

	@SequenceGenerator(name="pjm",allocationSize=1, sequenceName="tb_projeto_map_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pjm")
	@Id
	Long id;
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	Projeto projeto;
		
	@ManyToOne
	@JoinColumn(name="programa_map_id")
	ProgramaMap programaMap;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public ProgramaMap getProgramaMap() {
		return programaMap;
	}

	public void setProgramaMap(ProgramaMap programaMap) {
		this.programaMap = programaMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoMap other = (ProjetoMap) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
