package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OracleAlimentoDao;
import br.com.projeto.model.Alimento;



@WebServlet("/listarAlimentacao")
public class AlimentacaoListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public AlimentacaoListarController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OracleAlimentoDao alimentoDao = new OracleAlimentoDao();
		
		List<Alimento> lstAlimento = alimentoDao.getAll();
		
		request.setAttribute("lstAlimento", lstAlimento);
		RequestDispatcher view = request.getRequestDispatcher("listaAlimentacao.jsp");  
		view.forward(request, response);
		
		
				
	
	}

}
