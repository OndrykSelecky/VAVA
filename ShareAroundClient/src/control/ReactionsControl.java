package control;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import data.Data;
import entity.Reaction;
import entity.Sharing;
import entity.User;
import session.ManageReactionsRemote;

public class ReactionsControl {

	public static void getReactionsBySharing(Sharing sharing)
	{
		Data.reactions = new ArrayList<Reaction>();
		ManageReactionsRemote remote;
		try 
		{
			remote = (ManageReactionsRemote)Client.getContext().lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			Data.reactions = remote.getReactionsBySharing(sharing);
			
		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Get reactions of current user
	 */
	public static void getReactionsByUser()
	{
		Data.reactions = new ArrayList<Reaction>();
		ManageReactionsRemote remote;
		try 
		{
			remote = (ManageReactionsRemote)Client.getContext().lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			Data.reactions = remote.getReactionsByUser(Data.user);
			
		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static void addNewReaction(Reaction reaction)
	{
				
		try {
			ManageReactionsRemote remote = (ManageReactionsRemote)Client.getContext().lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			remote.addReaction(reaction);
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
