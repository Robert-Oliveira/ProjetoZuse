package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.dao.impl.OracleUsuarioDao;
import br.com.projeto.model.Usuario;


@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public LoginController() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OracleUsuarioDao uDao = new OracleUsuarioDao();
		Usuario usuario = new Usuario();

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		usuario = uDao.getLogin(email, senha);

		if(usuario != null) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("email", email);
			session.setAttribute("senha", senha);
			session.setAttribute("codigo", usuario.getCdUsuario());
			
			System.out.println(session.getAttribute("codigo"));
			
			RequestDispatcher rd = request.getRequestDispatcher("dashbord.jsp"); 
			rd.forward(request, response);	
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
			rd.forward(request, response);
		}



	}

}