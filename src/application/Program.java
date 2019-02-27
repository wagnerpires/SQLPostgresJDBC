package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import sgbd.Connect;

public class Program {
	public static void main(String[] args) {
		try {
			// Connect pgsql = new Connect("192.168.25.10", "dev", "int30int", "5432", "desenv");
			
			Connect pgsql = new Connect();
			pgsql.setServer("192.168.25.10");
			pgsql.setUser("dev");
			pgsql.setPass("int30int");
			pgsql.setPort("5432");
			pgsql.setDatabase("desenv");

			if (pgsql.conexaoDB() != null) {
				System.out.println(pgsql);
				System.out.println();

				pgsql.setSql("SELECT id, nome from desenv.tb_teste");
				ResultSet rs = pgsql.execSQL();

				if (rs != null) {
					String nome;
					int cod;

					while (rs.next()) {
						cod  = rs.getInt("id");
						nome = rs.getString("nome");
						System.out.printf("ID: %s - NOME: %s%n", cod, nome);
					}
					pgsql.CloseRs();
					pgsql.CloseConn();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Problemas na execução da consulta: SQLException: " + ex.getMessage());
		}
	}
}
