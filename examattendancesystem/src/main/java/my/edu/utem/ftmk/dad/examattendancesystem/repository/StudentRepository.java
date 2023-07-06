package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Student;


/**
 * This interface is Student Repository
 * @author wengchuan
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	/**
	 * This method will get the Student by using courseId 
	 * 
	 * @param courseId
	 * @return
	 */
	List<Student> findByStudentCourseCourseId(Long courseId);
	
	/**
	 * This method will get the student using student noMatirc and courseId  
	 * 
	 * @param studentMatric
	 * @param courseId
	 * @return
	 */
	Student findByNoMatricAndStudentCourseCourseId(String studentMatric, Long courseId);
}
 