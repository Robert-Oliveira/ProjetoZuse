package br.com.projeto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionManager {
	
	private Connection connection;

	public ConnectionManager () {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		OpenConection();
		return this.connection;
	}
	
	public void OpenConection() {
		try {
			this.connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM88959", "281288");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	public int executeCommand (PreparedStatement stmt) {
		
		int j = 0;
		
		try {
			this.connection.setAutoCommit(false);
			j = stmt.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
		}
		
		return j;
	}
	
public int executeUpdate (PreparedStatement pstmt) {
		
		int j = 0;
		
		try {
			this.connection.setAutoCommit(false);
			j = pstmt.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
		}
		
		return j;
	}
	
	public ResultSet getData(PreparedStatement stmt) {
		
		ResultSet result = null;
		try {
			result = stmt.executeQuery();
			return result;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			//closeConnection();
		}
		return null;
	}

	
}
