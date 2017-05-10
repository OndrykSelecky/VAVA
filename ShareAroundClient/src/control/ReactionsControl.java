package control;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import data.Data;
import entity.Reaction;
import entity.Sharing;
import session.ManageReactionsRemote;

/**
 * Groups all reaction actions.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class ReactionsControl {

	private static Logger LOG = Logger.getLogger(ReactionsControl.class.getName());

	/**
	 * Gets reactions of one specific sharing.
	 * 
	 * @param sharing
	 *            Sharing for which we want to get reactions
	 */
	public static void getReactionsBySharing(Sharing sharing) {
		Data.reactions = new ArrayList<Reaction>();
		ManageReactionsRemote remote;
		try {
			remote = (ManageReactionsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			Data.reactions = remote.getReactionsBySharing(sharing);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Getting reactions by sharing failed.", e);
		}
	}

	/**
	 * Get reactions of the current user. Sets them into reactions field in
	 * {@link data.Data}}.
	 */
	public static void getReactionsByUser() {
		Data.reactions = new ArrayList<Reaction>();
		ManageReactionsRemote remote;
		try {
			remote = (ManageReactionsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			Data.reactions = remote.getReactionsByUser(Data.user);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Getting reactions by user failed.", e);
		}

	}

	/**
	 * Adds new reaction.
	 * 
	 * @param reaction
	 *            The reaction to be added.
	 */
	public static void addNewReaction(Reaction reaction) {

		try {
			ManageReactionsRemote remote = (ManageReactionsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageReactions!session.ManageReactionsRemote");
			remote.addReaction(reaction);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Failed adding new reaction.", e);
		}

	}

}
