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
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="tb_cidadao")
public class Cidadao extends BaseModel {

	@SequenceGenerator(name="cid",allocationSize=1, sequenceName="tb_cidadao_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cid")
	@Id
	Long id;
	
	String nome;
	
	String cpf;
	
	@Column(name="data_nasc")
	Date dataNascimento;
	
	Integer sexo;
	
	String rg;
	
	@Column(name="orgao_emiss_rg")
	String orgaoEmissorRg;
	
	@Column(name="data_emiss_rg")
	Date dataEmissaoRg;
	
	@Column(name="uf_nasc")
	Integer ufNascimento;
	
	Integer naturalidade;
		
	Integer etnia;
	
	@Column(name="fone_res")
	String telefoneResidencial;
	
	@Column(name="fone_cel")
	String telefoneCelular;
	
	String email;
	
	@Column(name="nome_pai")
	String nomePai;
	
	@Column(name="nome_mae")
	String nomeMae;
	
	String cep;
	
	String endereco;
	
	Integer uf;
	
	Integer municipio;
	
	String bairro;
	
	String complemento;
	
	@Column(name="num_residencia")
	String numeroResidencia;

	Integer escolaridade;
	
	@Column(name="frequenta_escola")
	Integer frequentaEscola;
	
	@Column(name="nome_escola")
	String nomeEscola;

	@Column(name="qtd_dependente")
	String qtdDependentes;
	
	@ManyToOne
	@JoinColumn(name="programa_governo")
	ProgramaGovernamental programaGovernamental;
	
	@Column(name="ja_trabalhou")
	Integer jaTrabalhou;
	
	@Column(name="ultima_empresa")
	String ultimaEmpresa;
	
	@Column(name="num_cpts")
	String numeroCpts;
	
	@Column(name="serie_cpts")
	String serieCpts;
	
	@Column(name="data_emiss_cpts")
	Date dataEmissaoCpts;
	
	@Column(name="pis_pasep")
	String pispasep;
	
	@Column(name="nis")
	String nis;
	
	@Column(name="numero_titulo")
	String numeroTitulo;
	
	@Column(name="zona_titulo")
	String zonaTitulo;
	
	@Column(name="secao_titulo")
	String secaoTitulo;
	
	@Transient
	boolean cepValido = true;
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public Integer getUfNascimento() {
		return ufNascimento;
	}

	public void setUfNascimento(Integer ufNascimento) {
		this.ufNascimento = ufNascimento;
	}

	public Integer getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Integer naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getEtnia() {
		return etnia;
	}

	public void setEtnia(Integer etnia) {
		this.etnia = etnia;
	}

	public Integer getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Integer escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Integer getUf() {
		return uf;
	}

	public void setUf(Integer uf) {
		this.uf = uf;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getFrequentaEscola() {
		return frequentaEscola;
	}

	public void setFrequentaEscola(Integer frequentaEscola) {
		this.frequentaEscola = frequentaEscola;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public String getQtdDependentes() {
		return qtdDependentes;
	}

	public void setQtdDependentes(String qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}

	public ProgramaGovernamental getProgramaGovernamental() {
		return programaGovernamental;
	}

	public void setProgramaGovernamental(ProgramaGovernamental programaGovernamental) {
		this.programaGovernamental = programaGovernamental;
	}

	public Integer getJaTrabalhou() {
		return jaTrabalhou;
	}

	public void setJaTrabalhou(Integer jaTrabalhou) {
		this.jaTrabalhou = jaTrabalhou;
	}

	public String getUltimaEmpresa() {
		return ultimaEmpresa;
	}

	public void setUltimaEmpresa(String ultimaEmpresa) {
		this.ultimaEmpresa = ultimaEmpresa;
	}

	public String getNumeroCpts() {
		return numeroCpts;
	}

	public void setNumeroCpts(String numeroCpts) {
		this.numeroCpts = numeroCpts;
	}

	public String getSerieCpts() {
		return serieCpts;
	}

	public void setSerieCpts(String serieCpts) {
		this.serieCpts = serieCpts;
	}

	public Date getDataEmissaoCpts() {
		return dataEmissaoCpts;
	}

	public void setDataEmissaoCpts(Date dataEmissaoCpts) {
		this.dataEmissaoCpts = dataEmissaoCpts;
	}

	public String getPispasep() {
		return pispasep;
	}

	public void setPispasep(String pispasep) {
		this.pispasep = pispasep;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public String getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

	public String getZonaTitulo() {
		return zonaTitulo;
	}

	public void setZonaTitulo(String zonaTitulo) {
		this.zonaTitulo = zonaTitulo;
	}

	public String getSecaoTitulo() {
		return secaoTitulo;
	}

	public void setSecaoTitulo(String secaoTitulo) {
		this.secaoTitulo = secaoTitulo;
	}
	
	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}
	
	public boolean isCepValido() {
		return cepValido;
	}

	public void setCepValido(boolean cepValido) {
		this.cepValido = cepValido;
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
		Cidadao other = (Cidadao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
