package session;

import javax.ejb.Remote;

import entity.User;

/**
 * Remote interface for user bean
 * @author ondryk
 *
 */
@Remote
public interface ManageUserRemote {
		
	
	public void fillDatabase();
	
	/**
	 * get user by name and password
	 * @param userName username
	 * @param password password
	 * @return User found in database, null, if it doesn't exist, or if more users with
	 * same name and address were found
	 */
	public User getByUserName(String userName, String password);

}
