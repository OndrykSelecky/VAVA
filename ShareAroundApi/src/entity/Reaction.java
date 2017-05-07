package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import entity.Sharing;


/**
 * Reakcia uûÌvateæa na zdieæanie.
 * @author ondryk
 *
 */
@Entity
@Table(name="reaction")
@NamedQueries({
@NamedQuery(name="entity.Reaction.getReactionsBySharing", query = "select r from Reaction r where :sharing = r.sharing order by r.date DESC"),
@NamedQuery(name="entity.Reaction.getReactionsByUser", query = "select r from Reaction r where :user = r.user order by r.date DESC"),
})
public class Reaction implements Serializable {

	
	private static final long serialVersionUID = -4356655808936398008L;
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private long id;
	
	/**
	 * UûÌvateæ, ktor˝ reakciu vytvoril
	 */
	@ManyToOne		
	private User user;
	
	
	/**
	 * Zdieæanie, na ktorÈ reaguje.
	 */
	@ManyToOne
	private Sharing sharing;
	
	/**
	 * Text spr·vy
	 */
	private String message;
	
	
	/**
	 * Reaguj˙ci urËÌ, ktorÈ kontaktnÈ ˙daje sa zobrazia autorovi zdieæania
	 */
	private Boolean showAddress;
	
	private Boolean showEmail;
	
	private Boolean showPhone;
	
	
	//zatiaæ neimplementovan· funkcionalita, malo sl˙ûiù na urËenie, Ëi uû autor zdieæania videl reakciu
	private Boolean isNew;
	
	private Date date;
	
	public Reaction()
	{
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
		this.isNew = true;		
	};

	public Reaction(User user, Sharing sharing, String message, Boolean showAddress, Boolean showEmail,
			Boolean showPhone) 
	{
		super();
		this.user = user;
		this.sharing = sharing;
		this.message = message;
		this.showAddress = showAddress;
		this.showEmail = showEmail;
		this.showPhone = showPhone;
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
		this.isNew = true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sharing getSharing() {
		return sharing;
	}

	public void setSharing(Sharing sharing) {
		this.sharing = sharing;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

		
	
	
	

}
