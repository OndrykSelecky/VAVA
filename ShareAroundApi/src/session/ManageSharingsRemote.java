package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Group;
import entity.Sharing;
import entity.SharingType;
import entity.User;

@Remote
public interface ManageSharingsRemote {
	
	public List<Sharing> getNewSharings(Group group);
	
	public List<Sharing> getNewSharingsByType(SharingType type, Group group);

	public List<SharingType> getSharingTypes();

	public List<Sharing> getSharingsByUser(User user);

	public int setInvalid(Sharing sharing);

	public List<Sharing> getNewSharingsByUserAndType(SharingType sharingType, User user);

	public void addSharing(Sharing sharing);
	
	public List<String> getTagsOfImage(String encodedImg);
}
