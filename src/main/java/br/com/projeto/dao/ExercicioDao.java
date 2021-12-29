package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.Exercicio;

public interface ExercicioDao {
		void create(Exercicio exercicio) throws DBException;
		void update(Exercicio exercicio) throws DBException;
		void delete(Exercicio exercicio) throws DBException;
		Exercicio getById(int id) throws DBException;
		List<Exercicio> getAll();
		
	}


