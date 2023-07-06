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

import my.edu.utem.ftmk.dad.examattendancesystem.model.Attendance;
import my.edu.utem.ftmk.dad.examattendancesystem.repository.AttendanceRepository;

/**
 * This class is the Attendance REST controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/attendance")
public class AttendanceRESTController {
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	
	/**
	 * This method list all attendance
	 * @return
	 */
	@GetMapping()
	public List<Attendance> getAttendance() {
		return attendanceRepository.findAll();
	}

	
	
	/**
	 * get specific attendance
	 * 
	 * @param attendanceId
	 * @return
	 */
	@GetMapping("{attendanceId}")
	public Attendance getAttendanceById(@PathVariable long attendanceId) {
		Attendance attendance = attendanceRepository.findById(attendanceId).get();
		return attendance;
	}

	
	
	/**
	 * 
	 * get specific attendance by using scheduleId
	 * 
	 * @param scheduleId
	 * @return
	 */
	@GetMapping("schedule/{scheduleId}")
	public List<Attendance> getSchedueByCourseId(
			@PathVariable long scheduleId) {
		
		// Retrieve the attendance with the given scheduleId
		List<Attendance> attendances = 
				attendanceRepository.findByScheduleScheduleId(scheduleId);

		return attendances;
	}

	
	/**
	 * 
	 * insert a new attendance
	 * 
	 * @param attendance
	 * @return
	 */
	@PostMapping()
	public Attendance insertAttendance(@RequestBody Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	
	

	
	/**
	 * 
	 *  update attendance
	 *  
	 * @param attendance
	 * @return
	 */
	@PutMapping()
	public Attendance updateAttendance(@RequestBody Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	
	
	/**
	 * 
	 * delete existing attendance
	 * 
	 *
	 * @param attendanceId
	 * @return
	 */
	@DeleteMapping("{attendanceId}")
	public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable long attendanceId) {
		attendanceRepository.deleteById(attendanceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
