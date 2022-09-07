package ischeduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ischeduler.model.Interviewer;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	Interviewer findByName(String name);

}
