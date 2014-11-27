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
@Table(name="tb_material_consumo")
public class MaterialConsumo extends BaseModel{
	
	@SequenceGenerator(name="map",allocationSize=1, sequenceName="tb_material_consumo_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="map")
	@Id
	Long id;
	
	String codigo;
	
	String descricao;
	
	String marca;
	
	Integer estoque;
	
	@ManyToOne
	@JoinColumn(name="unidade_cptr_id")
	UnidadeCptr unidadeCptr;
	
	@ManyToOne
	@JoinColumn(name="grupo_id")
	Grupo grupo;
		
	@ManyToOne
	@JoinColumn(name="subgrupo_id")
	SubGrupo subGrupo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public UnidadeCptr getUnidadeCptr() {
		return unidadeCptr;
	}

	public void setUnidadeCptr(UnidadeCptr unidadeCptr) {
		this.unidadeCptr = unidadeCptr;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
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
		MaterialConsumo other = (MaterialConsumo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
