package ischeduler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.validation.Validations;

@Service
public class AvailabilityService {

	private List<Availability> availabilities = new ArrayList<Availability>();
	
	@Autowired
	private Validations validation;
	
	public List<Availability> add(Availability availability) 
			throws AvailabilityExpeption, InterviewerNotFoundException {

		validation.validateAvailability(availabilities, availability);
		
		int init = new DateTime(availability.getInitTime()).getHourOfDay();
		int end = new DateTime(availability.getEndTime()).getHourOfDay();
		int numberOfHours = end - init;
		
		for (int i = 0; i < numberOfHours; i++) {
			Availability toRegister = new Availability();
			toRegister.setInterviewer(availability.getInterviewer());
			toRegister.setInitTime(new DateTime(availability.getInitTime()).plusHours(i).toDate());
			toRegister.setEndTime(new DateTime(availability.getInitTime()).plusHours(i+1).toDate());
			availabilities.add(toRegister);
		}
		return availabilities;
	}

	public List<Availability> list() {
		return availabilities;
	}

	public List<Availability> listBy(String name) {
		return availabilities
				.stream()
				.filter(find -> find.getInterviewer().getName().equals(name))
				.collect(Collectors.toList());
	}

}
