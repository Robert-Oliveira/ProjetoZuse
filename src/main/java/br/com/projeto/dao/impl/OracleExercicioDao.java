package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.ExercicioDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Exercicio;

public class OracleExercicioDao implements ExercicioDao{

	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(Exercicio exercicio)throws DBException {
		

		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_EXERCICIO ( CD_EXERCIO, NM_EXERCICO, NR_CALORIAS)"
															+ "VALUES(SEQ_CD_EXERCIO.nextval,?,?)");
			pstmt.setString(1, exercicio.getNmExercicio());
			pstmt.setInt(2, exercicio.getNrCalorias());

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(Exercicio exercicio)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE INTO T_SHL_EXERCICIO SET CD_EXERCIO=?, NM_EXERCICO=?, NR_CALORIAS=?)");
			pstmt.setInt(1, exercicio.getCdExercicio());
			pstmt.setString(2, exercicio.getNmExercicio());
			pstmt.setInt(3, exercicio.getNrCalorias());
		

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Exercicio getById(int id)throws DBException {
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_EXERCICIO WHERE CD_EXERCIO = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Exercicio exercicio = new Exercicio();
				
				exercicio.setNmExercicio(result.getString("NM_EXERCICO"));
				exercicio.setNrCalorias(result.getInt("NR_CALORIAS"));
			
				
				return exercicio;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@Override
	public void delete(Exercicio exercicio)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		

		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("DELETE INTO T_SHL_EXERCICIO WHERE CD_EXERCICIO=?");
			pstmt.setInt(1,exercicio.getCdExercicio());
		

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Exercicio> getAll(){
		ConnectionManager dao = new ConnectionManager();

		
		List<Exercicio> lstExercicio  = new ArrayList<Exercicio>();

		try {
			PreparedStatement pstmt;
			pstmt = dao.getConnection().prepareStatement("SELECT CD_EXERCIO, NM_EXERCICO, NR_CALORIAS FROM T_SHL_EXERCICIO ");
			ResultSet result = dao.getData(pstmt);
			while (result.next()) {
				Exercicio exercicio = new Exercicio();
				exercicio .setCdExercicio(result.getInt("CD_EXERCIO"));
				exercicio .setNmExercicio(result.getString("NM_EXERCICO"));
				exercicio .setNrCalorias(result.getInt("NR_CALORIAS"));
				lstExercicio .add(exercicio);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstExercicio ;
	}

}
