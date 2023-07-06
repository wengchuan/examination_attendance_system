package my.edu.utem.ftmk.dad.examattendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This class represents the Course Model
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int courseId;

	@Column(name = "course_name")
	private String courseName;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	@JsonIgnoreProperties("academicAdvisee")
	private Staff teachingLecturer;

	/**
	 * get courseId
	 * 
	 * @return
	 */
	public int getCourseId() {

		return courseId;

	}

	/**
	 * 
	 * set courseId
	 * 
	 * @param courseId
	 */
	public void setCourseId(int courseId) {

		this.courseId = courseId;

	}

	/**
	 * get courseName
	 * 
	 * @return
	 */
	public String getCourseName() {

		return courseName;
		
	}
	
	/**
	 * set courseName
	 * 
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		
		this.courseName = courseName;
		
	}

	/**
	 * 
	 * get teachingLecturer
	 * 
	 * @return
	 */
	public Staff getTeachingLecturer() {
		
		return teachingLecturer;
		
	}

	/**
	 * set teachingLecturer 
	 * 
	 * @param teachingLecturer
	 */
	public void setTeachingLecturer(Staff teachingLecturer) {
		
		this.teachingLecturer = teachingLecturer;
		
	}

}
