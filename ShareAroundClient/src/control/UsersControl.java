package control;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Data;
import entity.User;
import session.ManageUserRemote;

public class UsersControl {
	
	public static User getUser(String name, String password)
	{
		User user = null;
		
		try {
			ManageUserRemote remote = (ManageUserRemote)Client.getContext().lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageUser!session.ManageUserRemote");
			user = remote.getByUserName(name, password);			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	
		return user;
		
	}

}
