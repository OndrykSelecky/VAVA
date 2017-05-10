package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Group with a name, it can be a dormitory or workplace
 * 
 * @author ondryk
 *
 */
@Entity
@Table(name = "group1")
public class Group implements Serializable {

	private static final long serialVersionUID = -3645128872768555986L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	/**
	 * Members - users
	 */
	@ManyToMany
	private List<User> members;

	private String description;

	/**
	 * Every sharing belongs to a specific group
	 */
	@OneToMany(mappedBy = "group")
	private Set<Sharing> sharings;

	public Group(String name, List<User> members, String description, Set<Sharing> sharings) {
		super();
		this.name = name;
		this.members = members;
		this.description = description;
		this.sharings = sharings;
	}

	public Group() {
	};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Sharing> getSharings() {
		return sharings;
	}

	public void setSharings(Set<Sharing> sharings) {
		this.sharings = sharings;
	}

}
