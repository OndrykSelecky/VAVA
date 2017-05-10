package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Tags to be used in filtering.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {

	private static final long serialVersionUID = -4792072825584795809L;

	@Id
	private String text;

	@ManyToMany(mappedBy = "tags")
	private Set<Sharing> sharings;

	public Tag(String text) {
		this.text = text;
	}

	public Tag() {
	};

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Sharing> getSharings() {
		return sharings;
	}

	public void setSharings(Set<Sharing> sharings) {
		this.sharings = sharings;
	}

}
