package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.PressaoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Pressao;

public class OraclePressaoDao implements PressaoDao {

	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(Pressao pressao)throws DBException {

		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_PRESSAO (CD_AFERICAO, DT_HORA_AFERICAO, NR_SISTOLICA, NR_DIASTOLICA, "
					+ "DS_OCORRENCIA, T_SHL_USUARIO_CD_USUARIO) VALUES(SEQ_CD_AFERICAO.nextval,TO_DATE(?,'YYYY-MM-DD HH24:MI'),?,?,?,?)");

			pstmt.setString(1,pressao.getDtAfericao());
			pstmt.setInt(2,pressao.getSistolica());
			pstmt.setInt(3,pressao.getDiastolica());
			pstmt.setString(4,pressao.getOcorrencia());
			pstmt.setInt(5,pressao.getCdUsuario());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Pressao pressao)throws DBException {

		ConnectionManager dao = new ConnectionManager();


		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE T_SHL_PRESSAO SET  NR_SISTOLICA =?, NR_DIASTOLICA =?, DS_OCORRENCIA =? "
					+ "WHERE CD_AFERICAO =?");

			pstmt.setInt(1,pressao.getSistolica());
			pstmt.setInt(2,pressao.getDiastolica());
			pstmt.setString(3,pressao.getOcorrencia());
			pstmt.setInt(4,pressao.getCdAfericao());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();

		}


	}

	@Override
	public Pressao getById(int id) {

		ConnectionManager dao = new ConnectionManager();

		PreparedStatement pstmt;

		try {

			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_PRESSAO WHERE CD_AFERICAO = ?");

			pstmt.setInt(1,id);

			ResultSet result = dao.getData(pstmt);

			while(result.next()) {

				Pressao pressao = new Pressao();

				pressao.setDtAfericao(result.getString("DT_HORA_AFERICAO"));
				pressao.setSistolica(result.getInt("NR_SISTOLICA"));
				pressao.setDiastolica(result.getInt("NR_DIASTOLICA"));
				pressao.setOcorrencia(result.getString("DS_OCORRENCIA"));
				pressao.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				pressao.setCdAfericao(result.getInt("CD_AFERICAO"));

				return pressao;


			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(int id)throws DBException {

		ConnectionManager dao = new ConnectionManager();


		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("DELETE T_SHL_PRESSAO WHERE CD_AFERICAO=?");
			pstmt.setInt(1,id);

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<Pressao> getAll(){
		ConnectionManager dao = new ConnectionManager();

		List<Pressao> lstPressao = new ArrayList<Pressao>();

		try {
			PreparedStatement pstmt;
			pstmt = dao.getConnection().prepareStatement("SELECT CD_AFERICAO, DT_HORA_AFERICAO, NR_SISTOLICA, NR_DIASTOLICA, "
					+ "DS_OCORRENCIA, T_SHL_USUARIO_CD_USUARIO FROM T_SHL_PRESSAO");

			ResultSet result = dao.getData(pstmt);
			while (result.next()) {
				Pressao pressao = new Pressao();
				pressao.setCdAfericao(result.getInt("CD_AFERICAO"));
				pressao.setDtAfericao(result.getString("DT_HORA_AFERICAO"));
				pressao.setSistolica(result.getInt("NR_SISTOLICA"));
				pressao.setDiastolica(result.getInt("NR_DIASTOLICA")); 
				pressao.setOcorrencia(result.getString("DS_OCORRENCIA"));
				pressao.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));

				lstPressao.add(pressao);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPressao;
	}

	@Override
	public List<Pressao> getByDate(String date) {

		ConnectionManager dao = new ConnectionManager();
		
		List<Pressao> lstPressao = new ArrayList<Pressao>();


		try {
			PreparedStatement pstmt;
			pstmt = dao.getConnection().prepareStatement("SELECT * FROM T_SHL_PRESSAO WHERE TRUNC(DT_HORA_AFERICAO) = TO_DATE(?,'YYYY-MM-DD')");

			pstmt.setString(1,date);

			ResultSet result = dao.getData(pstmt);
			
			while (result.next()) {
				
				Pressao pressao = new Pressao();
				
				pressao.setCdAfericao(result.getInt("CD_AFERICAO"));
				pressao.setDtAfericao(result.getString("DT_HORA_AFERICAO"));
				pressao.setSistolica(result.getInt("NR_SISTOLICA"));
				pressao.setDiastolica(result.getInt("NR_DIASTOLICA")); 
				pressao.setOcorrencia(result.getString("DS_OCORRENCIA"));
				pressao.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				
				lstPressao.add(pressao);

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPressao;
	}

}
