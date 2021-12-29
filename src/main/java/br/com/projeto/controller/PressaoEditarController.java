package br.com.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.impl.OraclePressaoDao;
import br.com.projeto.model.Pressao;

@WebServlet("/pressaoEditar")
public class PressaoEditarController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PressaoEditarController() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("id"));


		Pressao pressao = new OraclePressaoDao().getById(codigo);

		request.setAttribute("pressao", pressao);

		RequestDispatcher rd = request.getRequestDispatcher("pressaoEditar.jsp");  
		rd.forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			OraclePressaoDao pressaoDao = new OraclePressaoDao();

			Pressao pressao = new Pressao();

			pressao.setSistolica(Integer.parseInt(request.getParameter("sistolica")));
			pressao.setDiastolica(Integer.parseInt(request.getParameter("diastolica")));
			pressao.setOcorrencia(request.getParameter("nota"));
			pressao.setCdAfericao(Integer.parseInt(request.getParameter("codigo")));

			pressaoDao.update(pressao);

			RequestDispatcher rd = request.getRequestDispatcher("listaPressaoBusca.jsp"); 
			rd.forward(request, response);


		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
