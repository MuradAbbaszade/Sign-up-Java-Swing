import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
	private String db_username = "root";
	private String db_password="";
	private String db_name="user_informations";
	private String host="localhost";
	private int port=3306;
	private Connection connection=null;
	private Statement statement =null;
	
	public DatabaseConnection(){
		String url = "jdbc:mysql://"+host+":"+port+"/"+db_name;
		try {
			connection = DriverManager.getConnection(url,db_username,db_password);
		} catch (SQLException e) {
			System.out.println("Connection lost.");
		}
		
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
}
