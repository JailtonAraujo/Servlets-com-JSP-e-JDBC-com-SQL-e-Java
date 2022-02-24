package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.endereco;
import model.fotoUser;

public class DAOUsuarioRepository {

	private Connection conncetion;

	public DAOUsuarioRepository() {
		conncetion = SingleConnectionBanco.getConnection();
	}

	public ModelLogin Salvar(ModelLogin modelLogin, long usuario_id) throws Exception {

		if (modelLogin.isNew()) {

			String sql = "insert into usuario (login, senha, email, nome, usuario_id, perfil, sexo, endereco_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
			String sql_endereco = "insert into endereco (cep, logradouro, bairro, localidade, uf, numero, complemento) values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conncetion.prepareStatement(sql_endereco, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, modelLogin.getEndereco().getCep());
			statement.setString(2, modelLogin.getEndereco().getLogradouro());
			statement.setString(3, modelLogin.getEndereco().getBairro());
			statement.setString(4, modelLogin.getEndereco().getLocalidade());
			statement.setString(5, modelLogin.getEndereco().getUf());
			statement.setInt(6, modelLogin.getEndereco().getNumero());
			statement.setString(7, modelLogin.getEndereco().getComplemento());
			
			statement.execute();
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			int idContato =resultSet.getInt(1);

			statement = conncetion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getNome());
			statement.setLong(5, usuario_id);
			statement.setString(6, modelLogin.getPerfil());
			statement.setString(7, modelLogin.getSexo());
			statement.setInt(8, idContato);

			statement.execute();
			resultSet = statement.getGeneratedKeys();
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
			int idEndereco = 0;	
			String sql_endereco = "";
			PreparedStatement statement = null;
			
			if(modelLogin.getEndereco().getId() == 0) {
				sql_endereco = "insert into endereco (cep, logradouro, bairro, localidade, uf, numero, complemento) values (?, ?, ?, ?, ?, ?, ?)";
				
				statement = conncetion.prepareStatement(sql_endereco, Statement.RETURN_GENERATED_KEYS);
				
				statement.setLong(1, modelLogin.getEndereco().getCep());
				statement.setString(2, modelLogin.getEndereco().getLogradouro());
				statement.setString(3, modelLogin.getEndereco().getBairro());
				statement.setString(4, modelLogin.getEndereco().getLocalidade());
				statement.setString(5, modelLogin.getEndereco().getUf());
				statement.setInt(6, modelLogin.getEndereco().getNumero());
				statement.setString(7, modelLogin.getEndereco().getComplemento());
				
				statement.executeUpdate();
				ResultSet resultSet = statement.getGeneratedKeys();
				resultSet.next();
				idEndereco = resultSet.getInt(1);
				
			}else {
				
				sql_endereco = "update endereco set cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?, complemento =? where id = ?";
				statement = conncetion.prepareStatement(sql_endereco, Statement.RETURN_GENERATED_KEYS);
				
				statement.setLong(1, modelLogin.getEndereco().getCep());
				statement.setString(2, modelLogin.getEndereco().getLogradouro());
				statement.setString(3, modelLogin.getEndereco().getBairro());
				statement.setString(4, modelLogin.getEndereco().getLocalidade());
				statement.setString(5, modelLogin.getEndereco().getUf());
				statement.setInt(6, modelLogin.getEndereco().getNumero());
				statement.setString(7, modelLogin.getEndereco().getComplemento());
				statement.setInt(8, modelLogin.getEndereco().getId());
				statement.executeUpdate();
			}
			
			
			
			
			String sql = "";
			if(modelLogin.getEndereco().getId() == 0) {
				sql = "update usuario set login=?, senha=?, email=?, nome=?, perfil =?, sexo = ?, endereco_id = ? where idusuario = ?;";
				
				statement = conncetion.prepareStatement(sql);

				statement.setString(1, modelLogin.getLogin());
				statement.setString(2, modelLogin.getSenha());
				statement.setString(3, modelLogin.getEmail());
				statement.setString(4, modelLogin.getNome());
				statement.setString(5, modelLogin.getPerfil());
				statement.setString(6, modelLogin.getSexo());
				statement.setInt(7, idEndereco);
				statement.setLong(8, modelLogin.getId());
			}else {			
				sql = "update usuario set login=?, senha=?, email=?, nome=?, perfil =?, sexo = ? where idusuario = ?;";
				
				statement = conncetion.prepareStatement(sql);

				statement.setString(1, modelLogin.getLogin());
				statement.setString(2, modelLogin.getSenha());
				statement.setString(3, modelLogin.getEmail());
				statement.setString(4, modelLogin.getNome());
				statement.setString(5, modelLogin.getPerfil());
				statement.setString(6, modelLogin.getSexo());
				statement.setLong(7, modelLogin.getId());
			}
			
			statement.executeUpdate();

			if (modelLogin.getFotouser() != null) {

				sql = "update usuario set fotouser = ?, fotouserextensao = ? where idusuario = ? ";
				statement = conncetion.prepareStatement(sql);
				statement.setString(1, modelLogin.getFotouser().getCodFoto());
				statement.setString(2, modelLogin.getFotouser().getExtensao());
				statement.setLong(3, modelLogin.getId());
				
				statement.execute();
				
			}
			
			conncetion.commit();
		}
		return this.ConsultarUsuario(modelLogin.getLogin(), usuario_id);
	}

	public List<ModelLogin> consultarUsuarioList(String nome, long usuario_id) throws Exception {

		List<ModelLogin> ListaDeUsuarios = new ArrayList<ModelLogin>();

		String sql = "select idusuario, login, email, nome, perfil, sexo, endereco_id from usuario where nome like ? and useradmin is false and usuario_id = ?;";

		PreparedStatement statement = conncetion.prepareStatement(sql);

		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, usuario_id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			endereco endereco = new endereco();
			
			modelLogin.setId(resultSet.getLong("idusuario"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			endereco.setId(resultSet.getInt("endereco_id"));
			
			modelLogin.setEndereco(endereco);

			ListaDeUsuarios.add(modelLogin);
		}
		return ListaDeUsuarios;
	}
	
	public List<ModelLogin> consultarUsuarioListPaginado(long usuario_id, Integer offset) throws Exception {

		List<ModelLogin> ListaDeUsuarios = new ArrayList<ModelLogin>();

		String sql = "select idusuario, login, email, nome, perfil, sexo, endereco_id from usuario where useradmin is false and usuario_id = ? order by nome limit 5 offset "+offset;

		PreparedStatement statement = conncetion.prepareStatement(sql);
		statement.setLong(1, usuario_id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			endereco endereco = new endereco();
			
			modelLogin.setId(resultSet.getLong("idusuario"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			endereco.setId(resultSet.getInt("endereco_id"));
			
			modelLogin.setEndereco(endereco);

			ListaDeUsuarios.add(modelLogin);
		}
		return ListaDeUsuarios;
	}

	public ModelLogin ConsultarUsuario(String login, long usuario_id) {

		try {

			ModelLogin modelLogin = new ModelLogin();
			fotoUser fotouser = new fotoUser();
			endereco endereco = new endereco();

			String sql = "select usuario.*, endereco.* from usuario left join endereco on usuario.endereco_id = endereco.id where upper(login) = upper('" + login
					+ "') and useradmin is false and usuario_id = ? ";

			PreparedStatement statement = conncetion.prepareStatement(sql);
			statement.setLong(1, usuario_id);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				modelLogin.setId(rs.getInt("idusuario"));
				modelLogin.setLogin(rs.getString("login"));
				modelLogin.setSenha(rs.getString("senha"));
				modelLogin.setEmail(rs.getString("email"));
				modelLogin.setNome(rs.getString("nome"));
				modelLogin.setAdmin(rs.getBoolean("useradmin"));
				modelLogin.setPerfil(rs.getString("perfil"));
				modelLogin.setSexo(rs.getString("sexo"));
				
				endereco.setId(rs.getInt("id"));
				endereco.setCep(rs.getLong("cep"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setLocalidade(rs.getString("localidade"));
				endereco.setUf(rs.getString("uf"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				
				fotouser.setCodFoto(rs.getString("fotouser"));
				fotouser.setExtensao(rs.getString("fotouserextensao"));
				
				modelLogin.setEndereco(endereco);
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
		endereco endereco = new endereco();

		String sql = "select usuario.*, endereco.* from usuario left join endereco on usuario.endereco_id = endereco.id where idusuario = ? and useradmin is false and usuario_id=?";

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
			
			endereco.setId(resultSet.getInt("id"));
			endereco.setCep(resultSet.getLong("cep"));
			endereco.setLogradouro(resultSet.getString("logradouro"));
			endereco.setBairro(resultSet.getString("bairro"));
			endereco.setLocalidade(resultSet.getString("localidade"));
			endereco.setUf(resultSet.getString("uf"));
			endereco.setNumero(resultSet.getInt("numero"));
			endereco.setComplemento(resultSet.getString("complemento"));
			
			usuario.setEndereco(endereco);
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
	
	
	public int totalPagina(Long usuarioLogado) throws SQLException {
		String sql = "select count(1) as total from usuario where usuario_id ="+usuarioLogado;
		
		PreparedStatement statement = conncetion.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		double cadastros = resultSet.getDouble("total");
		
		double porpagina = 5.0;
		
		double pagina = cadastros / porpagina;
		
		double restoDaDivisao = pagina % 2;
		
		if(restoDaDivisao > 0) {
			pagina++;
		}
		
		int paginanova = (int) pagina;
		
		return paginanova;
	}

}
