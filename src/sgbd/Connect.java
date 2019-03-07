package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DomainException;

public class Connect {

	private String server;
	private String user;
	private String pass;
	private String port;
	private String database;
	private String sql;
	private Connection conn;
	private ResultSet rs;

	public Connect(String server, String user, String pass, String port, String database) {
		this.server = server;
		this.user = user;
		this.pass = pass;
		this.port = port;
		this.database = database;
	}

	public Connect() {

	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String toString() {
		return "Connection [server=" + server + ", user=" + user + ", pass= ***" + ", port=" + port + ", database="
				+ database + "]";
	}

	public void conexaoDB() throws DomainException {
		String url = "jdbc:postgresql://" + server + ":" + port + "/" + database;
		String usuario = user;
		String senha = pass;

		try {
			conn = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException ex) {
			throw new DomainException("Problemas na conexão com o servidor: conexaoDB() " + ex.getMessage());
		}
	}

	public List<String> execSQL() throws DomainException {
		try {

			List<String> list = new ArrayList<>();

			Statement stm = (Statement) conn.createStatement();
			rs = stm.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					list.add(rs.getInt("id") + " - " + rs.getString("nome") + "%n");
				}
			}
			rs.close();
			conn.close();
			return list;
		} catch (SQLException ex) {
			throw new DomainException("Problemas na execução da consulta: ExecSQL() " + ex.getMessage());
		}
	}

	public void CloseConn() throws DomainException {
		try {
			conn.close();
		} catch (SQLException ex) {
			throw new DomainException("Problemas no fechamento da conexão: CloseConn()" + ex.getMessage());
		}
	}

	public void CloseRs() throws DomainException {
		try {
			rs.close();
		} catch (SQLException ex) {
			throw new DomainException("Problemas no fechamento do resultset: <CloseRs()> " + ex.getMessage());
		}
	}
}
