package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import sgbd.Conexao;

public class Program {
	public static void main(String[] args) {
		try {
			Connection conn = new Conexao().conexaoDB("192.168.25.10", "dev", "senha", "5432", "desenv");
			
			if (conn != null) {
				System.out.println("Conex�o efetuada com sucesso!");
				System.out.println();

				String consulta = "SELECT id, nome from desenv.tb_teste";
				ResultSet rs = new Conexao().execSQL(consulta, conn);

				if (rs != null) {
					String nome;
					int cod;

					while (rs.next()) {
						cod = rs.getInt("id");
						nome = rs.getString("nome");
						System.out.printf("ID: %s - NOME: %s%n", cod, nome);
					}
					conn.close();
				} else {
					System.out.println("Problemas na execu��o da consulta");
				}
			} else {
				System.out.println("Problemas na conex�o com o servidor!");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
