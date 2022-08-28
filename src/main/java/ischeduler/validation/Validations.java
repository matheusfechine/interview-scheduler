package ischeduler.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.CandidateNotFoundException;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.model.Candidate;
import ischeduler.model.Interviewer;
import ischeduler.service.CandidateService;
import ischeduler.service.InterviewerService;

@Service
public class Validations {

	@Autowired
	private InterviewerService interviewerService;

	@Autowired
	private CandidateService candidateService;
	
	public void validateInterviewerAvailability(List<Availability> availabilities, Availability availability)
			throws AvailabilityExpeption, InterviewerNotFoundException {
		if(availability.getInterviewer() == null) {
			return;
		}
		Interviewer interviewer = interviewerService.findBy(availability.getInterviewer().getName());

		if (interviewer == null) {
			throw new InterviewerNotFoundException("Interviewer Not Found!");
		}
		
	}
	public void validateCandidateAvailability(List<Availability> availabilities, Availability availability)
			throws AvailabilityExpeption, CandidateNotFoundException {
		if(availability.getCandidate() == null) {
			return;
		}
		Candidate candidate = candidateService.findBy(availability.getCandidate().getName());

		if (candidate == null) {
			throw new CandidateNotFoundException("Interviewer Not Found!");
		}
		
	}

}
