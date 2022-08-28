package ischeduler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ischeduler.exception.CandidateExistsException;
import ischeduler.model.Candidate;

@Service
public class CandidateService {

	private List<Candidate> candidates = new ArrayList<Candidate>();
	
	public Candidate register(Candidate candidate) throws CandidateExistsException {
		candidate.setId(candidates.size()+1);
		candidates.add(candidate);
		return candidate;
	}

	public List<Candidate> list() {
		return candidates;
	}

	public Candidate findBy(String name) {
		return candidates.stream().filter(find -> find.getName().equals(name))
				.findFirst().orElse(null);
	}

}
