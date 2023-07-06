package my.edu.utem.ftmk.dad.examattendancesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Staff;
import my.edu.utem.ftmk.dad.examattendancesystem.repository.StaffRepository;



/**
 * This is the Staff REST Controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/staff")
public class StaffRESTController {
	@Autowired
	private StaffRepository staffRepository;

	
	
	/**
	 * list all staff
	 * 
	 * @return
	 */
	@GetMapping
	public List<Staff> getStaff() {
		return staffRepository.findAll();
	}


	
	/**
	 * get specific staff
	 * 
	 * @param staffId
	 * @return
	 */
	@GetMapping("{staffId}")
	public Staff getStaffById(@PathVariable long staffId) {
		
		Staff staff = staffRepository.findById(staffId).get();
		
		return staff;
	}

	
	
	/**
	 * 
	 * insert a new staff
	 * 
	 * @param staff
	 * @return
	 */
	@PostMapping()
	public Staff insertStaff(@RequestBody Staff staff) {
		
		return staffRepository.save(staff);
	}

	
	/**
	 * update staff
	 * 
	 * @param staff
	 * @return
	 */
	@PutMapping()
	public Staff updateStaff(@RequestBody Staff staff) {
		
		return staffRepository.save(staff);
	}

}
