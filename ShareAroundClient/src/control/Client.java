package control;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import data.Data;
import session.ManageSharingsRemote;
import session.ManageUserRemote;
import view.Login;

public class Client {

	private static Context context;
	
	private static void init() throws NamingException
	{
		context = createRemoteEjbContext("localhost", "8080");
		ManageUserRemote remote = (ManageUserRemote)context.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageUser!session.ManageUserRemote");
				
		remote.fillDatabase();
		
		ManageSharingsRemote sharingRemote = (ManageSharingsRemote)context.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
		
		Data.sharingTypes = sharingRemote.getSharingTypes();
	}
	
	public static void main(String[] args) throws NamingException {
		
		init();
		
		Login window = new Login();
		window.setVisible(true);
		
	}
	
	public static Context getContext()
	{
		return context;
	}
		
 	private static Context createRemoteEjbContext(String host, String port) throws NamingException {
		Hashtable<Object, Object> props = new Hashtable<Object, Object>();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		
		props.put("jboss.naming.client.ejb.context", false);
		props.put("org.jboss.ejb.client.scoped.context", true);
 
		props.put("endpoint.name", "client-endpoint");
		props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
		props.put("remote.connections", "default");
		props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
 
        props.put(Context.PROVIDER_URL, "http-remoting://" + host + ":" + port);
        props.put("remote.connection.default.host", host);
        props.put("remote.connection.default.port", port);
 
        return new InitialContext(props);
    }


}
