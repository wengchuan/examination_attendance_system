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

import my.edu.utem.ftmk.dad.examattendancesystem.model.Course;

import my.edu.utem.ftmk.dad.examattendancesystem.repository.CourseRepository;

/**
 * This class is the course REST Controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/course")
public class CourseRESTController {
	@Autowired
	private CourseRepository courseRepository;

	
	
	/**
	 * list all course
	 * @return
	 */
	@GetMapping
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}

	
	
	
	/**
	 *  get specific course using courseId 
	 * 
	 * @param courseId
	 * @return
	 */
	@GetMapping("{courseId}")
	public Course getCourseById(@PathVariable long courseId) {
		Course course = courseRepository.findById(courseId).get();
		return course;
	}
	
	

	
	
	
	/**
	 * 
	 *  insert a new course
	 * 
	 * @param course
	 * @return
	 */
	@PostMapping()
	public Course insertCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	
	
	/**
	 * 
	 * update course
	 * 
	 * @param course
	 * @return
	 */
	@PutMapping()
	public Course updateCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	

	
	/**
	 * 
	 * delete existing course
	 * 
	 * @param courseId
	 * @return
	 */
	@DeleteMapping("{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable long courseId) {
		courseRepository.deleteById(courseId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
