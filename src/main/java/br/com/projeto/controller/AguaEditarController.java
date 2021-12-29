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



@WebServlet("/aguaEditar")
public class AguaEditarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
    public AguaEditarController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("id"));


		Agua agua = new OracleAguaDao().getById(codigo);

		request.setAttribute("agua", agua);

		RequestDispatcher rd = request.getRequestDispatcher("aguaEditar.jsp");  
		rd.forward(request, response);

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			OracleAguaDao aguaDao = new OracleAguaDao();

			Agua agua = new Agua();

			agua.setQtIngerida(Integer.parseInt(request.getParameter("qtIngerida")));
			agua.setCdAgua(Integer.parseInt(request.getParameter("codigo")));


			RequestDispatcher rd = request.getRequestDispatcher("listaAguaBusca.jsp"); 
			rd.forward(request, response);
			
			aguaDao.update(agua);


		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	

}
