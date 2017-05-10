package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
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
@NamedQuery(name = "entity.tag.getByText", query = "select t from Tag t where t.text = :tagText")
public class Tag implements Serializable {

	private static final long serialVersionUID = -4792072825584795809L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String text;

	@ManyToMany
	private List<Sharing> sharings;

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

	public List<Sharing> getSharings() {
		return sharings;
	}

	public void setSharings(List<Sharing> sharings) {
		this.sharings = sharings;
	}

	@Override
	public String toString() {
		return text;
	}
}
