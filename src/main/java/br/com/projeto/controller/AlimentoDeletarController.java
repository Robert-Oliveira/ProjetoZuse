package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OracleAlimentoDao;
import br.com.projeto.model.Alimento;



@WebServlet("/AlimentoDeletar")
public class AlimentoDeletarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public AlimentoDeletarController() {
        super();

    }

	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("id"));


		Alimento alimento = new OracleAlimentoDao().getById(codigo);

		request.setAttribute("alimento", alimento);

		RequestDispatcher rd = request.getRequestDispatcher("alimentoEditar.jsp");  
		rd.forward(request, response);

	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	

		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			OracleAlimentoDao alimentoDao = new OracleAlimentoDao();
			
			System.out.println(codigo);
			alimentoDao.delete(codigo);
			
			RequestDispatcher rd = request.getRequestDispatcher("listaAlimentoBusca.jsp"); 
			rd.forward(request, response);
			
		} catch (br.com.projeto.exception.DBException e) {
			
			e.printStackTrace();
		}

    }
}
