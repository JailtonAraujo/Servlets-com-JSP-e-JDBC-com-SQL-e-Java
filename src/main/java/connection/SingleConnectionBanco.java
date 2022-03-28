package connection;


import java.sql.Connection;
import java.sql.DriverManager;



public class SingleConnectionBanco {
	
	private static String url= "jdbc:postgresql://ec2-3-225-213-67.compute-1.amazonaws.com:5432/d5h7nbvp72dh7q?sslmode=require&autoReconnect=true";
	private static String user = "aqrmspejntxsms";
	private static String password = "5e5d6f15f2a4a1fb3646d0403dff3c7f2b8a2c75aa8ffad98192b39a4aee581a";
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		Connectar();
	}
	
	public SingleConnectionBanco() {/*Quando tiver uma instancia vai conectar*/
		Connectar();
	}
	
	public static void Connectar() {
		
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");/*Carrega o Drive de Conexão do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);/*Para não efetuar alterações no banco sem comando*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
