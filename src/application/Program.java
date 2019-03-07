package application;

import java.util.ArrayList;
import java.util.List;

import exceptions.DomainException;
import sgbd.Connect;

public class Program {
	public static void main(String[] args) {
		try {

			List<String> list = new ArrayList<>();
			
			// Connect pgsql = new Connect("192.168.25.10", "dev", "int30int", "5432", "desenv");
			Connect pgsql = new Connect();
			pgsql.setServer("192.168.25.10");
			pgsql.setUser("dev");
			pgsql.setPass("int30int");
			pgsql.setPort("5432");
			pgsql.setDatabase("desenv");
			pgsql.conexaoDB();

			System.out.println(pgsql);
			System.out.println();

			pgsql.setSql("SELECT id, nome from desenv.tb_teste");

			list = pgsql.execSQL();

			for (String lst : list) {
				System.out.printf(lst);
			}
		} catch (DomainException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
