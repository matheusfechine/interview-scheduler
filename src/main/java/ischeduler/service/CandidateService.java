package ischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.CandidateExistsException;
import ischeduler.model.Candidate;
import ischeduler.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository repository;
	
	public Candidate register(Candidate candidate) throws CandidateExistsException {
		return repository.save(candidate);
	}

	public List<Candidate> list() {
		return repository.findAll();
	}

	public Candidate findBy(String name) {
		return repository.findByName(name);
	}

}
