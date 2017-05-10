package control;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import data.Data;
import entity.Sharing;
import entity.SharingType;
import session.ManageSharingsRemote;
import control.Client;

/**
 * Groups all sharing actions.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class SharingsControl {

	private static Logger LOG = Logger.getLogger(SharingsControl.class.getName());

	/**
	 * Gets all sharings from one group.
	 */
	public static void getAllSharingsOfGroup() {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharings(Data.group);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Failed getting all sharings of one group.", e);
		}
	}

	/**
	 * Gets all sharings from one group for specific {@link SharingType}
	 * 
	 * @param sharingType
	 *            Sharing type which we want to filter.
	 */
	public static void getAllSharingsOfGroup(SharingType sharingType) {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharingsByType(sharingType, Data.group);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Failed getting all sharings of one group for specific sharing type.", e);
		}

	}

	/**
	 * Gets all sharings of one user for specific {@link SharingType}
	 * 
	 * @param sharingType
	 *            Sharing type which we want to filter.
	 */
	public static void getAllSharingsOfUser(SharingType sharingType) {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharingsByUserAndType(sharingType, Data.user);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Failed getting all sharings of user for specific sharing type.", e);
		}

	}

	/**
	 * Gets all sharings of one user
	 */
	public static void getAllSharingsOfUser() {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getSharingsByUser(Data.user);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Failed getting all sharings of user.", e);
		}

	}

	/**
	 * Sets sharing as invalid.
	 * 
	 * @param sharing
	 *            Sharing which we want to set as invalid.
	 */
	public static void setInvalid(Sharing sharing) {

		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			int updateCount = remote.setInvalid(sharing);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Setting sharing as invalid failed.", e);
		}

	}

	/**
	 * Add a new sharing.
	 * 
	 * @param sharing
	 *            Sharing to be added.
	 */
	public static void addNewSharing(Sharing sharing) {
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			remote.addSharing(sharing);

		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Adding a new sharing failed.", e);
		}

	}

	/**
	 * Gets tags for BASE64 encoded image.
	 * 
	 * @param encodedImg
	 *            BASE64 encoded image string
	 */
	public static void getTagsOfImage(String encodedImg) {
		ManageSharingsRemote remote;

		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.tags = remote.getTagsOfImage(encodedImg);
		} catch (NamingException e) {
			LOG.log(Level.SEVERE, "Getting tags for BASE64 encoded image failed.", e);
		}
	}

}
