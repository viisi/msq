package br.gov.msq.dao;

import java.sql.SQLException;

import br.gov.msq.pojo.Questionario;

public class QuestionarioDAO extends Conexao {
	
	public void incluirQuestionario(Questionario questionario) throws SQLException {
		String sql = "INSERT INTO msq.\"Questionario\"(codigo, descricao) values ('" + questionario.getCodigo() + "', '" + questionario.getDescricao() + "');";
		System.out.println(sql);
		pS = conn.prepareStatement(sql);
		pS.execute();
	}
}