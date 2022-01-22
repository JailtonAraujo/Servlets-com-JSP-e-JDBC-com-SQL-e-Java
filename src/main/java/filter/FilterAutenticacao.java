package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
      
	private static Connection connection;
	
	
    public FilterAutenticacao() {
    
    }

	/*Encerra os processos quando o servidor é parado*/
    /*Mataria os processos de conexão com o banco*/
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*Intercepta as requisições e as respostas no sistema*/
	/*Tudo oque for feito no sistema vai passar por aqui*/
	/*Validação de autenticação*/
	/*Dar commit e rolback de transações com o banco*/
	/*Validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String UsuarioLogado = (String) session.getAttribute("usuario");/*Pegando o Atirbuto da Sessão*/
			
			String UrlParaAutenticar = req.getServletPath();/*Url que esta sendo acessada*/
			
			if(UsuarioLogado == null && !UrlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) /*Não Esta Logado*/{
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + UrlParaAutenticar);
				request.setAttribute("msg", "Por favor Realize o Login");
				redireciona.forward(request, response);
				
				return; /*Para execução e redirecionamento para o login*/
			}else {
				
				chain.doFilter(request, response);
			}
			
			connection.commit(); /*Deu tudo certo Commita no Banco*/
		}catch(Exception ex) {
			ex.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*Inicia a conexão com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
