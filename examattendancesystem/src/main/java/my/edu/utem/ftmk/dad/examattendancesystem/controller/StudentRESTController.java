package my.edu.utem.ftmk.dad.examattendancesystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Attendance;
import my.edu.utem.ftmk.dad.examattendancesystem.model.Student;
import my.edu.utem.ftmk.dad.examattendancesystem.repository.StudentRepository;

/**
 * This is the Student REST Controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/student")
public class StudentRESTController {
	@Autowired
	private StudentRepository studentRepository;

	
	
	/**
	 * list all student
	 * 
	 * @return
	 */
	@GetMapping
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}


	
	/**
	 * 
	 * get specific student
	 * 
	 * @param studentId
	 * @return
	 */
	@GetMapping("{studentId}")
	public Student getStudentById(@PathVariable long studentId) {
		
		Student student = studentRepository.findById(studentId).get();
		
		return student;
	}


	/**
	 * get specific course student list
	 * 
	 * @param courseId
	 * @return
	 */
	@GetMapping("course/{courseId}")
	public List<Student> getStudentByCourseId(@PathVariable long courseId) {
		
		// Retrieve the course with the given courseId
		List<Student> student = studentRepository.findByStudentCourseCourseId(courseId);

		// Return an empty list if the course is not found
		return student;
	}

	
	
	
	/**
	 * Check if a student is in a specific course
	 * 
	 * @param studentMatric
	 * @param courseId
	 * @return
	 */
	@GetMapping("{studentMatric}/course/{courseId}")
	public Student checkStudentInCourse(@PathVariable String studentMatric, @PathVariable long courseId) {
		
		Student student = studentRepository.findByNoMatricAndStudentCourseCourseId(studentMatric, courseId);
		
		return student;
	}


	
	/**
	 * insert a new student
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping()
	public Student insertStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	
	/**
	 * update student
	 * 
	 * @param student
	 * @return
	 */
	@PutMapping()
	public Student updateStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	
	
	/**
	 * // delete existing student
	 * 
	 * @param studentId
	 * @return
	 */
	@DeleteMapping("{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long studentId) {
		
		studentRepository.deleteById(studentId);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	/**
	 * This method will get all the late student using courseId
	 * 
	 * @param courseId
	 * @return
	 */
	@GetMapping("latestudent/{courseId}")
	public List<Student> getLateStudent(@PathVariable long courseId) {

		String uriStudentCourse = "http://localhost:8080/examinationattendance/"
				+ "api/student/course/" + courseId;

		// Get a list course from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student[]> responseStudentCourse = restTemplate.getForEntity(
				uriStudentCourse, Student[].class);

		// Parse JSON data to array of object
		Student[] allStudents = responseStudentCourse.getBody();

		// Create a list to store the students who didn't attend the exam
		List<Student> lateStudent = new ArrayList<>();

		// Get the attendance list using schedule id
		String uriAttendance = "http://localhost:8080/examinationattendance/api/"
		+ "attendance/schedule/" + courseId;

		ResponseEntity<Attendance[]> responseAttendance = restTemplate.getForEntity(
				uriAttendance, Attendance[].class);

		
		// Parse JSON data to array of object
		Attendance[] allAttendance = responseAttendance.getBody();

		// Iterate over all students enrolled in the course

		for (Student student : allStudents) {
			boolean attended = false;

			// Check if the student has attendance record for the exam
			for (Attendance attendance : allAttendance) {
				
				if (student.getStudentId() == attendance.getStudent()
						.getStudentId()) {
					
					attended = true;
					break;
				}
			}

			// If the student didn't attend the exam, add them to the list
			if (!attended) {
				
				lateStudent.add(student);
			}
		}

		return lateStudent;

	}
	
	/**
	 * This method will generate the message for late student 
	 * 
	 * @param studentName
	 * @param subjectName
	 * @param scheduleTime
	 * @param academicAdvisor
	 * @param teachingLecturer
	 * @return
	 */
	@GetMapping("latemessage/{studentName}/{subjectName}/{scheduleTime}/"
			+ "{academicAdvisor}/{teachingLecturer}")
	public String[] generateLateMessage(@PathVariable String studentName,@PathVariable
			String subjectName,@PathVariable String scheduleTime,
			@PathVariable String academicAdvisor,
			@PathVariable String teachingLecturer) 
	
	{
		
		String lateMessageStudent = "Dear " + studentName
				+ ",We regret to inform you that you have arrived late for the " 
				+ subjectName + " scheduled today. "
				+ "The exam scheduled at " + scheduleTime + "."
				+ "Please ensure to check the exam schedule provided "
				+ "earlier to avoid such incidents in the future."
				+ "If you have any concerns, please "
				+ "contact the exam coordinator immediately.";

		String lateMessageAdvisor = "Dear " + academicAdvisor + "," 
				+ "We would like to inform you that your advisee,"
				+ studentName + ", " + "has unfortunately missed the "
				+ "scheduled exam for " + subjectName + ". "
				+ "The exam took place today at " + scheduleTime + "."
				+ "Thank you for your prompt attention to this matter.";

		
		String lateMessageLecturer = "Dear " + teachingLecturer + ","
				+ "We wanted to bring to your attention that one of your students, "
				+ studentName + ", "
				+ "is late for the scheduled exam today. " + "The exam, " 
				+ subjectName + ", began at " + scheduleTime
				+ ", and unfortunately, " + "the student missed the initial portion. "
				+ "Thank you for your understanding and assistance in "
				+ "addressing this matter.";
		
		
		String lateMessageString[] = { lateMessageStudent, 
				lateMessageAdvisor, lateMessageLecturer };
		

		return lateMessageString;
	}

}
