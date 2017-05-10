package session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import entity.Reaction;
import entity.Sharing;
import entity.User;

/**
 * Session bean for managing reactions
 * @author ondryk *
 */
@Stateless
public class ManageReactions implements ManageReactionsRemote {

	private static final Logger log = Logger.getLogger(ManageReactions.class.getName());

	@PersistenceContext
	private EntityManager manager;
	
	public void addReaction(Reaction reaction)
	{
		try {
			manager.persist(reaction);
			log.info("Added reaction " + reaction + " (user.id = " + reaction.getUser().getId() + ") on sharing " + reaction.getSharing() + 
					" (id = " + reaction.getSharing().getId() + ")");
		}
		catch (EntityExistsException e){
			log.error("Adding reaction " + reaction + " (user.id = " + reaction.getUser().getId() + ") on sharing " + reaction.getSharing() + 
					" (id = " + reaction.getSharing().getId() + "): already exists in database", e);
		}
		catch (Exception e){
			log.error("Adding reaction " + reaction + " (user.id = " + reaction.getUser().getId() + ") on sharing " + reaction.getSharing() + 
					" (id = " + reaction.getSharing().getId() + ")", e);
		}
		
	}
	
	public List<Reaction> getReactionsBySharing(Sharing sharing)
	{
		TypedQuery<Reaction> query = manager.createNamedQuery("entity.Reaction.getReactionsBySharing", Reaction.class);
		
		List<Reaction> result = new ArrayList<Reaction>();
		
		try {
			query.setParameter("sharing", sharing);
			result = query.getResultList();
			log.info( result.size() + " reactions found for sharing " + sharing + " (id = " + sharing.getId() + ")");
		}
		catch (Exception e){
			log.error("getting reactions for sharing " + sharing + " (id = " + sharing.getId() + ")" , e);
		}
		
		return result;
	}
	
	public List<Reaction> getReactionsByUser(User user)
	{
		List<Reaction> result = new ArrayList<Reaction>();
		
		TypedQuery<Reaction> query = manager.createNamedQuery("entity.Reaction.getReactionsByUser", Reaction.class);
		try{
			query.setParameter("user", user);
			result = query.getResultList();
			log.info( result.size() + " reactions found for user " + user + " (id = " + user.getId() + ")");
		}
		catch (Exception e){
			log.error("getting reactions for user " + user + " (id = " + user.getId() + ")" , e);
		}
		
		return result;
	}
	

}
