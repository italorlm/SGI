package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();
//	public abstract void setDataCadastro(Date data);
//	public abstract Date getDataCadastro();

	
}
