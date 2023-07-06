package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Staff;

/**
 * This interface is Staff Repository 
 * 
 * @author wengchuan
 *
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
