package model;

import java.util.Date;

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
@Table(name="tb_funcionario")
public class Funcionario extends BaseModel{
	
	@SequenceGenerator(name="map",allocationSize=1, sequenceName="tb_funcionario_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="map")
	@Id
	Long id;
	
	String nome;
	
	Integer situacao;
	
	@ManyToOne
	@JoinColumn(name="unidade_cptr_id")
	UnidadeCptr unidadeCptr;
	
	String telefone;
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	Cargo cargo;
	
	@Column(name="data_admissao")
	Date dataAdmissao;

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

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public UnidadeCptr getUnidadeCptr() {
		return unidadeCptr;
	}

	public void setUnidadeCptr(UnidadeCptr unidadeCptr) {
		this.unidadeCptr = unidadeCptr;
	}
	
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
