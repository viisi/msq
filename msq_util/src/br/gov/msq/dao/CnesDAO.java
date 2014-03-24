package br.gov.msq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.gov.msq.pojo.Cnes;

public class CnesDAO extends Conexao {
	
	public void incluirCnes(Cnes cnes) throws SQLException {
		String sqlCount = "SELECT COUNT(*) FROM \"Cnes\" WHERE NUMERO = '" + cnes.getCodigoCnes() + "';";
		pS = conn.prepareStatement(sqlCount);
		ResultSet rs = pS.executeQuery();
		
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		if(count > 0) {
			System.out.println("CNES existente=" + cnes.getCodigoCnes());
		} else {
			String sql = "INSERT INTO \"Cnes\"(numero) values ('" + cnes.getCodigoCnes() + "');";
			//System.out.println(sql);
			pS = conn.prepareStatement(sql);
			pS.execute();
		}
		
	}
}