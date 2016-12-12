package KN_14_5_Serbina.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	
	protected static final String USER_DAO = "dao.KN_14_5_Serbina.usermanagement.db.UserDao";
	protected static  Properties properties;
	
	private static DaoFactory instance;
	private static final String DAO_FACTORY = "dao.factory";
	
	static {
		properties = new Properties ();
		try {
			properties.load(DaoFactory.class.getClassLoader()
					.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			Class factoryClass;
			try {
				factoryClass = Class.forName(properties
						.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException (e);
			}
		}
		return instance;

	}
	
	protected DaoFactory() {
	
	}
	
	public static void init (Properties prop) {
		properties = prop;
		instance = null;
	}

	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
		}
	
	public abstract UserDao getUserDao ();
	/*
	  {
	
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory (getConnectionFactory());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	*/

}
