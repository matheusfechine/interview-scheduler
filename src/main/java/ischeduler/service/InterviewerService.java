package ischeduler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ischeduler.exception.InterviewerExistsException;
import ischeduler.model.Interviewer;

@Service
public class InterviewerService {

	private static  List<Interviewer> interviewers;
	
	public InterviewerService() {
		if(interviewers==null) {
			interviewers = new ArrayList<Interviewer>();
		}
	}
	
	public Interviewer register(Interviewer interviewer) throws InterviewerExistsException {
		interviewer.setId(interviewers.size()+1);
		interviewers.add(interviewer);
		return interviewer;
	}

	public List<Interviewer> list() {
		return interviewers;
	}

	public Interviewer findBy(final String name) {
		return interviewers.stream().filter(find -> find.getName().equals(name))
				.findFirst().orElse(null);
	}

}
