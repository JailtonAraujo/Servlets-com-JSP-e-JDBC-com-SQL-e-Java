package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;
import dao.DAOVersionadorBanco;



@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
      
	private static Connection connection;
	
	
    public FilterAutenticacao() {
    
    }

	/*Encerra os processos quando o servidor é parado*/
    /*Mataria os processos de conexão com o banco*/
    
    /*FILTERS SÓ SAO CAPAZES DE FILTAR PASTAS DE ARQUIVOS COMO NO EXEMPLO @WebFilter(urlPatterns = {"/principal/*"}) O FILTER SO IRÁ INTERCEPTAR OS ARQUIVOS APARTIR DO DIRETORIO /principal*/
    
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
				request.setAttribute("msg", "Por favor! Faça o Login!");
				redireciona.forward(request, response);
				
				return; /*Para execução e redirecionamento para o login*/
			}else {
				
				chain.doFilter(request, response);
			}
			
			connection.commit(); /*Deu tudo certo Commita no Banco*/
		}catch(Exception ex) {
			ex.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Causa da Exceção: "+ex.getMessage());
			redirecionar.forward(request, response);
			
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
