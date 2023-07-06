package my.edu.utem.ftmk.dad.examattendancesystem.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Attendance;
import my.edu.utem.ftmk.dad.examattendancesystem.model.Schedule;
import my.edu.utem.ftmk.dad.examattendancesystem.model.Student;



/**
 * 
 * This class is manage the student attendance 
 * @author wengchuan
 *
 */
@Controller
public class AttendanceController {
	
	/**
	 * 
	 * The method will get the student attendance list 
	 * 
	 * @param scheduleId
	 * @param model
	 * @return
	 */
	
@GetMapping("/attendance/{scheduleId}/list")
public String listAttendance(@PathVariable long scheduleId, Model model) {
		
	// The URI for GET attendance using scheduleId 
		String uri = "http://localhost:8080/examinationattendance/api/attendance/"
				+ "schedule/"+scheduleId;

		// Get a list order types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Attendance[]> response = restTemplate.getForEntity(uri, 
				Attendance[].class);

		// Parse JSON data to array of object
		Attendance attendance[] = response.getBody();

		// Parse an array to a list object
		List<Attendance> attendances = Arrays.asList(attendance);

		// Attach list to model as attribute 
		model.addAttribute("attendances", attendances);

		return "attendances";
	}



	/**
	 * This method is display the front end for add attendance
	 * @param scheduleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/attendance/{scheduleId}")
	public String getAttendance(@PathVariable long scheduleId, Model model) { 
		
		RestTemplate restTemplate = new RestTemplate();
		
		// Create request body
		String uriSchedule = "http://localhost:8080/examinationattendance/api/"
				+ "schedule/" + scheduleId;
		
		ResponseEntity<Schedule> responseSchedule = restTemplate.getForEntity(
				uriSchedule, Schedule.class);

		Schedule examSchedule = responseSchedule.getBody();
		
		// Parse an array to a list object 
		List<Schedule> examScheduleList = Arrays.asList(examSchedule);  
		
		model.addAttribute("examSchedule", examSchedule);    
		
		model.addAttribute("attendance", new Attendance()); 
		
		return "addAttendance"; 

	}  
 
		  
	/**
	 * 
	 * This method will update or add an attendance 
	 * @param attendance
	 * @param scheduleId
	 * @return
	 */
	@RequestMapping("/attendance/{scheduleId}/save") 		
	public String recordAttendance(@ModelAttribute Attendance attendance,
			@PathVariable long scheduleId) {
		
		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "http://localhost:8080/examinationattendance/api/attendance";
		
		 // Print attendance object as JSON
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	
	        String attendanceJson = objectMapper.writeValueAsString(attendance);
	        
	        System.out.println(attendanceJson);
	        
	    } catch (JsonProcessingException e) {
	    	
	        e.printStackTrace(); 
	    } 
	    
	   //get schedule using scheduleId
	    String uriSchedule = "http://localhost:8080/examinationattendance/api/schedule/" +
	   attendance.getSchedule().getScheduleId();
	    
		ResponseEntity<Schedule> responseSchedule = restTemplate.getForEntity(
				uriSchedule, Schedule.class);
		
		Schedule examSchedule = responseSchedule.getBody();
		
	    attendance.setSchedule(examSchedule);
	    
		Student student = validateStudentCourse(attendance.getStudent().getNoMatric(),
				attendance.getSchedule().getCourse().getCourseId());
		
		attendance.setStudent(student);
		
		// Create request body
		HttpEntity<Attendance> request = new HttpEntity<Attendance>(attendance);
 
		String attendanceResponse = "";

		if (attendance.getAttendanceId() > 0) {
			// This block update an attendance type and
			// send request as PUT

			restTemplate.put(uri, request, Attendance.class);

		} else {
			// This block add a new attendance 
			// send request as POST
			attendanceResponse = restTemplate.postForObject(uri, request, String.class);

		}

		// Redirect request to display a list of attendance
		return "redirect:/attendance/{scheduleId}/list";

	}
	
	
	
	
	/**
	 * This method will validate the student is in the exam name list or not
	 * 
	 * @param studentMatric
	 * @param courseId
	 * @return
	 */
	public Student validateStudentCourse(String studentMatric, long courseId) {
		// Make a request to check if the student is in the course
		String uri = "http://localhost:8080/examinationattendance/api/student/" +
		studentMatric + "/course/" + courseId;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Student> response = restTemplate.getForEntity(
				uri, Student.class);
		
		return response.getBody();
	}
}
