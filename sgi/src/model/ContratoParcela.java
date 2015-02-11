package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="tb_contrato_parcela")
public class ContratoParcela extends BaseModel {

	@SequenceGenerator(name="cpa",allocationSize=1, sequenceName="tb_contrato_parcela_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cpa")
	@Id
	Long id;
	
	String parcela;
	
	@Column(name="mes_desembolso")
	String mesDesembolso;
	
	@Column(name="mes_pagamento")
	String mesPagamento;
	
	@Column(name="valor_parcial")
	String valorParcial;
	
	@ManyToOne
	@JoinColumn(name="contrato_id")
	Contrato contrato;
	
	@Transient
	boolean editado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}
	
	public String getMesDesembolso() {
		return mesDesembolso;
	}

	public void setMesDesembolso(String mesDesembolso) {
		this.mesDesembolso = mesDesembolso;
	}

	public String getMesPagamento() {
		return mesPagamento;
	}

	public void setMesPagamento(String mesPagamento) {
		this.mesPagamento = mesPagamento;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	public String getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(String valorParcial) {
		this.valorParcial = valorParcial;
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
		ContratoParcela other = (ContratoParcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
