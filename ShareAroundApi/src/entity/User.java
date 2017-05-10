package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The user entity. It belong to a group and can have many shares.
 * 
 * @author ondryk
 *
 */
@Entity
@Table(name = "user1")
@NamedQuery(name = "entity.user.getByUserName", query = "select u from User u where u.userName = :userName and u.password = :password")
public class User implements Serializable {

	private static final long serialVersionUID = -3363120264501521428L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;

	private String lastName;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	/**
	 * Groups this user belongs to.
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "members")
	private List<Group> groups;

	/**
	 * Contact information
	 */
	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;

	private String email;

	private String phone;

	/**
	 * The list of sharings of this user.
	 */
	@OneToMany(mappedBy = "user")
	private List<Sharing> sharings;

	/**
	 * The reactions of this users.
	 */
	@OneToMany(mappedBy = "user")
	private List<Reaction> reactions;

	public User() {
	};

	public User(String firstName, String lastName, String userName, String password, Address address, String email,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Sharing> getSharings() {
		return sharings;
	}

	public void setSharings(List<Sharing> sharings) {
		this.sharings = sharings;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

}
