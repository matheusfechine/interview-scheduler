package ischeduler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.CandidateNotFoundException;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.validation.Validations;

@Service
public class AvailabilityService {

	private List<Availability> availabilities = new ArrayList<Availability>();

	@Autowired
	private Validations validation;

	public List<Availability> add(Availability availability)
			throws AvailabilityExpeption, InterviewerNotFoundException, CandidateNotFoundException {

		validation.validateInterviewerAvailability(availabilities, availability);
		validation.validateCandidateAvailability(availabilities, availability);

		int init = new DateTime(availability.getInitTime()).getHourOfDay();
		int end = new DateTime(availability.getEndTime()).getHourOfDay();
		int numberOfHours = end - init;

		for (int i = 0; i < numberOfHours; i++) {
			Availability toRegister = new Availability();
			if (availability.getInterviewer() != null) {
				toRegister.setInterviewer(availability.getInterviewer());
			}
			if (availability.getCandidate() != null) {
				toRegister.setCandidate(availability.getCandidate());
			}
			toRegister.setInitTime(new DateTime(availability.getInitTime()).plusHours(i).toDate());
			toRegister.setEndTime(new DateTime(availability.getInitTime()).plusHours(i + 1).toDate());
			availabilities.add(toRegister);
		}
		return availabilities;
	}

	public List<Availability> list() {
		return availabilities;
	}

	public List<Availability> listByInterviewer(String name) {
		return availabilities.stream()
				.filter(find -> find.getCandidate() == null && find.getInterviewer().getName().equals(name))
				.collect(Collectors.toList());
	}

	public List<Availability> listByCandidate(String name) {
		return availabilities.stream()
				.filter(find -> find.getInterviewer() == null && find.getCandidate().getName().equals(name))
				.collect(Collectors.toList());
	}

	public List<Availability> listForArrangeBy(Availability availability) {
		List<Availability> candidates = listByCandidate(availability.getCandidate().getName());
		List<Availability> interviewers = listByInterviewer(availability.getInterviewer().getName());
		return candidates.stream().filter(c -> interviewers.stream()
				.anyMatch(i -> i.getInitTime().equals(c.getInitTime())))
				.collect(Collectors.toList());
	}

}
