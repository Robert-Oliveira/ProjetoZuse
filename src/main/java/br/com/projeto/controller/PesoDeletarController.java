package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OraclePesoDao;
import br.com.projeto.model.Peso;


@WebServlet("/pesoDeletar")
public class PesoDeletarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PesoDeletarController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("id"));


		Peso peso = new OraclePesoDao().getById(codigo);

		request.setAttribute("peso", peso);

		RequestDispatcher rd = request.getRequestDispatcher("pesoEditar.jsp");  
		rd.forward(request, response);

	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			OraclePesoDao pesoDao = new OraclePesoDao();
			
			System.out.println(codigo);
			pesoDao.delete(codigo);
			
			RequestDispatcher rd = request.getRequestDispatcher("listaPesoBusca.jsp"); 
			rd.forward(request, response);
			
		} catch (br.com.projeto.exception.DBException e) {
			
			e.printStackTrace();
		}
			
		
	}

}
