package br.gov.msq.dao;

import java.sql.ResultSet;

import br.gov.msq.pojo.Aplicacao;

public class AplicacaoDAO extends Conexao {
	
	public void incluirAplicacoes(Aplicacao aplicacao) throws Exception {
		
		String sql = "";
		try {
			sql = "INSERT INTO \"Aplicacao\"(codigo_questionario, codigo_apoiador, codigo_pergunta, codigo_cnes, valor) VALUES (" +
					"'" + aplicacao.getQuestionario().getCodigo() + "'," +
					"'" + aplicacao.getApoiador().getCodigo() + "'," +
					"'" + aplicacao.getPergunta().getCodigo() + "'," +
					"'" + aplicacao.getCnes().getCodigoCnes() + "'," +
					"'" + aplicacao.getValor() + "'" +
					");";
		
		
			pS = conn.prepareStatement(sql);
			pS.execute();
		} catch (Exception e) {
			System.out.println(sql + " \r" + e.getMessage());
		}
		
		//if(!registroExistente(aplicacao)) {
		//}
		
	}
	
	private boolean registroExistente(Aplicacao aplicacao) throws Exception {
		String sqlCount = "SELECT COUNT(*) FROM \"Aplicacao\" WHERE codigo_questionario = '" + aplicacao.getQuestionario().getCodigo() + "'" +
				" AND codigo_apoiador = '" + aplicacao.getApoiador().getCodigo() + "'" +
				" AND codigo_pergunta = '" + aplicacao.getPergunta().getCodigo() + "'" +
				" AND valor = '" + aplicacao.getValor() + "'";
		pS = conn.prepareStatement(sqlCount);
		ResultSet rs = pS.executeQuery();
		
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		return count > 0;
	}
}