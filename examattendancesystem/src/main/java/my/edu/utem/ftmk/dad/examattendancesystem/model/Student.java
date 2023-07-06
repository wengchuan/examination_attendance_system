package my.edu.utem.ftmk.dad.examattendancesystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * This class is the Student Model 
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "no_matric")
	private String noMatric;

	@ManyToOne
	@JoinColumn(name = "advisor_id")
	@JsonIgnoreProperties("academicAdvisee")
	private Staff academicAdvisor;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "student_program")
	private String studentProgram;

	@ManyToMany
	@JoinTable(name = "student_course", // Specify the name of the join table
			joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> studentCourse;

	/**
	 * 
	 * get student Id 
	 * @return
	 */
	
	public int getStudentId() {
		return studentId;
	}
	
	
	/**
	 * 
	 * set StudentId 
	 * @param studentId
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	
	/**
	 * 
	 * get studentName 
	 * @return
	 */
	public String getStudentName() {
		return studentName;
	}
	
	
	/**
	 * 
	 * set studentName 
	 * 
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 
	 * get Student noMatric 
	 * 
	 * @return
	 */
	public String getNoMatric() {
		return noMatric;
	}

	
	/**
	 * 
	 *set Student noMatric 
	 * 
	 * @param noMatric
	 */
	public void setNoMatric(String noMatric) {
		this.noMatric = noMatric;
	}

	
	/**
	 * 
	 * get Student academicAdvisor
	 *  
	 * @return
	 */
	public Staff getAcademicAdvisor() {
		return academicAdvisor;
	}

	/**
	 * 
	 * set Student academicAdvisor 
	 * 
	 * @param academicAdvisor
	 */
	public void setAcademicAdvisor(Staff academicAdvisor) {
		this.academicAdvisor = academicAdvisor;
	}
	
	

	/**
	 * 
	 * get Student phoneNumber 
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * set Student phoneNumber 
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * get Student email 
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set Student email 
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * get studentProgram  
	 * 
	 * @return
	 */
	public String getStudentProgram() {
		return studentProgram;
	}

	/**
	 *   set studentProgram 
	 *   
	 * @param studentProgram
	 */
	public void setStudentProgram(String studentProgram) {
		this.studentProgram = studentProgram;
	}

	
	/**
	 * 
	 * get a list of studentCourse
	 * 
	 * @return
	 */
	public List<Course> getStudentCourse() {
		return studentCourse;
	}


	/**
	 * 
	 * set studentCourse
	 * 
	 * @return
	 */
	
	public void setStudentCourse(List<Course> studentCourse) {
		this.studentCourse = studentCourse;
	}
}
