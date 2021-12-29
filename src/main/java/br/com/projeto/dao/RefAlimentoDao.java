package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.RefAlimento;

public interface RefAlimentoDao {
	void create(RefAlimento refAlimento) throws DBException;
	void update(RefAlimento refAlimento) throws DBException;
	void delete(RefAlimento refAlimento) throws DBException;
	RefAlimento getById(int id) throws DBException;
	List<RefAlimento> getAll();
	RefAlimento getByDate(String date) throws DBException;
	
	
}
