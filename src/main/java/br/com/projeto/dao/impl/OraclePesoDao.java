package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.PesoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Peso;


public class OraclePesoDao implements PesoDao{

	ConnectionManager dao = new ConnectionManager();
	
	
	@Override
	public void create(Peso peso)throws DBException{

		

		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_PESO (CD_PESO, DT_PESO, VL_PESO, CD_USUARIO) "
					+ "VALUES(SEQ_CD_PESO.nextval,TO_DATE(?,'YYYY-MM-DD'),?,?)");

			pstmt.setString(1,peso.getDtPeso());
			pstmt.setInt(2,peso.getQtPeso());
			pstmt.setInt(3,peso.getCdUsuario());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	@Override
	public void update(Peso peso)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE T_SHL_PESO SET VL_PESO=? WHERE CD_PESO=?");

			
			pstmt.setInt(1,peso.getQtPeso());
			pstmt.setInt(2,peso.getCdPeso());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	@Override
	public Peso getById(int id){
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_PESO WHERE CD_PESO = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Peso peso = new Peso();
				
				peso.setCdPeso(result.getInt("CD_PESO"));
				peso.setDtPeso(result.getString("DT_PESO"));
				peso.setQtPeso(result.getInt("VL_PESO"));
				peso.setCdUsuario(result.getInt("CD_USUARIO"));
								
				return peso;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	

	@Override
	public List<Peso> getByDate(String date)  {
		
		ConnectionManager dao = new ConnectionManager();
		
		List<Peso> lstPeso = new ArrayList<Peso>();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_PESO WHERE DT_PESO = TO_DATE(?,'YYYY-MM-DD')");
			
			pstmt.setString(1,date);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Peso peso = new Peso();
				
				peso.setCdPeso(result.getInt("CD_PESO"));
				peso.setDtPeso(result.getString("DT_PESO"));
				peso.setQtPeso(result.getInt("VL_PESO"));
				peso.setCdUsuario(result.getInt("CD_USUARIO"));
								
				lstPeso.add(peso);
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return lstPeso;
	}
	
	
	@Override
	public void delete(int id)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("DELETE T_SHL_PESO WHERE CD_PESO=?");
			pstmt.setInt(1,id);
		

			 dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Peso> getAll(){
		ConnectionManager dao = new ConnectionManager();

		
		List<Peso> lstPeso = new ArrayList<Peso>();

		try {
			PreparedStatement stmt;
			stmt = dao.getConnection().prepareStatement("SELECT CD_PESO, DT_PESO, VL_PESO, CD_USUARIO FROM T_SHL_PESO");
			ResultSet result = dao.getData(stmt);
			while (result.next()) {
				Peso peso = new Peso();
				peso.setCdPeso(result.getInt("CD_PESO"));
				peso.setDtPeso(result.getString("DT_PESO"));
				peso.setQtPeso(result.getInt("VL_PESO"));
				peso.setCdUsuario(result.getInt("CD_USUARIO"));

				lstPeso.add(peso);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPeso;
	}

	


}
