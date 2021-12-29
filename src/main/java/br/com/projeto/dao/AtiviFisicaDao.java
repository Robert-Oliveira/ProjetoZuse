package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.exception.DBException;
import br.com.projeto.model.AtividadeFisica;

public interface AtiviFisicaDao {

		void create(AtividadeFisica ativFisica) throws DBException;
		void update(AtividadeFisica ativFisica) throws DBException;
		void delete(int id) throws DBException;
		AtividadeFisica getById(int id) throws DBException;
		List<AtividadeFisica> getAll();
		List<AtividadeFisica> getByDate(String date) throws DBException;
	}


