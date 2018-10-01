package br.ucsal.TesteQualidade.Murucsal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Autenticador")
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pessoa user = new Pessoa(null, null);
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		user.setNome(login);
		user.setSenha(senha);
		if (autenticarUser(user)) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("paginaInicial.jsp");
		} else {
			request.setAttribute("erro", "Usuario ou senha invalios!");
			RequestDispatcher d = request.getRequestDispatcher("paginaErro.jsp");
			d.forward(request, response);
		}
	}

	private boolean autenticarUser(Pessoa user) {
		boolean autenticado = false;
		if (user.getNome() != null && user.getSenha() != null && user.getNome().equals(user.getSenha())) {
			autenticado = true;
		}
		return autenticado;
	}

}
