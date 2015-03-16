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
	
	String matricula;
	
	String telefone;
	
	String celular;
	
	String email;
	
	@ManyToOne
	@JoinColumn(name="setor_id")
	Setor setor;	
	
	@JoinColumn(name="entidade_id")
	Entidade entidade;
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	EntidadeCargo cargo;
	
	@Column(name="data_admissao")
	Date dataAdmissao;
	
	Integer sexo;
	
	String salario;
	
	public Funcionario() {}
	
	public Funcionario(String nome, String matricula, String email, 
			Date dataAdmissao, Integer sexo, String celular, String telefone) {
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.dataAdmissao = dataAdmissao;
		this.sexo = sexo;
		this.celular = celular;
		this.telefone = telefone;
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
		
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public EntidadeCargo getCargo() {
		return cargo;
	}

	public void setCargo(EntidadeCargo cargo) {
		this.cargo = cargo;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	
	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
