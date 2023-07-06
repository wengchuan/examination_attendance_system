package my.edu.utem.ftmk.dad.examattendancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.examattendancesystem.model.Location;

/**
 * This interface is Location Repository
 * 
 * @author wengchuan
 *
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
