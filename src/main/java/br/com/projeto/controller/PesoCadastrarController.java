package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.dao.impl.OraclePesoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Peso;

@WebServlet("/pesoNovo")

public class PesoCadastrarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public PesoCadastrarController() {
        super();
 
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();		

			OraclePesoDao pesoDao = new OraclePesoDao();
			
			Peso peso = new Peso();
			
			peso.setDtPeso(request.getParameter("dataPeso"));
			peso.setQtPeso(Integer.parseInt(request.getParameter("peso")));
			peso.setCdUsuario((Integer) session.getAttribute("codigo"));
			
			
			RequestDispatcher rd = request.getRequestDispatcher("listaPesoBusca.jsp"); 
			rd.forward(request, response);	
		
			
			pesoDao.create(peso);
			
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
	}

}
