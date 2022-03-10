package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection = null;
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public void gravarTelefone (ModelTelefone modelTelefone) throws SQLException {
		
		String sql = "insert into telefone (numero, idUsuarioPai, idUsuarioCad) values (?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelTelefone.getTelefone());
		statement.setLong(2, modelTelefone.getUsuario_pai().getId());
		statement.setLong(3, modelTelefone.getUsuario_cad().getId());
		
		statement.execute();
		connection.commit();
	}
	
	public void deleteFone (int idTelefone) throws SQLException {
		String sql = "delete from telefone where idTelefone = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idTelefone);
		
		statement.executeUpdate();
		connection.commit();
	}
	
	public List<ModelTelefone> listarTelefone(int idUserPai) throws Exception{
		
		String sql = "select * from telefone where idUsuarioPai = ?";
		List<ModelTelefone> listaTelefones = new ArrayList<ModelTelefone>();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idUserPai);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setTelefone(resultSet.getString("numero"));
			modelTelefone.setIdTelefone(resultSet.getInt("idTelefone"));
			modelTelefone.setUsuario_cad(daoUsuarioRepository.buscarUsuarioPorId(resultSet.getLong("idUsuarioCad")));
			
			modelTelefone.setUsuario_pai(daoUsuarioRepository.buscarUsuarioPorId(resultSet.getInt("idUsuarioPai")));
			
			listaTelefones.add(modelTelefone);
		}
		return listaTelefones;
		
	}
	
	public boolean ExistFone(String fone, long idUsuario) throws SQLException {
		String sql = "select count(1) > 0 as existe from telefone where idUsuarioPai = ? and numero = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, idUsuario);
		statement.setString(2, fone);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		
		return resultSet.getBoolean("existe");
		
	}
}
