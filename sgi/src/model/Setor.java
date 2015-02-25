package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "tb_setor")
public class Setor extends BaseModel {

	@SequenceGenerator(name = "set", allocationSize = 1, sequenceName = "tb_setor_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set")
	@Id
	Long id;

	@Column(name="id_corporativo")
	Integer idCorporativo;
	
	String nome;

	String telefone;

	String endereco;

	String responsavel;
	
	@Column(name="und_externa")
	boolean unidadeExterna;

	public Setor() {}
	
	public Setor(Integer idCorporativo, String nome, String telefone, String responsavel) {
		this.idCorporativo = idCorporativo;
		this.nome = nome;
		this.telefone = telefone;
		this.responsavel = responsavel;
	}

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
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public boolean isUnidadeExterna() {
		return unidadeExterna;
	}

	public void setUnidadeExterna(boolean unidadeExterna) {
		this.unidadeExterna = unidadeExterna;
	}

	public Integer getIdCorporativo() {
		return idCorporativo;
	}

	public void setIdCorporativo(Integer idCorporativo) {
		this.idCorporativo = idCorporativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idCorporativo == null) ? 0 : idCorporativo.hashCode());
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
		Setor other = (Setor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCorporativo == null) {
			if (other.idCorporativo != null)
				return false;
		} else if (!idCorporativo.equals(other.idCorporativo))
			return false;
		return true;
	}

	

}
