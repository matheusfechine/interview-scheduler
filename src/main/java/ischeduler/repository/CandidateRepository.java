package ischeduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ischeduler.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

	Candidate findByName(String name);

}
