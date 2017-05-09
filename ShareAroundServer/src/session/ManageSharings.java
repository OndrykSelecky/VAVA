package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
 * Session Bean implementation class ManageSharings
 */
@Stateless
public class ManageSharings implements ManageSharingsRemote {

	@PersistenceContext
	private EntityManager manager;

	public List<Sharing> getNewSharings(Group group) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharings", Sharing.class);
		query.setParameter("group", group);
		List<Sharing> result = query.getResultList();

		return result;
	}

	public List<Sharing> getSharingsByUser(User user) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharings", Sharing.class);
		query.setParameter("user", user);
		List<Sharing> result = query.getResultList();

		return result;
	}

	public List<Sharing> getNewSharingsByType(SharingType type, Group group) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getNewSharingsByType", Sharing.class);
		query.setParameter("group", group);
		query.setParameter("type", type);
		List<Sharing> result = query.getResultList();

		return result;
	}

	public List<Sharing> getNewSharingsByUserAndType(SharingType type, User user) {
		TypedQuery<Sharing> query = manager.createNamedQuery("entity.sharing.getMySharingsByType", Sharing.class);
		query.setParameter("type", type);
		query.setParameter("user", user);
		List<Sharing> result = query.getResultList();

		return result;
	}

	public List<SharingType> getSharingTypes() {
		TypedQuery<SharingType> query = manager.createNamedQuery("entity.sharingType.getAll", SharingType.class);
		List<SharingType> sharingTypeList = query.getResultList();

		return sharingTypeList;

	}

	public void addSharing(Sharing sharing) {
		manager.persist(sharing);
	}

	/**
	 * Sets sharing as invalid
	 * 
	 * @param sharing
	 * @return
	 */
	public int setInvalid(Sharing sharing) {
		Query query = manager.createNamedQuery("entity.sharing.setInvalid");
		query.setParameter("id", sharing.getId());
		int changeCount = query.executeUpdate();
		return changeCount;
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

			return tags;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

}
