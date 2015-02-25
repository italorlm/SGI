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

@Entity
@Table(name="tb_material_permanente")
public class MaterialPermanente extends BaseModel{
	
	@SequenceGenerator(name="map",allocationSize=1, sequenceName="tb_material_permanente_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="map")
	@Id
	Long id;
	
	String descricao;
	
	@Column(name="tombo_atual")
	String tomboAtual;
	
	@Column(name="tombo_anterior")
	String tomboAnterior;
	
	@Column(name="estado_conservacao")
	Integer estadoConservacao;
	
	@ManyToOne
	@JoinColumn(name="setor_id")
	Setor setor;
	
	@ManyToOne
	@JoinColumn(name="tipo_mat_perm_id")
	TipoMaterialPermanente tipoMaterialPermanente;
	
	String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTomboAtual() {
		return tomboAtual;
	}

	public void setTomboAtual(String tomboAtual) {
		this.tomboAtual = tomboAtual;
	}

	public String getTomboAnterior() {
		return tomboAnterior;
	}

	public void setTomboAnterior(String tomboAnterior) {
		this.tomboAnterior = tomboAnterior;
	}

	public Integer getEstadoConservacao() {
		return estadoConservacao;
	}

	public void setEstadoConservacao(Integer estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public TipoMaterialPermanente getTipoMaterialPermanente() {
		return tipoMaterialPermanente;
	}

	public void setTipoMaterialPermanente(
			TipoMaterialPermanente tipoMaterialPermanente) {
		this.tipoMaterialPermanente = tipoMaterialPermanente;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		MaterialPermanente other = (MaterialPermanente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
