package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.AtiviFisicaDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.AtividadeFisica;

public class OracleAtiviFisicaDao implements AtiviFisicaDao{
	
	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(AtividadeFisica ativFisica)throws DBException {

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_ATIV_FISICA (CD_PRATICA, DT_PRATICA, TM_DURACAO, T_SHL_USUARIO_CD_USUARIO, "
														+ "ATIVIDADE) VALUES(SEQ_CD_PRATICA.nextval,?,?,?,?)");
			
			pstmt.setString(1,ativFisica.getDtAtivFisica());
			pstmt.setInt(2,ativFisica.getDuracao());
			pstmt.setInt(3,ativFisica.getCdUsuario());
			pstmt.setString(4,ativFisica.getAtividade());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(AtividadeFisica ativFisica)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE INTO T_SHL_ATIV_FISICA SET CD_PRATICA=?, DT_PRATICA=?, TM_DURACAO=?, T_SHL_USUARIO_CD_USUARIO=?, "
														+ "ATIVIDADE=?)");
			
			pstmt.setInt(1,ativFisica.getCdAtivFisica());
			pstmt.setString(2,ativFisica.getDtAtivFisica());
			pstmt.setInt(3,ativFisica.getDuracao());
			pstmt.setInt(4,ativFisica.getCdUsuario());
			pstmt.setString(5,ativFisica.getAtividade());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	@Override
	public AtividadeFisica getById(int id) {
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_ATIV_FISICA WHERE T_SHL_ATIV_FISICA = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				AtividadeFisica atividadeFisica = new AtividadeFisica();
				
				atividadeFisica.setDtAtivFisica(result.getString("DT_PRATICA"));
				atividadeFisica.setDuracao(result.getInt("TM_DURACAO"));
				atividadeFisica.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				atividadeFisica.setCdExercico(result.getInt("CD_PRATICA"));
				atividadeFisica.setAtividade(result.getString("ATIVIDADE"));
				
				return atividadeFisica;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@Override
	public List<AtividadeFisica> getByDate(String date) {
		
		ConnectionManager dao = new ConnectionManager();
		
		List<AtividadeFisica> lstExercicio = new ArrayList<AtividadeFisica>();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_ATIV_FISICA WHERE DT_PRATICA = TO_DATE(?,'DD/MM/YYYY')");
			
			pstmt.setString(1,date);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				AtividadeFisica atividadeFisica = new AtividadeFisica();
				
				atividadeFisica.setDtAtivFisica(result.getString("DT_PRATICA"));
				atividadeFisica.setDuracao(result.getInt("TM_DURACAO"));
				atividadeFisica.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				atividadeFisica.setCdExercico(result.getInt("CD_PRATICA"));
				atividadeFisica.setDtAtivFisica(result.getString("ATIVIDADE"));
				
				
				lstExercicio.add(atividadeFisica);
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return lstExercicio;
	}
	
	
	
	@Override
	public void delete(int id)throws DBException {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement stmt;
		try {
			stmt = dao.getConnection().prepareStatement ("DELETE INTO T_SHL_ATIV_FISICA WHERE CD_PRATICA=?");
			stmt.setInt(1,id);
		

			dao.executeCommand(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<AtividadeFisica> getAll(){
		ConnectionManager dao = new ConnectionManager();

		List<AtividadeFisica> lstAtivFisica = new ArrayList<AtividadeFisica>();

		try {
			PreparedStatement stmt;
			stmt = dao.getConnection().prepareStatement("SELECT CD_PRATICA, DT_PRATICA, TM_DURACAO, T_SHL_USUARIO_CD_USUARIO, ATIVIDADE FROM T_SHL_ATIV_FISICA");
			ResultSet result = dao.getData(stmt);
			while (result.next()) {
				AtividadeFisica ativFisica = new AtividadeFisica();
				ativFisica.setCdAtivFisica(result.getInt("CD_PRATICA"));
				ativFisica.setDtAtivFisica(result.getString("DT_PRATICA"));
				ativFisica.setDuracao(result.getInt("TM_DURACAO"));
				ativFisica.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				ativFisica.setAtividade(result.getString("ATIVIDADE"));
				
				lstAtivFisica.add(ativFisica);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAtivFisica;
	}

	
		
	}


