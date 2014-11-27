package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="tb_usuario_acesso")
public class UsuarioAcesso extends BaseModel implements Serializable  {

	@SequenceGenerator(name="uac",allocationSize=1, sequenceName="tb_usuario_acesso_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="uac")
	@Id
	Long id; 
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	Usuario usuario;
	
	Date data;
		
	public UsuarioAcesso(Usuario usuario) {
		this.usuario = usuario;
		this.data = new Date();
	}
	    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		UsuarioAcesso other = (UsuarioAcesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    
}
