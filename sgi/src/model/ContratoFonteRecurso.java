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
@Table(name="tb_contrato_fonte_recurso")
public class ContratoFonteRecurso extends BaseModel {

	@SequenceGenerator(name="cpa",allocationSize=1, sequenceName="tb_contrato_fonte_recurso_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cpa")
	@Id
	Long id;
	
	@ManyToOne
	@JoinColumn(name="fonte_recurso_id")
	FonteRecurso fonteRecurso;	
		
	@ManyToOne
	@JoinColumn(name="contrato_id")
	Contrato contrato;
	
	@Transient
	boolean editado = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public FonteRecurso getFonteRecurso() {
		return fonteRecurso;
	}

	public void setFonteRecurso(FonteRecurso fonteRecurso) {
		this.fonteRecurso = fonteRecurso;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
		ContratoFonteRecurso other = (ContratoFonteRecurso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
