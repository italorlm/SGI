package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_fonte_recurso")
public class FonteRecurso extends BaseModel {
	
	@SequenceGenerator(name="c",allocationSize=1, sequenceName="tb_fonte_recurso_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="c")
	@Id
	Long id;
	
	String nome;
	
	@Column(name="valor_trimestre")
	String valorTrimestre;

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

	public String getValorTrimestre() {
		return valorTrimestre;
	}

	public void setValorTrimestre(String valorTrimestre) {
		this.valorTrimestre = valorTrimestre;
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
		FonteRecurso other = (FonteRecurso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
