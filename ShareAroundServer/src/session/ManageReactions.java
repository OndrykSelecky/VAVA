package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Reaction;
import entity.Sharing;
import entity.User;

/**
 * Session Bean implementation class ManageReactions
 */
@Stateless
public class ManageReactions implements ManageReactionsRemote {


	@PersistenceContext
	private EntityManager manager;
	
	public void addReaction(Reaction reaction)
	{
		manager.persist(reaction);
	}
	
	public List<Reaction> getReactionsBySharing(Sharing sharing)
	{
		TypedQuery<Reaction> query = manager.createNamedQuery("entity.Reaction.getReactionsBySharing", Reaction.class);
		query.setParameter("sharing", sharing);
		List<Reaction> result = query.getResultList();
		
		return result;
	}
	
	public List<Reaction> getReactionsByUser(User user)
	{
		TypedQuery<Reaction> query = manager.createNamedQuery("entity.Reaction.getReactionsByUser", Reaction.class);
		query.setParameter("user", user);
		List<Reaction> result = query.getResultList();
		
		return result;
	}
	

}
