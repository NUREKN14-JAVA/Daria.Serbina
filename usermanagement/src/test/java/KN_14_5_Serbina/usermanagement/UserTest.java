package KN_14_5_Serbina.usermanagement;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private User user;
	private Date dateOfBirthd;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		user = new User();
		Calendar calendar= Calendar.getInstance();
		calendar.set(1996, Calendar.OCTOBER, 4);
		dateOfBirthd = calendar.getTime();
	}
	
	@Test
	public void testGetFullName() {
		user.setFirstName("Daria");
		user.setLastName("Serbina");
		assertEquals("Serbina, Daria", user.getFullName());
	}

	public void testGetAge() {
		user.setDateOfBirth(dateOfBirthd);
		assertEquals(2016-1996, user.getAge());
	}
}