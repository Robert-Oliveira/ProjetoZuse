package br.com.projeto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.projeto.connection.ConnectionManager;
import br.com.projeto.dao.UsuarioDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Usuario;

public class OracleUsuarioDao implements UsuarioDao{


	@Override
	public void create(Usuario usuario)throws DBException {

		ConnectionManager dao = new ConnectionManager();

		PreparedStatement pstmt;
		try {
			pstmt = dao.getConnection().prepareStatement ("INSERT INTO T_SHL_USUARIO (CD_USUARIO, NM_USUARIO, DS_EMAIL, NR_ALTURA, "
					+ "DT_NASCIMENTO, DS_SEXO, DS_SENHA) VALUES(SEQ_CD_USUARIO.nextval,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?)");

			pstmt.setString(1,usuario.getNomeUsuario());
			pstmt.setString(2,usuario.getEmail());
			pstmt.setDouble(3,usuario.getAltura());
			pstmt.setString(4,usuario.getDtNascimento());
			pstmt.setString(5,usuario.getSexo());		
			pstmt.setString(6, usuario.getSenha());


			dao.executeCommand(pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Usuario getLogin(String email, String senha) {

		ConnectionManager dao = new ConnectionManager();

		Usuario usuario = new Usuario();

		PreparedStatement pstmt;

		try {

			pstmt = dao.getConnection().prepareStatement( "SELECT CD_USUARIO, NM_USUARIO, DS_EMAIL, NR_ALTURA, DT_NASCIMENTO, DS_SEXO, "
					+ "DS_SENHA FROM T_SHL_USUARIO WHERE DS_EMAIL =? AND DS_SENHA =?");

			pstmt.setString(1,email);
			pstmt.setString(2,senha);

			ResultSet result = dao.getData(pstmt);

			while(result.next()) {

				usuario.setCdUsuario(result.getInt("CD_USUARIO"));
				usuario.setNomeUsuario(result.getString("NM_USUARIO"));
				usuario.setEmail(result.getString("DS_EMAIL"));
				usuario.setAltura(result.getDouble("NR_ALTURA"));
				usuario.setDtNascimento(result.getString("DT_NASCIMENTO"));
				usuario.setSexo(result.getString("DS_SEXO"));
				usuario.setSenha(result.getString("DS_SENHA"));

				return usuario;

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

