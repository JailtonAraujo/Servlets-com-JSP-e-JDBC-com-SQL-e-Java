package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection conncetion;

	public DAOUsuarioRepository() {
		conncetion = SingleConnectionBanco.getConnection();
	}

	public ModelLogin Salvar(ModelLogin modelLogin) throws Exception {

		if (modelLogin.isNew()) {

			String sql = "insert into usuario (login, senha, email, nome) values (?, ?, ?, ?)";

			PreparedStatement statement = conncetion.prepareStatement(sql);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getNome());

			statement.execute();
			conncetion.commit();

		}else {
			String sql = "update usuario set login=?, senha=?, email=?, nome=? where idusuario = ?;";
			
			
			PreparedStatement statement = conncetion.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getNome());
			
			statement.setLong(5, modelLogin.getId());
			
			statement.executeUpdate();
			conncetion.commit();
		}
		return this.ConsultarUsuario(modelLogin.getLogin());
	}

	public ModelLogin ConsultarUsuario(String login) {

		try {

			ModelLogin modelLogin = new ModelLogin();

			String sql = "select * from usuario where upper(login) = upper('" + login + "') ";

			PreparedStatement statement = conncetion.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				modelLogin.setId(rs.getInt(1));
				modelLogin.setLogin(rs.getString(2));
				modelLogin.setSenha(rs.getString(3));
				modelLogin.setEmail(rs.getString(4));
				modelLogin.setNome(rs.getString(5));

			}

			return modelLogin;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public boolean ValidarLogin(String login) throws Exception {
		String sql = "select count(*) as existe from usuario where upper(login) = upper('" + login + "');";

		PreparedStatement statement = conncetion.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();/* Para avançar nos resultados */

		return resultSet.getBoolean("existe");

	}

}
