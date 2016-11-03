package KN_14_5_Serbina.usermanagement.db;

import java.util.Collection;

import KN_14_5_Serbina.usermanagement.User;

public interface UserDao {
	User create (User user) throws DatabaseException;
	
	void update (User user) throws DatabaseException;
	
	void delete (User user) throws DatabaseException;

	void find (Long id) throws DatabaseException;

	Collection findAll() throws DatabaseException;	
}
