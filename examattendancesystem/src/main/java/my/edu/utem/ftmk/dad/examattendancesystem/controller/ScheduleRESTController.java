package my.edu.utem.ftmk.dad.examattendancesystem.controller;

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

import my.edu.utem.ftmk.dad.examattendancesystem.model.Schedule;
import my.edu.utem.ftmk.dad.examattendancesystem.repository.ScheduleRepository;



/**
 *This is Schedule REST Controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleRESTController {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	
	
		/**
		 *  list all schedule
		 *  
		 * @return
		 */
		@GetMapping
		public List<Schedule> getAllSchedules() {
			
			return scheduleRepository.findAll();
		}

	
		
		/**
		 * 
		 * get specific schedule
		 * 
		 * @param scheduleId
		 * @return
		 */
		@GetMapping("{scheduleId}")
		public Schedule getScheduleById(@PathVariable long scheduleId) {
			
			Schedule schedule = scheduleRepository.findById(scheduleId).get();
			
			return schedule;
		}
		
	
		
		/**
		 * 
		 * get specific schedule by using courseId
		 * 
		 * @param courseId
		 * @return
		 */
		@GetMapping("course/{courseId}")
		public List<Schedule> getSchedueByCourseId(@PathVariable long courseId) {
			
		// Retrieve the course with the given courseId
		List<Schedule> schedules = scheduleRepository.findByCourseCourseId(courseId);

		    
		 return schedules;
		} 	

	
		
		/**
		 * insert a new schedule
		 * 
		 * @param schedule
		 * @return
		 */
		@PostMapping()
		public Schedule insertSchedule(@RequestBody Schedule schedule) {
			
			return scheduleRepository.save(schedule);
		}

		
		
		/**
		 * update schedule
		 * 
		 * @param schedule
		 * @return
		 */
		@PutMapping()
		public Schedule updateSchedule(@RequestBody Schedule schedule) {
			return scheduleRepository.save(schedule);
		}
		
	
		/**
		 * delete existing schedule
		 * 
		 * 
		 * @param scheduleId
		 * @return
		 */
		@DeleteMapping("{scheduleId}")
		public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable
				long scheduleId) {
			
			scheduleRepository.deleteById(scheduleId);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
