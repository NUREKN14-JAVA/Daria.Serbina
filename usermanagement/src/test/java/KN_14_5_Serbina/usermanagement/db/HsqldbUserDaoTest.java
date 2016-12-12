package KN_14_5_Serbina.usermanagement.db;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import KN_14_5_Serbina.usermanagement.User;
import junit.framework.TestCase;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	 private HsqldbUserDao dao;
	 private ConnectionFactory connectionFactory;
	 
		@Before
		protected void setUp() throws Exception {
			super.setUp();
			
			dao = new HsqldbUserDao(connectionFactory);
			
		}
@Before
		

		 @Test
		    public void testUpdate() {
		        try {
		            User user = new User();
		            user.setId(1L);
		           
		            String newFirstName = "Kurt";
		            String newLastName = "Vonnegut";
		            Date newDate = new Date(1922,11,11);
		            user.setFirstName(newFirstName);
		            user.setLastName(newLastName);
		            user.setDateOfBirth(newDate);
		            
		            dao.update(user);
		            user = dao.find(user.getId());
		            assertEquals("Firstname fail", newFirstName, user.getFirstName());
		            assertEquals("Lastname fail", newLastName, user.getLastName());
		            assertEquals("Date fail", newDate, user.getDateOfBirth());
		        } catch (DatabaseException e) {
		            e.printStackTrace();
		            fail(e.toString());
		        }
		    }
		
		@Test
		public void testDelete() {
		 try {
			User user = new User();
			long id = 1L;
			user.setId(id);
				dao.delete(user);
			}catch(DatabaseException e){
				e.printStackTrace();
				fail(e.toString());
			}
		}
		 
		@Test
	    public void testFind() {
	        try {
	        	long id=2L;
	        	 String newFirstName = "Max";
	            String newLastName = "Brooks";
	            //Calendar cal = Calendar.getInstance();
	           // cal.add(1972,Calendar.NOVEMBER,17);
	           // SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	           // String formatted = format1.format(cal.getTime());
	            Calendar calendar = Calendar.getInstance();
	            calendar.set(1972, Calendar.NOVEMBER, 17);
	            //DateFormat format = DateFormat.getInstance();
	        	//Date date = format.parse("1972-11-17");
	            User user = dao.find(id);
	            //String formatted = format1.format(cal.getTime());
	            assertEquals("Firstname fail", newFirstName, user.getFirstName());
	            assertEquals("Lastname fail", newLastName, user.getLastName());
	            Calendar calendarFind = Calendar.getInstance();
	            calendarFind.setTime(user.getDateOfBirth());
	            assertEquals("Year fail", calendar.get(Calendar.YEAR), calendarFind.get(Calendar.YEAR));
	            assertEquals("Month fail", calendar.get(Calendar.MONTH), calendarFind.get(Calendar.MONTH));
	            assertEquals("Day fail", calendar.get(Calendar.DAY_OF_MONTH), calendarFind.get(Calendar.DAY_OF_MONTH));
	        } catch (DatabaseException e) {
	            e.printStackTrace();
	            fail(e.toString());
	        } 
	        long id = 3L;
	        try {
	            dao.find(id);   
	        } catch (DatabaseException e) {
	            assertEquals(e.getMessage().toString(), "Could not find the user with id="+id);
	            
	        }
	    }
		
		public void testFindAll(){
	try {
		Collection collection = dao.findAll();
		  assertNotNull("Collection is null", collection);
          assertEquals("Collection size.", 2, collection.size());
	} catch (DatabaseException e) {
		e.printStackTrace();
		fail(e.toString());
	}
}
		
		@Test
		public void testCreate() {
		 try {
			User user = new User();
			 user.setFirstName("John");
			 user.setLastName("Doe");
			 user.setDateOfBirth(new Date(1907,5,3));
			 assertNull(user.getId());
			 user = dao.create(user);
			 assertNotNull(user);
			 assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
		 
		 
		}

		@Override
		protected IDatabaseConnection getConnection() throws Exception {
			connectionFactory=new ConnectionFactoryImpl( "org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement", "sa", "");
			return new DatabaseConnection(connectionFactory.createConnection()) ;
		}
		
		@Override
		protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
			return dataSet;
		}



}