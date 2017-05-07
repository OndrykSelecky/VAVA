package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Group;
import entity.Sharing;
import entity.SharingType;
import entity.User;

import java.util.List;

/**
 * Session Bean implementation class ManageSharings
 */
@Stateless
public class ManageSharings implements ManageSharingsRemote {

	@PersistenceContext
	private EntityManager manager; 
	
	public List<Sharing> getNewSharings(Group group)
	{
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharings", Sharing.class);
		query.setParameter("group", group);		
		List<Sharing> result = query.getResultList();
		
		return result;
	}
	
	public List<Sharing> getSharingsByUser(User user)
	{
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharings", Sharing.class);
		query.setParameter("user", user);
		List<Sharing> result = query.getResultList();
		
		return result;
	}
	
	public List<Sharing> getNewSharingsByType(SharingType type, Group group)
	{
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharingsByType", Sharing.class);
		query.setParameter("group", group);
		query.setParameter("type", type);
		List<Sharing> result = query.getResultList();
		
		return result;
	}
	
	public List<Sharing> getNewSharingsByUserAndType(SharingType type, User user)
	{
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharingsByType", Sharing.class);
		query.setParameter("type", type);
		query.setParameter("user", user);
		List<Sharing> result = query.getResultList();
		
		return result;
	}
	
	public List<SharingType> getSharingTypes()
	{
		TypedQuery<SharingType> query = manager.createNamedQuery("entity.sharingType.getAll", SharingType.class);
		List<SharingType> sharingTypeList = query.getResultList();
		
		return sharingTypeList;
		
	}
	
	public void addSharing(Sharing sharing)
	{
		manager.persist(sharing);
	}
	
	/**
	 * Nastaví zdie¾anie ako neplatné
	 * @param sharing
	 * @return
	 */
	public int setInvalid(Sharing sharing)
	{
		Query query = manager.createNamedQuery("entity.sharing.setInvalid");
		query.setParameter("id", sharing.getId());
		int changeCount = query.executeUpdate();
		return changeCount;
	}

}
