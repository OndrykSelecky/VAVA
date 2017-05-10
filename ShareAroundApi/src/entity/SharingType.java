package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The type of sharing.
 * 
 * @author ondryk
 *
 */
@Entity
@Table(name = "sharingtype")
@NamedQuery(name = "entity.sharingType.getAll", query = "select st from SharingType st")
public class SharingType implements Serializable {

	private static final long serialVersionUID = 2413459041500673677L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(mappedBy = "type")
	private List<Sharing> sharings;

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

	public List<Sharing> getSharings() {
		return sharings;
	}

	public void setSharings(List<Sharing> sharings) {
		this.sharings = sharings;
	}

	public SharingType(String name) {
		super();
		this.name = name;
	}

	public SharingType() {
	};

}
