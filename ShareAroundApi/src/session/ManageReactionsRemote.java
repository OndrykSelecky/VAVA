package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Reaction;
import entity.Sharing;
import entity.User;

/**
 * Remote interface for session bean ManageReactions
 * @author ondryk
 *
 */
@Remote
public interface ManageReactionsRemote {

	/**
	 * Add new reaction into database. 
	 * @param reaction new reaction
	 */
	public void addReaction(Reaction reaction);

	/**
	 * Returns list of reactions on particular sharings.
	 * @param sharing sharing, for which reactions are needed
	 * @return list of reactions
	 */
	public List<Reaction> getReactionsBySharing(Sharing sharing);

	
	/**
	 * Returns list of reactions for user
	 * @param user user, for which reactions are needed
	 * @return list of reactions, entity Reaction
	 */
	public List<Reaction> getReactionsByUser(User user);

}
