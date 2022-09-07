package com.ischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischeduler.exception.InterviewerExistsException;
import com.ischeduler.model.Interviewer;
import com.ischeduler.repository.InterviewerRepository;

@Service
public class InterviewerService {

	@Autowired
	private InterviewerRepository repository;
	
	public Interviewer register(Interviewer interviewer) throws InterviewerExistsException {
		if(findBy(interviewer.getName()) != null) {
			throw new InterviewerExistsException("Interviewer is already registered.");
		}
		
		return repository.save(interviewer);
	}

	public List<Interviewer> list() {
		return repository.findAll();
	}

	public Interviewer findBy(final String name) {
		return repository.findByName(name);
	}

}
