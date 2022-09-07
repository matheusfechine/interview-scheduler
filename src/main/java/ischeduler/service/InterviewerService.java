package ischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.InterviewerExistsException;
import ischeduler.model.Interviewer;
import ischeduler.repository.InterviewerRepository;

@Service
public class InterviewerService {

	@Autowired
	private InterviewerRepository repository;
	
	public Interviewer register(Interviewer interviewer) throws InterviewerExistsException {
		return repository.save(interviewer);
	}

	public List<Interviewer> list() {
		return repository.findAll();
	}

	public Interviewer findBy(final String name) {
		return repository.findByName(name);
	}

}
