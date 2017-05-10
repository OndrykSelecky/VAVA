package data;

import java.util.List;

import entity.Group;
import entity.Reaction;
import entity.Sharing;
import entity.SharingType;
import entity.User;

/**
 * Datastore of our application.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class Data {

	public static User user;

	public static Group group;

	public static List<SharingType> sharingTypes;

	public static List<Sharing> sharings;

	public static List<Reaction> reactions;

	public static List<String> tags;
}
