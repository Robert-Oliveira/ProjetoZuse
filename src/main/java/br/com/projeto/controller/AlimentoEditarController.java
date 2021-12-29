package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.AlimentoDao;
import br.com.projeto.dao.impl.OracleAlimentoDao;
import br.com.projeto.model.Alimento;


@WebServlet("/AlimentoEditar")
public class AlimentoEditarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AlimentoEditarController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("id"));


		AlimentoDao alimento = (AlimentoDao) new OracleAlimentoDao().getById(codigo);

		request.setAttribute("alimento", alimento);

		RequestDispatcher rd = request.getRequestDispatcher("alimentoEditar.jsp");  
		rd.forward(request, response);

	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			OracleAlimentoDao alimentoDao = new OracleAlimentoDao();

			Alimento alimento = new Alimento();

			alimento.setNmAlimento("nmAlimento");
			alimento.setCalorias(Integer.parseInt(request.getParameter("caloria")));
			alimento.setQtAlimento(Integer.parseInt(request.getParameter("QtAlimento")));
			alimento.setQtCarbo(Integer.parseInt(request.getParameter("carboidrato")));
			alimento.setQtGordura(Integer.parseInt(request.getParameter("gordura")));
			alimento.setQtProteina(Integer.parseInt(request.getParameter("proteina")));
			

			alimentoDao.update(alimento);

			RequestDispatcher rd = request.getRequestDispatcher("listaAlimentoBusca.jsp"); 
			rd.forward(request, response);


		}catch(Exception e) {
			e.printStackTrace();
		}

	}
		
	

}
