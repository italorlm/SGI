package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_perfil")
public class Perfil extends BaseModel implements Serializable {
	
	/* Tipos de Perfis
	  	1 - USUARIO
	  	2 - ADMINISTRADOR FINANCEIRO
	  	3 - ADMINISTRADOR
	  	4 - COORDENADOR
	  	5 - SECRETARIO	  
	*/
	
	@SequenceGenerator(name="per",allocationSize=1, sequenceName="tb_perfil_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="per")
	@Id
	Long id;
	
	String nome;
	
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}