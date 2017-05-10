package control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import entity.User;
import session.ManageUserRemote;

/**
 * Groups all user actions.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class UsersControl {

	private static Logger LOG = Logger.getLogger(UsersControl.class.getName());

	/**
	 * Gets user accroding to entered credentials.
	 * 
	 * @param name
	 *            The sername of user.
	 * @param password
	 *            The password of user
	 * @return user if everything went well or null if error was encountered.
	 */
	public static User getUser(String name, String password) {
		User user = null;

		try {
			ManageUserRemote remote = (ManageUserRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageUser!session.ManageUserRemote");
			user = remote.getByUserName(name, password);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Getting user failed.", e);
		}

		return user;

	}

}
