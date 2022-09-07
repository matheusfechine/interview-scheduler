package com.ischeduler.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischeduler.exception.AvailabilityExpeption;
import com.ischeduler.exception.CandidateNotFoundException;
import com.ischeduler.exception.InterviewerNotFoundException;
import com.ischeduler.model.Availability;
import com.ischeduler.model.Candidate;
import com.ischeduler.model.Interviewer;
import com.ischeduler.service.CandidateService;
import com.ischeduler.service.InterviewerService;

@Service
public class Validations {

	@Autowired
	private InterviewerService interviewerService;

	@Autowired
	private CandidateService candidateService;

	public void validateInterviewerAvailability(List<Availability> availabilities, Availability availability)
			throws AvailabilityExpeption, InterviewerNotFoundException {
		if (availability.getInterviewer() == null) {
			return;
		}
		Interviewer interviewer = interviewerService.findBy(availability.getInterviewer().getName());

		if (interviewer == null) {
			throw new InterviewerNotFoundException("Interviewer Not Found!");
		}
		availability.setInterviewer(interviewer);
	}

	public void validateCandidateAvailability(List<Availability> availabilities, Availability availability)
			throws AvailabilityExpeption, CandidateNotFoundException {
		if (availability.getCandidate() == null) {
			return;
		}
		Candidate candidate = candidateService.findBy(availability.getCandidate().getName());

		if (candidate == null) {
			throw new CandidateNotFoundException("Candidate Not Found!");
		}
		
		availability.setCandidate(candidate);

	}

}
