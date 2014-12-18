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
				"jdbc:postgresql://localhost:5432/corporativo", "postgres",
		"postgres");
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
	
	public Municipio buscaMunicipioId(Integer codigo) throws SQLException {
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
	
	public List<Municipio> buscaMunicipioEstado(Integer estado) throws SQLException {
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



