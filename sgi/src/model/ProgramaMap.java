package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="tb_programa_map")
public class ProgramaMap extends BaseModel {
	
	@SequenceGenerator(name="prm",allocationSize=1, sequenceName="tb_programa_map_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="prm")
	@Id
	Long id;
	
	String nome;
	
	@ManyToOne
	@JoinColumn(name="programa_id")
	Programa programa;
	
	@Transient
	boolean check;
	
	//Atributo para auditoria
	@Transient
	boolean editado = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public boolean isEditado() {
		return editado;
	}

	public void editado() {
		this.editado = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((programa == null) ? 0 : programa.hashCode());
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
		ProgramaMap other = (ProgramaMap) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (programa == null) {
			if (other.programa != null)
				return false;
		} else if (!programa.equals(other.programa))
			return false;
		return true;
	}	
}
