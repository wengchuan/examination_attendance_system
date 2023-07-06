package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Schedule;


/**
 * This interface is Schedule Repository
 * 
 * @author wengchuan
 *
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	/**
	 * This method will get the Schedule using courseId 
	 * 
	 * @param courseId
	 * @return
	 */
	List<Schedule> findByCourseCourseId(long courseId);


}
