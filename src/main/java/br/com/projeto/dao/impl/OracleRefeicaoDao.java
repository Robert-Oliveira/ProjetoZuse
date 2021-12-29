package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.model.Refeicao;

public class OracleRefeicaoDao {
	
	ConnectionManager dao = new ConnectionManager();
	
	public List<Refeicao> getAll(){ 

		List<Refeicao> lstRefeicao = new ArrayList<Refeicao>();

		try { PreparedStatement pstmt; 
		pstmt = dao.getConnection().prepareStatement("SELECT CD_REFEICAO, NM_REFEICAO FROM T_SHL_REFEICAO"); 

		ResultSet result = dao.getData(pstmt); 

		while (result.next()) { 

			Refeicao refeicao = new Refeicao(); 

			refeicao.setCdRefeicao(result.getInt("CD_REFEICAO"));
			refeicao.setNmRefeicao(result.getString("NM_REFEICAO")); 

			lstRefeicao.add(refeicao); 
		} 
		} catch(SQLException e) { 
			e.printStackTrace(); 
		} 
		return lstRefeicao; 
	}


}
