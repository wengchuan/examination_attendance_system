package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Course;


/**
 * This interface is the Course Repository 
 * 
 * @author wengchuan
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
