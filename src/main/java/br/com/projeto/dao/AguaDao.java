package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Agua;

public interface AguaDao {

		void create(Agua agua) throws DBException;
		void update(Agua agua) throws DBException;
		void delete(int id) throws DBException;
		Agua getById(int id)throws DBException;
		List<Agua> getAll();
		List<Agua> getByDate(String date) throws DBException;
		
	}


