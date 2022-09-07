package ischeduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ischeduler.model.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

	List<Availability> findByInterviewerName(String name);

	List<Availability> findByCandidateName(String name);

}
