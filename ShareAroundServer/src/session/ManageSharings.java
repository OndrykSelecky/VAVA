package session;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import entity.Group;
import entity.Sharing;
import entity.SharingType;
import entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Session Bean implementation class ManageSharings. Manages connection with database for queries about Sharings
 * @author ondryk
 *
 */
@Stateless
public class ManageSharings implements ManageSharingsRemote {

	private static final Logger log = Logger.getLogger(ManageSharings.class.getName());
	
	@PersistenceContext
	private EntityManager manager;

	
	public List<Sharing> getNewSharings(Group group) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharings", Sharing.class);
		List<Sharing> result;
		
		try{
			query.setParameter("group", group);
			result = query.getResultList();
			log.info(result.size() + " sharings returned for group " +
					 group + " (name = " + group.getName() + ", id = " + group.getId() + ")");
		}
		catch (Exception e){
			log.error("Getting new sharings of group " +
					 group + " (name = " + group.getName() + ", id = " + group.getId() + ")", e);
			result = Collections.emptyList();
		}
		return result;
	}

	public List<Sharing> getSharingsByUser(User user) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharings", Sharing.class);
		List<Sharing> result;
		
		try{
			query.setParameter("user", user);
			result = query.getResultList();
			log.info(result.size() + " sharings returned for user " +
					user + " (name = " + user.getUserName() + ", id = " + user.getId() + ")");
		}
		catch (Exception e){
			log.error("Getting new sharings of group " +
					user + " (name = " + user.getUserName() + ", id = " + user.getId() + ")", e);
			result = Collections.emptyList();
		}

		return result;
	}

	public List<Sharing> getNewSharingsByType(SharingType type, Group group) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharingsByType", Sharing.class);

		
		List<Sharing> result;
		
		try{
			query.setParameter("group", group);
			query.setParameter("type", type);
			result = query.getResultList();
			log.info(result.size() + " sharings returned for group " +
					 group + " (name = " + group.getName() + ", id = " + group.getId() + ") and type " + type.getName());
		}
		catch (Exception e){
			log.error("Getting new sharings of group " +
					 group + " (name = " + group.getName() + ", id = " + group.getId() + ") and type " + type.getName(), e);
			result = Collections.emptyList();
		}

		return result;
	}

	public List<Sharing> getNewSharingsByUserAndType(SharingType type, User user) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharingsByType", Sharing.class);		
		List<Sharing> result;
		
		try{
			query.setParameter("type", type);
			query.setParameter("user", user);
			result = query.getResultList();
			log.info(result.size() + " sharings returned for user " +
					user + " (name = " + user.getUserName() + ", id = " + user.getId() + ") and type " + type.getName());
		}
		catch (Exception e){
			log.error("Getting new sharings of group " +
					user + " (name = " + user.getUserName() + ", id = " + user.getId() + ") and type " + type.getName(), e);
			result = Collections.emptyList();
		}

		return result;
	}

	public List<SharingType> getSharingTypes() {
		
		TypedQuery<SharingType> query = manager.createNamedQuery("entity.sharingType.getAll", SharingType.class);
		 
		
		try {		
			
			List<SharingType> sharingTypeList = query.getResultList();
			log.error(sharingTypeList.size() + " sharing types read from database");
			
			return sharingTypeList;
		}
		catch (Exception e)
		{
			log.error("Getting list of sharing types ", e);
		}
				
		return Collections.emptyList();

	}

	public void addSharing(Sharing sharing) {
		
		
		try {
			manager.persist(sharing);
			log.info("Added sharing " + sharing + " (user.id = " + sharing.getUser().getId() + ", username = " + 
			sharing.getUser().getUserName() + ")");
		}
		catch (EntityExistsException e){
			log.error("Added sharing " + sharing + " (user.id = " + sharing.getUser().getId() + ", username = " + 
			sharing.getUser().getUserName() + "): already exists", e);
		}
		catch (Exception e){
			log.error("Added sharing " + sharing + " (user.id = " + sharing.getUser().getId() + ", username = " + 
					sharing.getUser().getUserName() + ")", e);
		}
	}


	public int setInvalid(Sharing sharing) {
		Query query = manager.createNamedQuery("entity.sharing.setInvalid");
		try{
			query.setParameter("id", sharing.getId());
			log.info("State od sharing change to not active " + sharing + " (id = " +
					 sharing.getId() + ", user " + sharing.getUser().getId() + ")");
			return query.executeUpdate();
		}
		catch (Exception e)
		{
			log.error("setting state of sharing " + sharing + " (id = " +
					 sharing.getId() + ", user " + sharing.getUser().getId() + ")", e);
		}
		
		return 0;
	}

	@Override
	public List<String> getTagsOfImage(String encodedImg) {
		List<String> tags = new ArrayList<>();

		String stringified = new JSONObject()
				.put("requests",
						new JSONArray().put(new JSONObject().put("image", new JSONObject().put("content", encodedImg))
								.put("features", new JSONArray().put(new JSONObject().put("type", "LABEL_DETECTION")))))
				.toString();
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://vision.googleapis.com/v1/images:annotate")
					.queryString("key", "AIzaSyCAz5GBolSSLvlygeKoptWT62jJiCrrWxw").body(stringified).asJson();

			JSONArray tagArray = jsonResponse.getBody().getObject().getJSONArray("responses").getJSONObject(0)
					.getJSONArray("labelAnnotations");
			
			for (int i = 0; i < tagArray.length(); i++) {
				tags.add(tagArray.getJSONObject(i).getString("description"));
			}

			log.info( tags.size() + " tags found for image");
			
			return tags;
		} catch (UnirestException e) {
			log.error("Error getting tags for image", e);
		}

		return Collections.emptyList();
	}

}
