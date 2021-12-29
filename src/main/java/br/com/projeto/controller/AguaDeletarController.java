package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OracleAguaDao;
import br.com.projeto.model.Agua;



@WebServlet("/aguaDelete")
public class AguaDeletarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AguaDeletarController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("id"));


		Agua agua = new OracleAguaDao().getById(codigo);

		request.setAttribute("agua", agua);

		RequestDispatcher rd = request.getRequestDispatcher("aguaEditar.jsp");  
		rd.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			OracleAguaDao aguaDao = new OracleAguaDao();
			
			System.out.println(codigo);
			aguaDao.delete(codigo);
			
			RequestDispatcher rd = request.getRequestDispatcher("listaAguaBusca.jsp"); 
			rd.forward(request, response);
			
		} catch (br.com.projeto.exception.DBException e) {
			
			e.printStackTrace();
		}
			
		
	}
	
	
	
}
