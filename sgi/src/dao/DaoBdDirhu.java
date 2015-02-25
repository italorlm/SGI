package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.Municipio;
import model.Uf;

public class DaoBdDirhu {	
	
	static Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:jtds:sqlserver://localhost:1433/BDDIRHU;namedpipe=true", "postgres",
				"postgres");
	}

	public List<Funcionario> buscarFuncionarios() throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {

			String sql = "select nm_fun as nome, nr_mat as matricula, fun_email as email from tbfuncionario "; 
								
			con = getConnection();
			pst = con.prepareStatement(sql);			
			rs = pst.executeQuery();
			while (rs.next()) {
				funcionarios.add(new Funcionario(Normalizer.normalize(rs.getString("nome"), Normalizer.Form.NFD)
						.replaceAll("[^\\p{ASCII}]", ""), rs.getString("matricula"), rs.getString("email")));
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
				
		return funcionarios;
	}
}



