package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Reprezentácie adresy. Každý užívate¾ má vlastnú adresu.
 * @author ondryk
 *
 */
@Entity
@Table(name="address")
public class Address implements Serializable{

	
	private static final long serialVersionUID = 7564776194312963061L;
	
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )	
	private long id;
	
	
    private String houseNumber;
     
    
    private String streetAddress;
     
    @Column( nullable = false)
    private String city;
     
    @Column( nullable = false)
    private String state;
     
    @Column(nullable = false)
    private String zipCode;
    
    @OneToOne(mappedBy="address")
    private User user;

        
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address(String streetAddress, String houseNumber, String zipCode, String city, String state ) {
		super();
		this.houseNumber = houseNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	
	public Address(){};
	
}
