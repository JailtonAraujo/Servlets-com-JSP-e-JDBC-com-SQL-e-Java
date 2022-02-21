package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.fotoUser;

public class DAOUsuarioRepository {

	private Connection conncetion;

	public DAOUsuarioRepository() {
		conncetion = SingleConnectionBanco.getConnection();
	}

	public ModelLogin Salvar(ModelLogin modelLogin, long usuario_id) throws Exception {

		if (modelLogin.isNew()) {

			String sql = "insert into usuario (login, senha, email, nome, usuario_id, perfil, sexo) values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conncetion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getNome());
			statement.setLong(5, usuario_id);
			statement.setString(6, modelLogin.getPerfil());
			statement.setString(7, modelLogin.getSexo());

			statement.execute();
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			long id = resultSet.getLong(1);
			conncetion.commit();

			if (modelLogin.getFotouser() != null) {

				sql = "update usuario set fotouser = ?, fotouserextensao = ? where idusuario = ? ";
				statement = conncetion.prepareStatement(sql);
				statement.setString(1, modelLogin.getFotouser().getCodFoto());
				statement.setString(2, modelLogin.getFotouser().getExtensao());
				statement.setLong(3, id);
				
				statement.execute();
				conncetion.commit();
			}

		} else {
			String sql = "update usuario set login=?, senha=?, email=?, nome=?, perfil =?, sexo = ? where idusuario = ?;";

			PreparedStatement statement = conncetion.prepareStatement(sql);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getNome());
			statement.setString(5, modelLogin.getPerfil());
			statement.setString(6, modelLogin.getSexo());
			statement.setLong(7, modelLogin.getId());

			statement.executeUpdate();
			conncetion.commit();

			if (modelLogin.getFotouser() != null) {

				sql = "update usuario set fotouser = ?, fotouserextensao = ? where idusuario = ? ";
				statement = conncetion.prepareStatement(sql);
				statement.setString(1, modelLogin.getFotouser().getCodFoto());
				statement.setString(2, modelLogin.getFotouser().getExtensao());
				statement.setLong(3, modelLogin.getId());
				
				statement.execute();
				conncetion.commit();
			}
		}
		return this.ConsultarUsuario(modelLogin.getLogin(), usuario_id);
	}

	public List<ModelLogin> consultarUsuarioList(String nome, long usuario_id) throws Exception {

		List<ModelLogin> ListaDeUsuarios = new ArrayList<ModelLogin>();

		String sql = "select idusuario, login, email, nome, perfil, sexo, fotouser, fotouserextensao from usuario where nome like ? and useradmin is false and usuario_id = ?;";

		PreparedStatement statement = conncetion.prepareStatement(sql);

		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, usuario_id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			fotoUser fotouser = new fotoUser();
			
			modelLogin.setId(resultSet.getLong("idusuario"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			fotouser.setCodFoto(resultSet.getString("fotouser"));
			fotouser.setExtensao(resultSet.getString("fotouserextensao"));
			
			modelLogin.setFotouser(fotouser);
			

			ListaDeUsuarios.add(modelLogin);
		}
		return ListaDeUsuarios;
	}

	public ModelLogin ConsultarUsuario(String login, long usuario_id) {

		try {

			ModelLogin modelLogin = new ModelLogin();
			fotoUser fotouser = new fotoUser();

			String sql = "select * from usuario where upper(login) = upper('" + login
					+ "') and useradmin is false and usuario_id = ? ";

			PreparedStatement statement = conncetion.prepareStatement(sql);
			statement.setLong(1, usuario_id);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				modelLogin.setId(rs.getInt(1));
				modelLogin.setLogin(rs.getString(2));
				modelLogin.setSenha(rs.getString(3));
				modelLogin.setEmail(rs.getString(4));
				modelLogin.setNome(rs.getString(5));
				modelLogin.setAdmin(rs.getBoolean("useradmin"));
				modelLogin.setPerfil(rs.getString("perfil"));
				modelLogin.setSexo(rs.getString("sexo"));
				
				fotouser.setCodFoto(rs.getString("fotouser"));
				fotouser.setExtensao(rs.getString("fotouserextensao"));
				
				modelLogin.setFotouser(fotouser);

			}

			return modelLogin;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public ModelLogin ConsultarUsuarioLogado(String login) {

		try {

			ModelLogin modelLogin = new ModelLogin();
			//fotoUser fotouser = new fotoUser();

			String sql = "select * from usuario where upper(login) = upper('" + login + "') ";

			PreparedStatement statement = conncetion.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				modelLogin.setId(rs.getInt(1));
				modelLogin.setLogin(rs.getString(2));
				modelLogin.setSenha(rs.getString(3));
				modelLogin.setEmail(rs.getString(4));
				modelLogin.setNome(rs.getString(5));
				modelLogin.setAdmin(rs.getBoolean("useradmin"));
				modelLogin.setPerfil(rs.getString("perfil"));
				modelLogin.setSexo(rs.getString("sexo"));
				
				//fotouser.setCodFoto(rs.getString("fotouser"));
				//fotouser.setExtensao(rs.getString("fotouserextensao"));
				
				//modelLogin.setFotouser(fotouser);

			}

			return modelLogin;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public ModelLogin buscarUsuarioPorId(String id, long usuario_id) throws Exception {

		ModelLogin usuario = new ModelLogin();
		fotoUser fotouser = new fotoUser();

		String sql = "select * from usuario where idusuario = ? and useradmin is false and usuario_id=?";

		PreparedStatement statement = conncetion.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, usuario_id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			usuario.setId(resultSet.getLong("idusuario"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuario.setSexo(resultSet.getString("sexo"));
			
			fotouser.setCodFoto(resultSet.getString("fotouser"));
			fotouser.setExtensao(resultSet.getString("fotouserextensao"));
			
			usuario.setFotouser(fotouser);
		}

		return usuario;

	}

	public boolean ValidarLogin(String login) throws Exception {
		String sql = "select count(*) as existe from usuario where upper(login) = upper('" + login + "');";

		PreparedStatement statement = conncetion.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();/* Para avançar nos resultados */

		return resultSet.getBoolean("existe");

	}

	public void deletar(String id) throws Exception {
		String sql = "delete from usuario where idusuario = ?";

		PreparedStatement statement = conncetion.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(id));

		statement.executeUpdate();

		conncetion.commit();

	}

}
