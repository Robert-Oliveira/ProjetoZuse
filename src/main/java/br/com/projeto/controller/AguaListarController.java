package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OracleAguaDao;
import br.com.projeto.model.Agua;
@WebServlet("/listaAgua")
public class AguaListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public AguaListarController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OracleAguaDao aDao = new OracleAguaDao();
		
		String data = request.getParameter("data");
		
		List<Agua> lstAgua = aDao.getByDate(data);
		
		request.setAttribute("lstAgua", lstAgua);
		
		RequestDispatcher rd = request.getRequestDispatcher("listaAgua.jsp");  
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
