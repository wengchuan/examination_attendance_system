package my.edu.utem.ftmk.dad.examattendancesystem.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Schedule;


/**
 * This class will display a list of exam schedule 
 * 
 * @author wengchuan
 *
 */
@Controller
public class ScheduleController {
	
	
	/**
	 * Display the exam schedule list 
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/schedule/list")
	public String getAllSchedule(Model model) {
		String uri = "http://localhost:8080/examinationattendance/api/schedule";

		// Get a list course from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Schedule[]> response = restTemplate.getForEntity(uri, Schedule[].class);

		// Parse JSON data to array of object
		Schedule schedule[] = response.getBody();

		// Parse an array to a list object
		List<Schedule> scheduleList = Arrays.asList(schedule);

		// Attach list to model as attribute
		model.addAttribute("schedule", scheduleList);

		return "schedule";
	}
}
