package model;

import java.util.List;

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
@Table(name="tb_cargo")
public class EntidadeCargo extends BaseModel {

	@SequenceGenerator(name="car",allocationSize=1, sequenceName="tb_cargo_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="car")
	@Id
	Long id;
	
	String nome;
	
	double salario;
	
	@ManyToOne
	@JoinColumn(name="entidade_id")
	Entidade entidade;
	
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
		
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
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
				+ ((entidade == null) ? 0 : entidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		EntidadeCargo other = (EntidadeCargo) obj;
		if (entidade == null) {
			if (other.entidade != null)
				return false;
		} else if (!entidade.equals(other.entidade))
			return false;
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
		if (Double.doubleToLongBits(salario) != Double
				.doubleToLongBits(other.salario))
			return false;
		return true;
	}
}
