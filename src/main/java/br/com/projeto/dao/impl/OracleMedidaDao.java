package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.MedidaDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Medida;

public class OracleMedidaDao implements MedidaDao{

	ConnectionManager dao = new ConnectionManager();

	@Override
	public void create(Medida medida) {
		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_MEDIDA (CD_MEDIDA, NM_MEDIDA, NR_MEDIDA, DT_MEDIDA, CD_USUARIO )"
															+ "VALUES (SEQ_CD_MEDIDA.nextval,?,?,?,?)");
			pstmt.setString(1,medida.getNmMedida());
			pstmt.setInt(2,medida.getNrMedida());
			pstmt.setString(3,medida.getData());
			pstmt.setInt(4, medida.getCdUsuario());

			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Medida medida) {
		ConnectionManager dao = new ConnectionManager();


		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("UPDATE INTO T_SHL_MEDIDA SET CD_MEDIDA=?, NM_MEDIDA=?, NR_MEDIDA=?, DT_MEDIDA=?, CD_USUARIO=? )");
			pstmt.setInt(1, medida.getCdMedida());
			pstmt.setString(2,medida.getNmMedida());
			pstmt.setInt(3,medida.getNrMedida());
			pstmt.setString(4,medida.getData());
			pstmt.setInt(5, medida.getCdUsuario());
			
			
			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Medida getById(int id)throws DBException {
		
		ConnectionManager dao = new ConnectionManager();
		
		PreparedStatement pstmt;
		
		try {
			
			pstmt = dao.getConnection().prepareStatement( "SELECT * FROM T_SHL_MEDIDA WHERE T_SHL_MEDIDA = ?");
			
			pstmt.setInt(1,id);
			
			ResultSet result = dao.getData(pstmt);
			
			while(result.next()) {
				
				Medida medida = new Medida();
				
				medida.setNmMedida(result.getString("NM_MEDIDA"));
				medida.setCdMedida(result.getInt("CD_MEDIDA"));
				medida.setNrMedida(result.getInt("NR_MEDIDA"));
				medida.setCdUsuario(result.getInt("T_SHL_USUARIO_CD_USUARIO"));
				medida.setData(result.getString("DT_MEDIDA"));
				
				return medida;
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	
	
	@Override
	public void delete(Medida medida) {
		ConnectionManager dao = new ConnectionManager();

		
		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("DELETE INTO T_SHL_EXERCICIO WHERE CD_MEDIDA=?");
			pstmt.setInt(1,medida.getCdMedida());
		

		 dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Medida> getAll(){
		ConnectionManager dao = new ConnectionManager();

		List<Medida> lstMedida = new ArrayList<Medida>();

		try {
			PreparedStatement stmt;
			stmt = dao.getConnection().prepareStatement("SELECT CD_MEDIDA, NM_MEDIDA, NR_MEDIDA, DT_MEDIDA, CD_USUARIO FROM T_SHL_MEDIDA ");
			ResultSet result = dao.getData(stmt);
			while (result.next()) {
				Medida medida = new Medida();
				medida.setCdMedida(result.getInt("CD_MEDIDA"));
				medida.setNmMedida(result.getString("NM_MEDIDA"));
				medida.setNrMedida(result.getInt("NR_MEDIDA"));
				medida.setData(result.getString("DT_MEDIDA"));
				medida.setCdUsuario(result.getInt("CD_USUARIO"));


				lstMedida.add(medida);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstMedida;
	}

}
