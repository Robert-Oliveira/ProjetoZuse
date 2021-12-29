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


@WebServlet("/pesoEditar")
public class PesoEditarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public PesoEditarController() {
        super();
    }

	
    @Override
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

			OraclePesoDao pesoDao = new OraclePesoDao();

			Peso peso = new Peso();

			peso.setQtPeso(Integer.parseInt(request.getParameter("qtPeso")));
			peso.setCdPeso(Integer.parseInt(request.getParameter("codigo")));


			RequestDispatcher rd = request.getRequestDispatcher("listaPesoBusca.jsp"); 
			rd.forward(request, response);
			
			pesoDao.update(peso);


		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
