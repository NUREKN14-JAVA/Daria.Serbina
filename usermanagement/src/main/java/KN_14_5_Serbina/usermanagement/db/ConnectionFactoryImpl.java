package KN_14_5_Serbina.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
	}

	public ConnectionFactoryImpl() {
		
		this.driver = "org.hsqldb.jdbcDriver"; 
		this.url = "jdbc:hsqldb:file:db/usermanagement"; 
		this.user = "sa"; 
		this.password = "";
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName (driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection (url, user, password);
		} catch (Exception e) {
			throw new DatabaseException (e);
			
		}
	
		
	}

}
