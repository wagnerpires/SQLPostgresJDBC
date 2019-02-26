/* Classe para conexão com banco de dados Postgresql
 * Data criação: 20/02/2019 - 16:43hs
 * Data alteração: 24/02/2019
 * Autor: Zulu
 */

package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	public Connection conexaoDB(String server, String user, String pass, String port, String database) {
		String url = "jdbc:postgresql://" + server + ":" + port + "/" + database;
		String usuario = user;
		String senha = pass;
		try {
			// Class.forName("org.postgresql.Driver").newInstance(); // The method newInstance() from the type Class is deprecated since version 9
			Connection conn = DriverManager.getConnection(url, usuario, senha);
			return conn;
		} catch (SQLException ex) {
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		} catch (Exception e) {
			System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);
			return null;
		}
	}

	public ResultSet execSQL(String SQL, Connection conn) {  // throws SQLException {
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(SQL);
			conn.close();
			return rs;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			return null;
		}
	}
}