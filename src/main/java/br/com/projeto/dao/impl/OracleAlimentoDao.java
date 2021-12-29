package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.AlimentoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Alimento;


public class OracleAlimentoDao implements AlimentoDao{
	
	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(Alimento alimento)throws DBException {

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_ALIMENTO (CD_ALIMENTO, NM_ALIMENTO, TP_MEDIDA, "
					+ "QT_ALIMENTO, CALORIAS, QT_CARBO, QT_PROTEINA, QT_GORDURA) VALUES (SEQ_ID_ALIMENTO.NEXTVAL,?,?,?,?,?,?,?)");

			pstmt.setString(1, alimento.getNmAlimento());
			pstmt.setString(2, alimento.getTpMedida());
			pstmt.setInt(3,alimento.getQtAlimento()); 
			pstmt.setInt(4,alimento.getCalorias());
			pstmt.setDouble(5,alimento.getQtCarbo()); 
			pstmt.setDouble(6,alimento.getQtProteina());
			pstmt.setDouble(7,alimento.getQtGordura()); 
			dao.executeCommand(pstmt);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Alimento alimento)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE INTO T_SHL_ALIMENTO SET CD_ALIMENTO=?, NM_ALIMENTO=?, TP_MEDIDA=?, "
					+ "QT_ALIMENTO=?, CALORIAS=?, QT_CARBO=?, QT_PROTEINA=?, QT_GORDURA=?)");

			pstmt.setInt(1,alimento.getCdAlimento()); 
			pstmt.setString(2, alimento.getNmAlimento());
			pstmt.setString(3, alimento.getTpMedida());
			pstmt.setInt(4,alimento.getQtAlimento()); 
			pstmt.setInt(5,alimento.getCalorias());
			pstmt.setDouble(6,alimento.getQtCarbo()); 
			pstmt.setDouble(7,alimento.getQtProteina());
			pstmt.setDouble(8,alimento.getQtGordura()); 
			dao.executeCommand(pstmt);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Alimento getById(int id) {
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_ALIMENTO WHERE CD_ALIMENTO = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Alimento alimento = new Alimento();
				
				alimento.setNmAlimento(result.getString("NM_ALIMENTO"));
				alimento.setTpMedida(result.getString("TP_MEDIDA"));
				alimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
				alimento.setCalorias(result.getInt("CALORIAS"));
				alimento.setQtCarbo(result.getInt("QT_CARBO"));
				alimento.setQtProteina(result.getInt("QT_PROTEINA"));
				alimento.setQtGordura(result.getInt("QT_GORDURA"));
				
				
				return alimento;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
		
	}
		@Override
		public Alimento getByDate(String date)  {

			ConnectionManager dao = new ConnectionManager();


			try {
				PreparedStatement pstmt;
				pstmt = dao.getConnection().prepareStatement("SELECT * FROM T_SHL_ALIMENTO WHERE CD_ALIMENTO = ?)");

				pstmt.setString(1,date);

				ResultSet result = dao.getData(pstmt);
				
				while (result.next()) {
					Alimento alimento = new Alimento();
					
					alimento.setNmAlimento(result.getString("NM_ALIMENTO"));
					alimento.setTpMedida(result.getString("TP_MEDIDA"));
					alimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
					alimento.setCalorias(result.getInt("CALORIAS"));
					alimento.setQtCarbo(result.getInt("QT_CARBO"));
					alimento.setQtProteina(result.getInt("QT_PROTEINA"));
					alimento.setQtGordura(result.getInt("QT_GORDURA"));
					
					return alimento;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
	}
	
	@Override
	public void delete(int id)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("DELETE INTO T_SHL_ALIMENTO WHERE CD_ALIMENTO=?");
			pstmt.setInt(1,id);
		

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Alimento> getAll(){ 
		ConnectionManager dao = new ConnectionManager();


		List<Alimento> lstAlimento = new ArrayList<Alimento>();

		try { PreparedStatement pstmt; 
		pstmt = dao.getConnection().prepareStatement("SELECT CD_ALIMENTO, NM_ALIMENTO, TP_MEDIDA, QT_ALIMENTO, CALORIAS, QT_CARBO, QT_PROTEINA, QT_GORDURA "
				+ "FROM T_SHL_ALIMENTO"); 

		ResultSet result = dao.getData(pstmt); 

		while (result.next()) { 

			Alimento alimento = new Alimento();

			alimento.setCdAlimento(result.getInt("CD_ALIMENTO"));
			alimento.setNmAlimento(result.getString("NM_ALIMENTO"));
			alimento.setTpMedida(result.getString("TP_MEDIDA"));
			alimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
			alimento.setCalorias(result.getInt("CALORIAS"));
			alimento.setQtCarbo(result.getInt("QT_CARBO"));
			alimento.setQtProteina(result.getInt("QT_PROTEINA"));
			alimento.setQtGordura(result.getInt("QT_GORDURA"));
			

			lstAlimento.add(alimento);
			
		} 
		} catch(SQLException e) { 
			e.printStackTrace(); 
		} 
		return lstAlimento; 
	}

}
