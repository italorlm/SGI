package model;

import java.io.Serializable;

public class Municipio implements Serializable{
	
	Integer id, idEstado, idMacroRegiao, idMicroRegiao, idPorte;
	
	String nome;
	
	public Municipio() {
		
	}
	
	public Municipio(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Municipio(Integer id, String nome, Integer idEstado, 
			Integer idMacroRegiao, Integer idMicroRegiao, Integer idPorte) {
		this.id = id;
		this.nome = nome;
		this.idEstado = idEstado;
		this.idMacroRegiao = idMacroRegiao;
		this.idMicroRegiao = idMicroRegiao;
		this.idPorte = idPorte;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdMacroRegiao() {
		return idMacroRegiao;
	}

	public void setIdMacroRegiao(Integer idMacroRegiao) {
		this.idMacroRegiao = idMacroRegiao;
	}

	public Integer getIdMicroRegiao() {
		return idMicroRegiao;
	}

	public void setIdMicroRegiao(Integer idMicroRegiao) {
		this.idMicroRegiao = idMicroRegiao;
	}

	public Integer getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(Integer idPorte) {
		this.idPorte = idPorte;
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
		Municipio other = (Municipio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
