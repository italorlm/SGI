package model;

import javax.persistence.Column;
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
@Table(name="tb_reajuste_salario")
public class ReajusteSalario extends BaseModel {

	@SequenceGenerator(name="rsa",allocationSize=1, sequenceName="tb_reajuste_salario_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rsa")
	@Id
	Long id;
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	Cargo cargo;
	
	@Column(name="percentual_reajuste")
	Integer percentualReajuste;
	
	@Column(name="ano_reajuste")
	Integer anoReajuste;
	
	@Column(name="salario_reajustado")
	double salarioReajustado;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Integer getPercentualReajuste() {
		return percentualReajuste;
	}

	public void setPercentualReajuste(Integer percentualReajuste) {
		this.percentualReajuste = percentualReajuste;
	}

	public Integer getAnoReajuste() {
		return anoReajuste;
	}

	public void setAnoReajuste(Integer anoReajuste) {
		this.anoReajuste = anoReajuste;
	}

	public double getSalarioReajustado() {
		return salarioReajustado;
	}

	public void setSalarioReajustado(double salarioReajustado) {
		this.salarioReajustado = salarioReajustado;
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
		ReajusteSalario other = (ReajusteSalario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
