package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entity.Tag;

@Entity
@Table(name="sharing")
@NamedQueries({
@NamedQuery(name="entity.sharing.getNewSharings", query = "select s from Sharing as s "
		+ "where :group in s.group AND s.active = true order by s.id DESC"),
@NamedQuery(name="entity.sharing.getNewSharingsByType", query = "select s from Sharing as s "
		+ " where :group in s.group AND s.active = true AND s.type = :type order by s.date DESC"),
@NamedQuery(name="entity.sharing.getMySharings", query = "select distinct s from Sharing as s LEFT JOIN FETCH s.reactions "
		+ " where s.user = :user order by s.date DESC"),
@NamedQuery(name="entity.sharing.getMySharingsByType", query = "select distinct s from Sharing as s LEFT JOIN FETCH s.reactions "
		+ " where s.user = :user AND s.active = true AND s.type = :type order by s.date DESC"),
@NamedQuery(name="entity.sharing.setInvalid", query="update Sharing set active = false WHERE id = :id"),
})
public class Sharing implements Serializable{

	
	private static final long serialVersionUID = -1012513616484821058L;
	
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private long id;
	
	
	/**
	 * Typ zdieæania. KaûdÈ m· jeden typ.
	 */
	@ManyToOne
	private SharingType type;
	
	
	/**
	 * UûÌvateæ, ktor˝ danÈ zdieæanie vytvoril
	 */
	@ManyToOne
	private User user;
	
	
	//Zatiaæ neimplementovanÈ, jednalo by sa o filtrovanie podæa tagov.
	@ManyToMany
	private Set<Tag> tags;
	
	
	/**
	 * Nadpis
	 */
	private String label;
	
	
	/**
	 * Popis
	 */
	private String description;
	
	/**
	 * Platnosù - Ëi je novÈ, alebo uû bolo zdieæanie ukonËenÈ.
	 */
	private Boolean active;
	
	private Date date;
	
	
	/**
	 * Cena, ak nejak· je. 	
	 */
	private String price;
	
	
	/**
	 * Skupina, vr·mci ktorej sa zobrazÌ
	 */
	@ManyToOne
	private Group group;
	
	
	/**
	 * Zoznam reakciÌ na zdieæanie.
	 */
	@OneToMany(mappedBy="sharing")
	private List<Reaction> reactions;
	
	
	private Boolean showAddress;
	
	private Boolean showEmail;
	
	private Boolean showPhone;

	
	public Sharing(){
		
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
		this.active = true;
	};
		
	
	public Sharing(SharingType type, User user, Set<Tag> tags, String label, String description, String price,
			Group group, Boolean showAddress, Boolean showEmail, Boolean showPhone) {
		super();
		this.type = type;
		this.user = user;
		this.tags = tags;
		this.description = description;
		this.active = true;
		this.price = price;
		this.group = group;
		this.showAddress = showAddress;
		this.showEmail = showEmail;
		this.showPhone = showPhone;
		this.label=label;
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SharingType getType() {
		return type;
	}

	public void setType(SharingType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isActive() {
		return active;
	}

	public void isActive(Boolean valid) {
		this.active = valid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	public Boolean getShowAddress() {
		return showAddress;
	}


	public void setShowAddress(Boolean showAddress) {
		this.showAddress = showAddress;
	}


	public Boolean getShowEmail() {
		return showEmail;
	}


	public void setShowEmail(Boolean showEmail) {
		this.showEmail = showEmail;
	}


	public Boolean getShowPhone() {
		return showPhone;
	}


	public void setShowPhone(Boolean showPhone) {
		this.showPhone = showPhone;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


		
	
}
