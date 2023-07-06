package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Attendance;

/**
 * This interface is the Attendance Repository
 * 
 * @author wengchuan
 *
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	
	
	/**
	 * //find attendance using schedule id 
	 * 
	 * @param scheduleId
	 * @return
	 */
	List<Attendance> findByScheduleScheduleId(Long scheduleId);
}
