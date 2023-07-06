package my.edu.utem.ftmk.dad.examattendancesystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 
 * This is the Schedule Model
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private int scheduleId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@Column(name = "schedule_time")
	private LocalDateTime scheduleTime;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "chief_invigilator")
	@JsonIgnoreProperties("academicAdvisee")
	private Staff chiefInvigilator;

	@ManyToMany
	@JoinTable(name = "ExamInvigilators", joinColumns = @JoinColumn(name = "invigilators_id"), inverseJoinColumns = @JoinColumn(name = "staff_id"))
	@JsonIgnoreProperties("invigilators.academicAdvisee")
	private List<Staff> Invigilators = new ArrayList<>();

	/**
	 * get ScheduleId 
	 * 
	 * @return
	 */
	public int getScheduleId() {
		
		return scheduleId;
		
	}

	/**
	 * set scheduleId 
	 * 
	 * @param scheduleId
	 */
	public void setScheduleId(int scheduleId) {
		
		this.scheduleId = scheduleId;
		
	}
	
	/**
	 * 
	 * get Course 
	 * @return
	 */
	public Course getCourse() {
		
		return course;
		
	}
	
	/**
	 * 
	 * set Course 
	 * @param course
	 */
	public void setCourse(Course course) {
		
		this.course = course;
		
	}

	
	/**
	 * 
	 * get scheduleTime
	 * 
	 * @return
	 */
	public LocalDateTime getScheduleTime() {
		
		return scheduleTime;
		
	}
	
	
	/**
	 * set scheduleTime 
	 * 
	 * @param scheduleTime
	 */
	public void setScheduleTime(LocalDateTime scheduleTime) {
		
		this.scheduleTime = scheduleTime;
		
	}

	/**
	 * get Location
	 * 
	 * @return
	 */
	public Location getLocation() {
		
		return location;
		
	}

	/**
	 * set Location 
	 * 
	 * @param location
	 */
	public void setLocation(Location location) {
		
		this.location = location;
		
	}

	/**
	 * 
	 * get chiefInvigilator 
	 * 
	 * @return
	 */
	public Staff getChiefInvigilator() {
		
		return chiefInvigilator;
		
	}

	/**
	 * 
	 * set chiefInvigilator 
	 * 
	 * @return
	 */
	public void setChiefInvigilator(Staff chiefInvigilator) {
		
		this.chiefInvigilator = chiefInvigilator;
		
	}

	/**
	 * get a list of Invigilators
	 * 
	 * @return
	 */
	public List<Staff> getInvigilators() {
		
		// Exclude academicAdvisee property when serializing invigilators
		for (Staff invigilator : Invigilators) {
			invigilator.setAcademicAdvisee(null);
		}
		return Invigilators;
	}
	
	/**
	 * set Invigilators  
	 * 
	 * @param invigilators
	 */
	public void setInvigilators(List<Staff> invigilators) {
		
		Invigilators = invigilators;
		
	}

}
