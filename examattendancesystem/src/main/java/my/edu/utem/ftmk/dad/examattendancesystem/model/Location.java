package my.edu.utem.ftmk.dad.examattendancesystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * This is the Location Model class
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int locationId;

	@Column(name = "location_name")
	private String locationName;

	/**
	 * get locationId 
	 * @return
	 */
	public int getLocationId() {
		
		return locationId;
		
	}

	/**
	 * set locationId 
	 * 
	 * @param locationId
	 */
	public void setLocationId(int locationId) {
		
		this.locationId = locationId;
		
	}
	
	/**
	 * get locationName 
	 * 
	 * @return
	 */
	public String getLocationName() {
		
		return locationName;
		
	}
	
	/**
	 * set locationName 
	 * 
	 * @param locationName
	 */
	public void setLocationName(String locationName) {
		
		this.locationName = locationName;
		
	}

}
