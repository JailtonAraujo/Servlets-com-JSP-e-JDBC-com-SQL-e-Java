package connection;


import java.sql.Connection;
import java.sql.DriverManager;



public class SingleConnectionBanco {
	
	private static String url= "jdbc:mysql://localhost:3306/java-servlet-bd?autoReconnect=true";
	private static String user = "root";
	private static String password = "";
	
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
				Class.forName("com.mysql.cj.jdbc.Driver");/*Carrega o Drive de Conexão do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);/*Para não efetuar alterações no banco sem comando*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
