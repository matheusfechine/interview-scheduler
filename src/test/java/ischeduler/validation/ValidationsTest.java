package ischeduler.validation;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.model.Interviewer;
import ischeduler.service.InterviewerService;

public class ValidationsTest {
	
	@InjectMocks
	private Validations validation;
	
	@Mock
	private InterviewerService interviewerService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test(expected = InterviewerNotFoundException.class)
	public void shoudNotRegisterWhenInterviererDoesNotExists() throws AvailabilityExpeption, InterviewerNotFoundException {
		when(interviewerService.findBy(anyString())).thenReturn(null);
		Availability availability = new Availability();
		Interviewer interviewer = new Interviewer();
		interviewer.setName("Jose");
		availability.setInterviewer(interviewer);
		validation.validateInterviewerAvailability(Arrays.asList(availability), availability);
		
	}

}
