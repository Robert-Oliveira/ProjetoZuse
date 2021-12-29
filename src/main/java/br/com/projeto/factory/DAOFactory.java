package br.com.projeto.factory;

import br.com.projeto.dao.PressaoDao;
import br.com.projeto.dao.impl.OraclePressaoDao;

public class DAOFactory {
	
	
	public static PressaoDao getPressaoDao() {
		return (PressaoDao) new OraclePressaoDao();
	}
	
		
}