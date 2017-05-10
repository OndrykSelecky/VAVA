package control;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import data.Data;
import session.ManageSharingsRemote;
import session.ManageUserRemote;
import utils.PropertiesWrapper;
import view.Login;

/**
 * The main entry class of our app.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class Client {

	private static Context context;
	private static Logger LOG = Logger.getLogger(Client.class.getName());

	private static void init() throws NamingException {
		context = createRemoteEjbContext(PropertiesWrapper.getProperties().getProperty("host"),
				PropertiesWrapper.getProperties().getProperty("port"));

		ManageUserRemote userRemote = (ManageUserRemote) context
				.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageUser!session.ManageUserRemote");

		ManageSharingsRemote sharingRemote = (ManageSharingsRemote) context
				.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");

		//userRemote.fillDatabase();
		Data.sharingTypes = sharingRemote.getSharingTypes();
	}

	public static void main(String[] args) {
		try {
			init();
			Login window = new Login();
			window.setVisible(true);
		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Initialization in main failed.", e);
		}
	}

	public static Context getContext() {
		return context;
	}

	private static Context createRemoteEjbContext(String host, String port) throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(jndiProperties);
	}

}
