package KN_14_5_Serbina.usermanagement.db;

import static org.junit.Assert.*;

import org.junit.Test;

import KN_14_5_Serbina.usermanagement.db.DaoFactory;
import KN_14_5_Serbina.usermanagement.db.UserDao;

public class DaoFactoryTest {

	@Test
	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull ("DaoFactory instance is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull ("UserDao instance is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
