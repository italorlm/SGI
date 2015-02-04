package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Municipio;
import model.Uf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dao.DaoCrp;

@Component
@Scope("globalSession")
public class MunicipioUfController {
	
	transient DaoCrp crp = new DaoCrp();
	
	List<SelectItem> municipios = new ArrayList<SelectItem>();	
	List<SelectItem> ufs = new ArrayList<SelectItem>();
	
	public List<SelectItem> getMunicipios() throws SQLException {
		if(municipios.size()==0) 
			for(Municipio m : crp.listaMunicipios()) {
				municipios.add(new SelectItem(new Long(m.getId()), m.getNome().toUpperCase()));
			}		
		return municipios;
	}

	public void setMunicipios(List<SelectItem> municipios) {
		this.municipios = municipios;
	}
		
	public List<SelectItem> getUfs() throws SQLException {
		if(ufs.size()==0) 
			for(Uf u : crp.listaUf()) {
				ufs.add(new SelectItem(new Long(u.getId()), u.getNome()));
			}		
		return ufs;
	}

	public void setUfs(List<SelectItem> ufs) {
		this.ufs = ufs;
	}

	public String buscarPorMunicipio(Integer codigoMunicipio) throws SQLException{
		if(codigoMunicipio!=0) {
			Municipio m = crp.buscarMunicipioPorId(codigoMunicipio);
			return m.getNome().toUpperCase();
		} else {
			return "";
		}
	}
	
	public List<Municipio> buscarMunicipioPorEstado(Integer codigoEstado) throws SQLException {
		return crp.buscarMunicipioPorEstado(codigoEstado);
	}
	
	public List<SelectItem> selectItemsMunicipioPorEstado(Integer codigoEstado) throws SQLException {
		return municipios(crp.buscarMunicipioPorEstado(codigoEstado));
	}
	
	public List<SelectItem> municipios(List<Municipio> lista) throws SQLException {
		if(lista.size()!=0) 
			for(Municipio m : lista) {
				municipios.add(new SelectItem(new Long(m.getId()), m.getNome().toUpperCase()));
			}		
		return municipios;
	}
	
	public List<Municipio> buscarMunicipioPorCodigoIbge(String codIbge) throws SQLException {
		return crp.buscarMunicipioPorCodigoIbge(codIbge);
	}
}
