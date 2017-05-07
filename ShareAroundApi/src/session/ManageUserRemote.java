package session;

import javax.ejb.Remote;

import entity.User;

@Remote
public interface ManageUserRemote {
		
	
	public void fillDatabase();
	
	public User getByUserName(String userName, String password);

}
