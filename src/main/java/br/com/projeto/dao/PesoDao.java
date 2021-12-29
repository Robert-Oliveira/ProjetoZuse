package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Peso;

public interface PesoDao {
	void create(Peso peso) throws DBException;
	void update(Peso peso) throws DBException;
	Peso getById(int id) throws DBException;
	List<Peso> getAll();
	List<Peso> getByDate (String date) throws DBException;
	void delete(int id) throws DBException;
	
}
