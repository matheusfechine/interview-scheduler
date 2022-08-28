package ischeduler.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.model.Interviewer;
import ischeduler.service.InterviewerService;

@Service
public class Validations {

	@Autowired
	private InterviewerService interviewerService;

	public void validateAvailability(List<Availability> availabilities, Availability availability)
			throws AvailabilityExpeption, InterviewerNotFoundException {
		Interviewer interviewer = interviewerService.findBy(availability.getInterviewer().getName());

		if (interviewer == null) {
			throw new InterviewerNotFoundException("Interviewer Not Found!");
		}
		
	}

}
