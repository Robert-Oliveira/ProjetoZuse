package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Alimento;

public interface AlimentoDao {
		void create(Alimento alimento) throws DBException;
		void update(Alimento alimento) throws DBException;
		void delete(int id) throws DBException;
		Alimento getById(int id)throws DBException;
		List<Alimento> getAll();
		Alimento getByDate(String date) throws DBException;
	
		
		
	}


