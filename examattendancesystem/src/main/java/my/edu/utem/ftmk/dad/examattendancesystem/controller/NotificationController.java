package my.edu.utem.ftmk.dad.examattendancesystem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Course;
import my.edu.utem.ftmk.dad.examattendancesystem.model.Schedule;
import my.edu.utem.ftmk.dad.examattendancesystem.model.Student;

/**
 * 
 * This class is for notifying the late student 
 * 
 * @author wengchuan
 *
 */
@Controller
public class NotificationController {
	/**
	 * 
	 * List all late student
	 * @param courseId
	 * @param model
	 * @return
	 */
	@GetMapping("/latestudent/{courseId}")
	public String getLateStudent(@PathVariable long courseId, Model model) {

		// Get a list course from the web service
		RestTemplate restTemplate = new RestTemplate();

		// TODO: Search for the course with the given courseId and retrieve the
		// necessary data
		// Find the schedule id using course id
		String uriSchedule = "http://localhost:8080/examinationattendance/api/" 
		+ "schedule/course/" + courseId;
		
		ResponseEntity<Schedule[]> responseSchedule = restTemplate.getForEntity(
				uriSchedule, Schedule[].class);

		Schedule examSchedule[] = responseSchedule.getBody();
		
		// Parse an array to a list object
		List<Schedule> examScheduleList = Arrays.asList(examSchedule);

		String uriLateStudent = "http://localhost:8080/examinationattendance/api/"
		+ "student/latestudent/" + courseId;
		
		ResponseEntity<Student[]> responseStudent = restTemplate.getForEntity(
				uriLateStudent, Student[].class);

		Student lateStudent[] = responseStudent.getBody();
		
		// Parse an array to a list object
		List<Student> lateStudentList = Arrays.asList(lateStudent);

		// Return the list of students who didn't attend the exam
		model.addAttribute("lateStudent", lateStudentList);
		model.addAttribute("examSchedule", examScheduleList);

		return "lateStudent";
	}

	
	
	
	/**
	 * 
	 * notify specific student
	 * @param courseId
	 * @param studentId
	 * @param model
	 * @return
	 */
	@GetMapping("/latestudent/{courseId}/notify/{studentId}")
	public String sendNotification(@PathVariable long courseId,
			@PathVariable Long studentId, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		// 1. get the schedule
		String uriSchedule = "http://localhost:8080/examinationattendance/api/"
				+ "schedule/course/" + courseId;
		
		ResponseEntity<Schedule[]> responseSchedule = restTemplate.getForEntity(
				uriSchedule, Schedule[].class);
		
		Schedule[] examSchedule = responseSchedule.getBody();

		// 2. get the student info
		String uriStudent = "http://localhost:8080/examinationattendance/api/"
				+ "student/" + studentId;
		
		ResponseEntity<Student> responseStudent = restTemplate.getForEntity(
				uriStudent, Student.class);
		
		Student studentInfo = responseStudent.getBody();

		// 3. generate message
		String studentName = studentInfo.getStudentName();
		String subjectName = examSchedule[0].getCourse().getCourseName();
		String scheduleTime = examSchedule[0].getScheduleTime().toString();
		String academicAdvisor = studentInfo.getAcademicAdvisor().getStaffName();
		
		// Find the course that match the schedule
		List<Course> studentCourses = studentInfo.getStudentCourse();
		Course matchingSubject = null;
		
		for (Course course : studentCourses) {
			if (course.getCourseName().equals(subjectName)) {
				matchingSubject = course;
				break; // Exit the loop if a match is found
			}
		}
		
		// get the teaching lecturer
		String teachingLecturerString = matchingSubject.getTeachingLecturer().getStaffName();

		// generate the message
		// Get late student
		String uriLateMessage = "http://localhost:8080/examinationattendance/api/"
		+ "student/latemessage/"
		+ studentName + "/" + subjectName + "/" + 
		scheduleTime + "/" + academicAdvisor + "/"
		+ teachingLecturerString;

		ResponseEntity<String[]> responseLateMessage = restTemplate.getForEntity(uriLateMessage, String[].class);

		// get the teaching lecturer
		String[] lateMessage = responseLateMessage.getBody();

		// 4. display message
		model.addAttribute("lateMessage", lateMessage);
		model.addAttribute("studentInfo", studentInfo);
		model.addAttribute("matchingSubject", matchingSubject);
		model.addAttribute("examSchedule", examSchedule);

		return "notification";

	}

	
	
	 
	/**
	 * notify all late student
	 * @param courseId
	 * @param model
	 * @return
	 */
	@GetMapping("/latestudent/{courseId}/notify/all")
	public String sendNotificationAll(@PathVariable long courseId, Model model) {

		RestTemplate restTemplate = new RestTemplate();

		// Get late student
		String uriLateStudent = "http://localhost:8080/examinationattendance/api/"
		+ "student/latestudent/" + courseId;
		
		ResponseEntity<Student[]> responseStudent = restTemplate.getForEntity(
				uriLateStudent, Student[].class);

		Student lateStudentList[] = responseStudent.getBody();
		
		// Parse an array to a list object
		List<Student> allLateStudent = Arrays.asList(lateStudentList);

		// 1. get the schedule
		String uriSchedule = "http://localhost:8080/examinationattendance/api/"
				+ "schedule/course/" + courseId;
		
		ResponseEntity<Schedule[]> responseSchedule = restTemplate.getForEntity(
				uriSchedule, Schedule[].class);
		
		Schedule[] examSchedule = responseSchedule.getBody();

		// 2. Extract student names from the lateStudent list
		String subjectName = examSchedule[0].getCourse().getCourseName();
		String scheduleTime = examSchedule[0].getScheduleTime().toString();

		// generate the message
		// 3. Generate messages for each student
		Course matchingSubject = null;
		List<String[]> lateMessages = new ArrayList<>();
		
		// loop for generate all student late messages
		for (Student lateStudent : allLateStudent) {
			
			// Find the course that match the schedule
			for (Course course : lateStudent.getStudentCourse()) {
				
				if (course.getCourseName().equals(subjectName)) {
					
					matchingSubject = course;
					break; // Exit the loop if a match is found
				}
			}

			// Get late student messsage
			String uriLateMessage = "http://localhost:8080/examinationattendance/api/" 
			+ "student/latemessage/"
			+ lateStudent.getStudentName() + "/" + subjectName + "/" + scheduleTime + "/"
			+ lateStudent.getAcademicAdvisor().getStaffName() + "/"
			+ matchingSubject.getTeachingLecturer().getStaffName();

			ResponseEntity<String[]> responseLateMessage = restTemplate.getForEntity(uriLateMessage,
					String[].class);

			// get the teaching lecturer
			String[] lateMessage = responseLateMessage.getBody();
			
			lateMessages.add(lateMessage);
		}

		model.addAttribute("lateMessages", lateMessages);
		model.addAttribute("lateStudent", allLateStudent);
		model.addAttribute("examSchedule", examSchedule);
		model.addAttribute("matchingSubject", matchingSubject);

		return "sendAllMessage";

	}

}
