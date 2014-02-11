package br.gov.msq.dao;

import java.sql.SQLException;

import br.gov.msq.pojo.Apoiador;

public class ApoiadorDAO extends Conexao {
	
	public void incluirApoiadores(Apoiador ... apoiadores) throws SQLException {
		for(Apoiador apoiador : apoiadores) {
			pS = conn.prepareStatement("INSERT INTO \"Apoiador\"(id, nome) values (" + apoiador.getId() + ", '" + apoiador.getNome() + "');");
			pS.execute();
			System.out.println("Apoiador incluido:" + apoiador.getNome());
		}
	}
}