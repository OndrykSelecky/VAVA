package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Group;
import entity.Sharing;
import entity.SharingType;
import entity.User;

@Remote
public interface ManageSharingsRemote {
	
	/**
	 * Returns list of sharings, which are actual and were published in group
	 * @param group	group, where sharings were published
	 * @return list of Sharing
	 */
	public List<Sharing> getNewSharings(Group group);
	
	/**
	 * Returns list of sharings, which have same type and are from one group
	 * @param type type of sharing
	 * @param group group
	 * @return list of Sharing
	 */
	public List<Sharing> getNewSharingsByType(SharingType type, Group group);

	/**
	 * Get all sharing types in list
	 * @return list of sharing types
	 */
	public List<SharingType> getSharingTypes();

	/**
	 * Get all sharings of user
	 * @param user user, for which sharings are needed
	 * @return list of sharings
	 */
	public List<Sharing> getSharingsByUser(User user);

	/**
	 * Set sharing as invalid. After that, it will be no longer showed in new sharings
	 * @param sharing
	 * @return number of changed rows in database
	 */
	public int setInvalid(Sharing sharing);

	/**
	 * Returns list of sharings, which have same type and are from one user
	 * @param sharingType
	 * @param user
	 * @return list of sharings
	 */
	public List<Sharing> getNewSharingsByUserAndType(SharingType sharingType, User user);

	/**
	 * add new sharing in database
	 * @param sharing
	 */
	public void addSharing(Sharing sharing);
	
	/**
	 * get tags of image
	 * @param encodedImg image
	 * @return list of tags as string
	 */
	public List<String> getTagsOfImage(String encodedImg);
}
