package model;

import java.util.Date;

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
@Table(name="tb_contrato_aditivo")
public class ContratoAditivo extends BaseModel {

	@SequenceGenerator(name="cta",allocationSize=1, sequenceName="tb_contrato_aditivo_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cta")
	@Id
	Long id;
	
	String tipo;
	
	Date data;
	
	@ManyToOne
	@JoinColumn(name="contrato_id")
	Contrato contrato;
	
	//Atributo para auditoria
	@Transient
	boolean editado = false;
	
	public ContratoAditivo() {}
	
	public ContratoAditivo(Contrato contrato) {
		this.contrato = contrato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
		
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		result = prime * result
				+ ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ContratoAditivo other = (ContratoAditivo) obj;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
}
