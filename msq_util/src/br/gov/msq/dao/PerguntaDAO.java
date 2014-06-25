package br.gov.msq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.gov.msq.pojo.Pergunta;

public class PerguntaDAO extends Conexao {
	
	public void incluirPerguntas(Pergunta pergunta) throws SQLException {
		String sql = "";
		if(!pergunta.getDescricao().equals("")) {
			 sql = "INSERT INTO msq.\"Pergunta\"(codigo, descricao) values ('" + pergunta.getCodigo() + "', '" + pergunta.getDescricao() + "');";
		} else {
			sql = "INSERT INTO \"Pergunta\"(codigo) values ('" + pergunta.getCodigo() + "');";
		}
		System.out.println(sql);
		pS = conn.prepareStatement(sql);
		pS.execute();
	}
	
	public boolean perguntaExistente(String codigoPergunta) throws Exception {
		String sqlCount = "SELECT COUNT(*) FROM msq.\"Pergunta\" WHERE codigo = '" + codigoPergunta + "';";
		pS = conn.prepareStatement(sqlCount);
		ResultSet rs = pS.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		return count > 0;
	}
}