package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OracleUsuarioDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Usuario;


@WebServlet("/usuarioCadastar")
public class UsuarioCadastarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public UsuarioCadastarController() {
        super();
       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			
			OracleUsuarioDao uDao = new OracleUsuarioDao();
			Usuario usuario = new Usuario();
			
			usuario.setNomeUsuario(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setAltura(Double.parseDouble(request.getParameter("altura")));
			usuario.setDtNascimento(request.getParameter("dtnasc"));		
			usuario.setSexo(request.getParameter("sexo"));
      		usuario.setSenha(request.getParameter("senha"));
			
			uDao.create(usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
			rd.forward(request, response);	
			
		} catch (DBException e) {
			
			e.printStackTrace();
		}
	}

}
