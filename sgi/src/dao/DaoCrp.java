package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Municipio;
import model.Uf;

public class DaoCrp {	
	
	static Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:postgresql://ciclopes:5432/corporativo", "guest",
		"setasguestpwd");
	}

	public List<Municipio> listaMunicipios() throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Municipio> municipios = new ArrayList<Municipio>();
		try {

			String sql = "select id, nome from municipio"
				+ " order by nome ";
			con = getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				municipios.add(new Municipio(rs.getInt("id"), 
						rs.getString("nome")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}
		return municipios;
	}
	
	public Municipio buscarMunicipioPorId(Integer codigo) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Municipio municipio = null;
		try {
			String sql = "select id, nome from municipio"
				+ " where id = ? ";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeQuery();
			while (rs.next()) {
				municipio = new Municipio(rs.getInt("id"), rs.getString("nome"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}		
		return municipio;
	}
	
	public List<Municipio> buscarMunicipioPorEstado(Integer estado) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Municipio> municipios = new ArrayList<Municipio>();
		try {
			String sql = "select id, nome from municipio"
				+ " where idestado = ? order by nome";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, estado);
			rs = pst.executeQuery();
			while (rs.next()) {
				municipios.add(new Municipio(rs.getInt("id"), rs.getString("nome")));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}		
		return municipios;
	}
	
	public List<Municipio> buscarMunicipioPorCodigoIbge(String codIbge) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Municipio> municipios = new ArrayList<Municipio>();
		try {
			String sql = "select id, nome, idestado, codigo_macreg,"
					+ " codigo_micreg, codigo_por from municipio"
				+ " where codigoibge = ? order by nome";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, codIbge);
			rs = pst.executeQuery();
			while (rs.next()) {
				municipios.add(new Municipio(rs.getInt("id"), rs.getString("nome"), rs.getInt("idestado"), 
						rs.getInt("codigo_macreg"), rs.getInt("codigo_micreg"), rs.getInt("codigo_por")));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}		
		return municipios;
	}
	
	public List<Uf> listaUf() throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Uf> ufs = new ArrayList<Uf>();
		try {

			String sql = "select id, sigla from estado"
				+ " order by sigla ";
			con = getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				ufs.add(new Uf(rs.getInt("id"), rs
						.getString("sigla")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}
		return ufs;
	}
	
}



