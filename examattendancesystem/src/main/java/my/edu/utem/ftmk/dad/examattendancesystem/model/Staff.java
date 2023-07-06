package my.edu.utem.ftmk.dad.examattendancesystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * This class is the Staff Model
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private int staffId;

	@Column(name = "staff_name")
	private String staffName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "academicAdvisor")
	@JsonIgnoreProperties("academicAdvisor")
	private List<Student> academicAdvisee;

	/**
	 * get staffId 
	 * @return
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * 
	 * set staffId
	 * 
	 * @param staffId
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	
	/**
	 * 
	 * get staffName
	 * 
	 * @return
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 
	 * set staffName
	 * 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * 
	 * get Staff phoneNumber 
	 * @return
	 */
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	/**
	 * 
	 * set staff phoneNumber 
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * 
	 * get Staff Email
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * set Staff email 
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * 
	 * get a list of Academic Advisee 
	 * 
	 * @return
	 */
	public List<Student> getAcademicAdvisee() {
		return academicAdvisee;
	}

	
	/**
	 * 
	 * set staff academic advisee 
	 * 
	 * @param academicAdvisee
	 */
	public void setAcademicAdvisee(List<Student> academicAdvisee) {
		this.academicAdvisee = academicAdvisee;
	}
}
