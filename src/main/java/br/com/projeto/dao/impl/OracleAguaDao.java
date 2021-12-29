package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.AguaDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Agua;


public class OracleAguaDao implements AguaDao{

	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(Agua agua)throws DBException {

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_AGUA (CD_AGUA, DT_AGUA, QT_INGERIDA, T_SHL_USUARIO_CD_USUARIO ) "
														+ "VALUES(SEQ_CD_AGUA.nextval,TO_DATE(?,'YYYY-MM-DD'),?,?)");
			pstmt.setString(1,agua.getDtAgua());
			pstmt.setInt(2,agua.getQtIngerida());
			pstmt.setInt(3,agua.getCdUsuario());

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Agua agua)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE T_SHL_AGUA SET QT_INGERIDA=? "
					+ "WHERE CD_AGUA=?");

			pstmt.setInt(1,agua.getQtIngerida());
			pstmt.setInt(2,agua.getCdAgua());

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Agua getById(int id) {
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_AGUA WHERE CD_AGUA = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Agua agua = new Agua();
				
				agua.setDtAgua(result.getString("DT_AGUA"));
				agua.setQtIngerida(result.getInt("QT_INGERIDA"));
				agua.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				agua.setCdAgua(result.getInt("CD_AGUA"));
				
				return agua;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@Override
	public void delete(int id)throws DBException {
		
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement stmt;
		try {
			stmt = dao.getConnection().prepareStatement ("DELETE T_SHL_AGUA WHERE CD_AGUA=?");
			stmt.setInt(1,id);
		

			 dao.executeCommand(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Agua> getAll(){
		ConnectionManager dao = new ConnectionManager();

	List<Agua> lstAgua = new ArrayList<Agua>();

	try {
		PreparedStatement stmt;
		stmt = dao.getConnection().prepareStatement("SELECT CD_AGUA, DT_AGUA, QT_INGERIDA, T_SHL_USUARIO_CD_USUARIO  FROM T_SHL_AGUA");
		ResultSet result = dao.getData(stmt);
		while (result.next()) {
			Agua agua = new Agua();
			agua.setCdAgua(result.getInt("CD_AGUA"));
			agua.setDtAgua(result.getString("DT_AGUA"));
			agua.setQtIngerida(result.getInt("QT_INGERIDA"));
			agua.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
			lstAgua.add(agua);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return lstAgua;
	}
	
	@Override
	public List<Agua> getByDate(String date)  {

		ConnectionManager dao = new ConnectionManager();
		
		List<Agua> lstAgua = new ArrayList<Agua>();


		try {
			PreparedStatement pstmt;
			pstmt = dao.getConnection().prepareStatement("SELECT * FROM T_SHL_AGUA WHERE DT_AGUA = TO_DATE(?,'yyyy-mm-dd')");

			pstmt.setString(1,date);

			ResultSet result = dao.getData(pstmt);
			
			while (result.next()) {
				
				Agua agua = new Agua();
				
				agua.setCdAgua(result.getInt("CD_AGUA"));
				agua.setDtAgua(result.getString("DT_AGUA"));
				agua.setQtIngerida(result.getInt("QT_INGERIDA"));
				agua.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));

				lstAgua.add(agua);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAgua;
	}
	
	
}