package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OraclePesoDao;
import br.com.projeto.model.Peso;



@WebServlet("/listaPeso")
public class PesoListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PesoListarController() {
        super();
    }

	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OraclePesoDao pDao = new OraclePesoDao();
		
		String data = request.getParameter("data");
		
		List<Peso> lstPeso = pDao.getByDate(data);
		
		request.setAttribute("lstPeso", lstPeso);
		
		RequestDispatcher rd = request.getRequestDispatcher("listaPeso.jsp");  
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
