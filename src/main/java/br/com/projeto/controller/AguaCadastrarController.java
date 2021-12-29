package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.dao.impl.OracleAguaDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Agua;

@WebServlet("/aguaNovo")

public class AguaCadastrarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public AguaCadastrarController() {
        super();
 
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();

			OracleAguaDao aguaDao = new OracleAguaDao();
			
			Agua agua = new Agua();
			
			agua.setDtAgua(request.getParameter("dataAgua"));
			agua.setQtIngerida(Integer.parseInt(request.getParameter("quantidade")));
			agua.setCdUsuario((Integer) session.getAttribute("codigo"));
			
			
			RequestDispatcher rd = request.getRequestDispatcher("listaAguaBusca.jsp");
			rd.forward(request, response);	
		
			
			aguaDao.create(agua);
			
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
	}

}
