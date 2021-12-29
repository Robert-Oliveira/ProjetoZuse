package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.RefAlimentoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.RefAlimento;


public class OracleRefAlimentoDao implements RefAlimentoDao{

	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(RefAlimento refAlimento)throws DBException {


		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_REF_ALIMENTO(CD_ALIMENTACAO, NM_ALIMENTO, NM_REFEICAO, DT_ALIMENTACAO, "
					+ "QT_ALIMENTO, CALORIAS, QT_CARBO, QT_PROTEINA, QT_GORDURA,CD_USUARIO) VALUES (SEQ_CD_REF_ALIMENTACAO.nextval,"
					+ "?,?,to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,?)");


			pstmt.setString(1,refAlimento.getNmAlimento());
			pstmt.setString(2,refAlimento.getNmRefeicao());
			pstmt.setString(3, refAlimento.getDtAlimentacao());
			pstmt.setInt(4,refAlimento.getQtAlimento());
			pstmt.setInt(5,refAlimento.getCaloria());
			pstmt.setInt(6,refAlimento.getQtCarboidrato());
			pstmt.setInt(7,refAlimento.getQtProteina());
			pstmt.setInt(8,refAlimento.getQtGordura());
			pstmt.setInt(9,refAlimento.getCdUsuario());

			dao.executeCommand(pstmt);	

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void update(RefAlimento refAlimento)throws DBException {


		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE T_SHL_REF_ALIMENTO SET CD_ALIMENTACAO=?, NM_ALIMENTO=?, NM_REFEICAO=?, DT_ALIMENTACAO=?, QT_ALIMENTO=?, CALORIAS=?, QT_CARBO=?, QT_PROTEINA=?, QT_GORDURA=?,CD_USUARIO=?");

					pstmt.setString(1,refAlimento.getNmAlimento());
					pstmt.setString(2,refAlimento.getNmRefeicao());
					pstmt.setString(3, refAlimento.getDtAlimentacao());
					pstmt.setInt(4,refAlimento.getQtAlimento());
					pstmt.setInt(5,refAlimento.getCaloria());
					pstmt.setInt(6,refAlimento.getQtCarboidrato());
					pstmt.setInt(7,refAlimento.getQtProteina());
					pstmt.setInt(8,refAlimento.getQtGordura());
					pstmt.setInt(9,refAlimento.getCdUsuario());
					
			dao.executeCommand(pstmt);	

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public RefAlimento getById(int id) {

		ConnectionManager dao = new ConnectionManager();

		PreparedStatement pstmt;

		try {

			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_REF_ALIMENTO WHERE CD_ALIMENTACAO = ?");

			pstmt.setInt(1,id);

			ResultSet result = dao.getData(pstmt);

			while(result.next()) {

				RefAlimento refAlimento = new RefAlimento();
				
				refAlimento.setNmAlimento(result.getString("NM_ALIMENTO"));
				refAlimento.setNmRefeicao(result.getString("NM_REFEICAO"));
				refAlimento.setDtAlimentacao(result.getString("DT_ALIMENTACAO"));
				refAlimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
				refAlimento.setCaloria(result.getInt("CALORIAS"));
				refAlimento.setQtCarboidrato(result.getInt("QT_CARBO"));
				refAlimento.setQtProteina(result.getInt("QT_PROTEINA"));
				refAlimento.setQtGordura(result.getInt("QT_GORDURA"));
				refAlimento.setCdUsuario(result.getInt("CD_USUARIO"));
				
						
				return refAlimento;


			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public RefAlimento getByDate(String date) {
		ConnectionManager dao = new ConnectionManager();

		PreparedStatement pstmt;

		try {

			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_REF_ALIMENTO WHERE DT_ALIMENTACAO = TO_DATE(?,'DD/MM/YYYY')");

			pstmt.setString(1,date);

			ResultSet result = dao.getData(pstmt);

			while(result.next()) {

				RefAlimento refAlimento = new RefAlimento();

				refAlimento.setNmAlimento(result.getString("NM_ALIMENTO"));
				refAlimento.setNmRefeicao(result.getString("NM_REFEICAO"));
				refAlimento.setDtAlimentacao(result.getString("DT_ALIMENTACAO"));
				refAlimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
				refAlimento.setCaloria(result.getInt("CALORIAS"));
				refAlimento.setQtCarboidrato(result.getInt("QT_CARBO"));
				refAlimento.setQtProteina(result.getInt("QT_PROTEINA"));
				refAlimento.setQtGordura(result.getInt("QT_GORDURA"));
				refAlimento.setCdUsuario(result.getInt("CD_USUARIO"));

				return refAlimento;


			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public void delete(RefAlimento refAlimento)throws DBException {


		PreparedStatement stmt;
		try {
			stmt = dao.getConnection().prepareStatement ("DELETE INTO T_SHL_REF_ALIMENTO WHERE CD_ALIMENTACAO=?");
			stmt.setInt(1,refAlimento.getCdAlimentacao());


			dao.executeCommand(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<RefAlimento> getAll(){ 

		List<RefAlimento> lstRfAlimento = new ArrayList<RefAlimento>();

		try { PreparedStatement stmt; 
		stmt = dao.getConnection().prepareStatement("SELECT CD_ALIMENTACAO, CD_ALIMENTACAO, NM_ALIMENTO, NM_REFEICAO, DT_ALIMENTACAO, QT_ALIMENTO, CALORIAS, QT_CARBO, QT_PROTEINA, QT_GORDURA,CD_USUARIO"
				+ "FROM T_SHL_REF_ALIMENTO");

		ResultSet result = dao.getData(stmt); 

		while (result.next()) { 

			RefAlimento refAlimento = new RefAlimento();

			refAlimento.setNmAlimento(result.getString("NM_ALIMENTO"));
			refAlimento.setNmRefeicao(result.getString("NM_REFEICAO"));
			refAlimento.setDtAlimentacao(result.getString("DT_ALIMENTACAO"));
			refAlimento.setQtAlimento(result.getInt("QT_ALIMENTO"));
			refAlimento.setCaloria(result.getInt("CALORIAS"));
			refAlimento.setQtCarboidrato(result.getInt("QT_CARBO"));
			refAlimento.setQtProteina(result.getInt("QT_PROTEINA"));
			refAlimento.setQtGordura(result.getInt("QT_GORDURA"));
			refAlimento.setCdUsuario(result.getInt("CD_USUARIO"));

			lstRfAlimento.add(refAlimento);

		} 
		} catch(SQLException e) { 
			e.printStackTrace(); 
		} 
		return lstRfAlimento; 
	}


}

