package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Reaction;
import entity.Sharing;
import entity.User;

@Remote
public interface ManageReactionsRemote {

	public void addReaction(Reaction reaction);

	public List<Reaction> getReactionsBySharing(Sharing sharing);

	public List<Reaction> getReactionsByUser(User user);

}
