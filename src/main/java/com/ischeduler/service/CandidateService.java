package com.ischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischeduler.exception.CandidateExistsException;
import com.ischeduler.model.Candidate;
import com.ischeduler.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository repository;
	
	public Candidate register(Candidate candidate) throws CandidateExistsException {
		if(findBy(candidate.getName()) != null) {
			throw new CandidateExistsException("Candidate is already registered.");
		}
		return repository.save(candidate);
	}

	public List<Candidate> list() {
		return repository.findAll();
	}

	public Candidate findBy(String name) {
		return repository.findByName(name);
	}

}
