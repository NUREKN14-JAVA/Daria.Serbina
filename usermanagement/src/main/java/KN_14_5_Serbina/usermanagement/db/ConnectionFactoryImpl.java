package KN_14_5_Serbina.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryImpl implements ConnectionFactory {

	@Override
	public Connection createConnection() throws DatabaseException {
		String url = "jdbc:hsqldb:file:db/usermanagement";
		String user = "sa";
		String password = "";
		String driver = "org.hsqldb.jdbcDriver";
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