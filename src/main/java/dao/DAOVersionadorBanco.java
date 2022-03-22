package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Connection connection = null;

	public DAOVersionadorBanco() {
		this.connection = SingleConnectionBanco.getConnection() ;
	}
	
	
	public void gravarArquivoSqlRodado(String nomeArquivo) throws Exception {
		String sql = "insert into versionadorbanco (arquivo_sql) values (?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nomeArquivo);
		statement.execute();
	}
	
	
	public boolean arquivoSqlRodado(String nomeDoArquivo)throws Exception {
		String sql = "select count(1) > 0 as rodado from versionadorbanco where arquivo_sql = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nomeDoArquivo);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		return resultSet.getBoolean("rodado");
	}
	
	
	

}
