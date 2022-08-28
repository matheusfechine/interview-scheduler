package ischeduler.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ischeduler.exception.AvailabilityExpeption;
import ischeduler.exception.InterviewerNotFoundException;
import ischeduler.model.Availability;
import ischeduler.model.Interviewer;
import ischeduler.validation.Validations;

public class AvailabilityServiceTest {

	@InjectMocks
	private AvailabilityService service;

	@Mock
	private Validations validation;
	
	@Mock
	private InterviewerService interviewerService;

	private Availability availabilityOne;
	private Availability availabilityTwo;
	private Availability availabilityThree;
	private DateTime baseDate;
	private Interviewer interviewer;

	@Before
	public void setUp() {
		createAvailabilities();
		MockitoAnnotations.initMocks(this);
	}

	private void createAvailabilities() {
		interviewer = new Interviewer();
		interviewer.setName("Matheus");
		baseDate = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2022).withMinuteOfHour(0)
				.withMillisOfSecond(0);
		availabilityOne = new Availability();
		availabilityOne.setInterviewer(interviewer);
		availabilityOne.setInitTime(baseDate.withHourOfDay(10).toDate());
		availabilityOne.setEndTime(baseDate.withHourOfDay(11).toDate());

		availabilityTwo = new Availability();
		availabilityTwo.setInterviewer(interviewer);
		availabilityTwo.setInitTime(baseDate.withHourOfDay(11).toDate());
		availabilityTwo.setEndTime(baseDate.withHourOfDay(12).toDate());

		availabilityThree = new Availability();
		availabilityThree.setInterviewer(interviewer);
		availabilityThree.setInitTime(baseDate.withHourOfDay(12).toDate());
		availabilityThree.setEndTime(baseDate.withHourOfDay(13).toDate());

	}

	@Test
	public void shouldAddAvailability() throws AvailabilityExpeption, InterviewerNotFoundException {
		List<Availability> expectedAvailabilities = new ArrayList<Availability>();
		DateTime scheduledDay = new DateTime(baseDate);
		scheduledDay.withDayOfMonth(1).withMonthOfYear(1).withYear(2022);
		expectedAvailabilities.add(availabilityOne);
		expectedAvailabilities.add(availabilityTwo);
		expectedAvailabilities.add(availabilityThree);
		Availability availabilityToBeRegistered = new Availability();
		availabilityToBeRegistered.setInterviewer(interviewer);
		availabilityToBeRegistered.setInitTime(scheduledDay.withHourOfDay(10).withMinuteOfHour(0).toDate());
		availabilityToBeRegistered.setEndTime(scheduledDay.withHourOfDay(13).withMinuteOfHour(0).toDate());

		List<Availability> actualAvailabilities = service.add(availabilityToBeRegistered);

		assertEquals(expectedAvailabilities, actualAvailabilities);

	}

	@Test
	public void shouldListByInterviewer() throws AvailabilityExpeption, InterviewerNotFoundException {
		List<Availability> expectedAvailabilities = new ArrayList<Availability>();
		DateTime scheduledDay = new DateTime(baseDate);
		scheduledDay.withDayOfMonth(1).withMonthOfYear(1).withYear(2022);
		Availability availabilityFour = new Availability();
		availabilityFour.setInterviewer(new Interviewer());
		availabilityFour.getInterviewer().setName("Jose");
		availabilityFour.setInitTime(baseDate.withHourOfDay(10).toDate());
		availabilityFour.setEndTime(baseDate.withHourOfDay(11).toDate());
		expectedAvailabilities.add(availabilityFour);
		service.add(availabilityFour);
		
		
		Availability availabilityToBeRegistered = new Availability();
		availabilityToBeRegistered.setInterviewer(interviewer);
		availabilityToBeRegistered.setInitTime(scheduledDay.withHourOfDay(10).withMinuteOfHour(0).toDate());
		availabilityToBeRegistered.setEndTime(scheduledDay.withHourOfDay(13).withMinuteOfHour(0).toDate());
		service.add(availabilityToBeRegistered);
		
		List<Availability> actualAvailabilities = service.listBy("Jose");
		
		assertEquals(expectedAvailabilities, actualAvailabilities);
	}
}
