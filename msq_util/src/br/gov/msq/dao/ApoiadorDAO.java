package br.gov.msq.dao;

import java.sql.SQLException;

import br.gov.msq.pojo.Apoiador;

public class ApoiadorDAO extends Conexao {
	
	public void incluirApoiadores(Apoiador apoiador) throws SQLException {
		String sql = "INSERT INTO \"Apoiador\"(codigo, nome) values ('" + apoiador.getCodigo() + "', '" + apoiador.getNome() + "');";
		System.out.println(sql);
		pS = conn.prepareStatement(sql);
		pS.execute();
		System.out.println("Apoiador incluido:" + apoiador.getNome());
	}
}