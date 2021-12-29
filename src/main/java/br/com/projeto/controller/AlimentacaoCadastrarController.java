package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.dao.impl.OracleAlimentoDao;
import br.com.projeto.dao.impl.OracleRefAlimentoDao;
import br.com.projeto.exception.DBException;
import br.com.projeto.model.Alimento;
import br.com.projeto.model.RefAlimento;


@WebServlet("/alimentacaoNovo")
public class AlimentacaoCadastrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlimentacaoCadastrarController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OracleAlimentoDao alimentoDao = new OracleAlimentoDao();
		
		List<Alimento> lstAlimento = alimentoDao.getAll();
		request.setAttribute("lstAlimento", lstAlimento);
		request.getRequestDispatcher("alimentacao.jsp").forward(request, response);


	}
	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int id = Integer.parseInt(request.getParameter("categoria"));

			OracleAlimentoDao alimentoDao = new OracleAlimentoDao();
			
			Alimento alimento = alimentoDao.getById(id);
			request.setAttribute("sali", alimento);
			List<Alimento> lstAlimento = alimentoDao.getAll();
			request.setAttribute("lstAlimento", lstAlimento);
			request.getRequestDispatcher("alimentacao.jsp").forward(request, response);
			
		}
			
	}

