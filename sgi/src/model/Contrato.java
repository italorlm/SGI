package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="tb_contrato")
public class Contrato extends BaseModel{
	
	@SequenceGenerator(name="cnt",allocationSize=1, sequenceName="tb_contrato_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cnt")
	@Id
	Long id;
	
	Integer tipo; //1-Contrato; 2-Conv�nio
	
	String executor;
	
	String contrato;
	
	String aditivo;
	
	String exercicio; //Integer?
	
	String numero;
	
	@ManyToOne
	@JoinColumn(name="fonte_recurso_id")
	FonteRecurso fonteRecurso;
		
	String valor;
	
	Integer parcelas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getAditivo() {
		return aditivo;
	}

	public void setAditivo(String aditivo) {
		this.aditivo = aditivo;
	}

	public String getExercicio() {
		return exercicio;
	}

	public void setExercicio(String exercicio) {
		this.exercicio = exercicio;
	}

	public FonteRecurso getFonteRecurso() {
		return fonteRecurso;
	}

	public void setFonteRecurso(FonteRecurso fonteRecurso) {
		this.fonteRecurso = fonteRecurso;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
