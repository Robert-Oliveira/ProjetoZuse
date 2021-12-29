package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OraclePressaoDao;
import br.com.projeto.model.Pressao;


@WebServlet("/listaPressao")
public class PressaoListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public PressaoListarController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OraclePressaoDao pDao = new OraclePressaoDao();
		
		String data = request.getParameter("data");
		
		List<Pressao> lstPressao = pDao.getByDate(data);
		
		request.setAttribute("lstPressao", lstPressao);
		
		RequestDispatcher rd = request.getRequestDispatcher("listaPressao.jsp");  
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
