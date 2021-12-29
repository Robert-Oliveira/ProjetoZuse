package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.dao.impl.OraclePressaoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Pressao;


@WebServlet("/pressaoNovo")
public class PressaoCadastrarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public PressaoCadastrarController() {
        super();
 
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			
			String data = request.getParameter("dtAfericao");
			String hora = request.getParameter("hora");
			
			String dataHora = data + " " + hora;
			
			System.out.println(session.getAttribute("codigo"));

			OraclePressaoDao pressaoDao = new OraclePressaoDao();
			
			Pressao pressao = new Pressao();
			
			pressao.setDtAfericao(dataHora);
			pressao.setSistolica(Integer.parseInt(request.getParameter("sistolica")));
			pressao.setDiastolica(Integer.parseInt(request.getParameter("diastolica")));
			pressao.setOcorrencia(request.getParameter("notas"));
			pressao.setCdUsuario((Integer) session.getAttribute("codigo"));
			 
			
			RequestDispatcher rd = request.getRequestDispatcher("listaPressaoBusca.jsp"); 
			rd.forward(request, response);	
		
			
			pressaoDao.create(pressao);
			
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
	}

}
