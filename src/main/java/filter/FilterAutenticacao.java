package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.SingleConnectionBanco;
import dao.DAOVersionadorBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
      
	private static Connection connection;
	
	
    public FilterAutenticacao() {
    
    }

	/*Encerra os processos quando o servidor � parado*/
    /*Mataria os processos de conex�o com o banco*/
    
    /*FILTERS S� SAO CAPAZES DE FILTAR PASTAS DE ARQUIVOS COMO NO EXEMPLO @WebFilter(urlPatterns = {"/principal/*"}) O FILTER SO IR� INTERCEPTAR OS ARQUIVOS APARTIR DO DIRETORIO /principal*/
    
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*Intercepta as requisi��es e as respostas no sistema*/
	/*Tudo oque for feito no sistema vai passar por aqui*/
	/*Valida��o de autentica��o*/
	/*Dar commit e rolback de transa��es com o banco*/
	/*Validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String UsuarioLogado = (String) session.getAttribute("usuario");/*Pegando o Atirbuto da Sess�o*/
			
			String UrlParaAutenticar = req.getServletPath();/*Url que esta sendo acessada*/
			
			if(UsuarioLogado == null && !UrlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) /*N�o Esta Logado*/{
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + UrlParaAutenticar);
				request.setAttribute("msg", "Por favor! Fa�a o Login!");
				redireciona.forward(request, response);
				
				return; /*Para execu��o e redirecionamento para o login*/
			}else {
				
				chain.doFilter(request, response);
			}
			
			connection.commit(); /*Deu tudo certo Commita no Banco*/
		}catch(Exception ex) {
			ex.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Causa da Exce��o: "+ex.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*Inicia a conex�o com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
		
		DAOVersionadorBanco daoVersionadorBanco = new DAOVersionadorBanco();
		
		String caminhoPastaSql = fConfig.getServletContext().getRealPath("versionadorbancosql")+File.separator;
		
		File [] filesSql = new File(caminhoPastaSql).listFiles();
		
		try {
		for (File file : filesSql) {
			
			boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
			
			if(!arquivoJaRodado) {
				
				FileInputStream entradaArquivo = new FileInputStream(file);
				Scanner lerArquivo = new Scanner(entradaArquivo,"UTF-8");
				
				StringBuilder sql = new StringBuilder();
				
				while(lerArquivo.hasNext()) {
					sql.append(lerArquivo.nextLine());
					sql.append("\n");
				}
				
				if(sql.toString() != null && !sql.toString().isEmpty()) {
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.gravarArquivoSqlRodado(file.getName());
					connection.commit();
				}
				lerArquivo.close();
			}
		}
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	
	

}
