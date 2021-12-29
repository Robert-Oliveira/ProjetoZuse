package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Medida;

public interface MedidaDao {

	void create(Medida medida) throws DBException;
	void update(Medida medida) throws DBException;
	void delete(Medida medida) throws DBException;
	Medida getById(int id) throws DBException;
	List<Medida> getAll();
	
}
