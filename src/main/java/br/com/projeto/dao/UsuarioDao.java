package br.com.projeto.dao;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Usuario;

public interface UsuarioDao {

	void create(Usuario usuario) throws DBException;
	Usuario getLogin(String email, String senha) throws DBException;
	
}
