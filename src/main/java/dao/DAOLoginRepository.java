package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean ValidarAutenticacao(ModelLogin modelLogin) {
		
		try {
			String sql = "select * from usuario where login = ? and senha = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				return true;/*Autenticado*/
			}else {
				return false;/*Não Autenticado*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
