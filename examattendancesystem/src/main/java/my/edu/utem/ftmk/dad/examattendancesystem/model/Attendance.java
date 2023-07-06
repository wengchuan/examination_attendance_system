package my.edu.utem.ftmk.dad.examattendancesystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This is the Attendance Model
 * 
 * @author wengchuan
 *
 */
@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_id")
	private int attendanceId;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	
	
	/**
	 * get attendance id 
	 * 
	 * @return
	 */
	public int getAttendanceId() {

		return attendanceId;
	}

	/**
	 * set attendance id 
	 * 
	 * @param attendanceId
	 * 
	 */
	public void setAttendanceId(int attendanceId) {

		this.attendanceId = attendanceId;

	}

	/**
	 * get Schedule 
	 * 
	 * @return
	 */
	public Schedule getSchedule() {

		return schedule;

	}

	/**
	 * set Schedule
	 * 
	 * @param schedule
	 */
	public void setSchedule(Schedule schedule) {

		this.schedule = schedule;

	}
	
	/**
	 * get Student 
	 * 
	 * @return
	 */
	public Student getStudent() {

		return student;

	}
	
	
	/**
	 * set Student 
	 * 
	 * @param student
	 */
	public void setStudent(Student student) {

		this.student = student;

	}

}
