package br.gov.msq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/qualifar";
	private static final String USER = "sa_ecar";
	private static final String PASSWD = "sa_ecar";
	
	protected Connection conn;
	protected PreparedStatement pS;
	
	public Conexao() {
		try {
			this.conn = createConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection createConnection() throws Exception {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL+"?user="+USER+"&password="+PASSWD+"&search_path=msq");
		return conn;
	}
}