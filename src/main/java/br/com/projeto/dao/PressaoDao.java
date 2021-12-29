package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Pressao;

public interface PressaoDao {
	void create(Pressao pressao) throws DBException;
	void update(Pressao pressao) throws DBException;
	void delete(int id) throws DBException;
	Pressao getById(int id) throws DBException;
	
	List<Pressao> getByDate(String date);
	
	List<Pressao> getAll();
}

