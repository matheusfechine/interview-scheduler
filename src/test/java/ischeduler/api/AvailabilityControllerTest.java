package ischeduler.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import ischeduler.model.Availability;
import ischeduler.model.Interviewer;
import ischeduler.service.AvailabilityService;

public class AvailabilityControllerTest {

	@InjectMocks
	private AvailabilityController controller;
	
	@Mock
	private AvailabilityService service;
	
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
	public void shouldListAll() {
		List<Availability> expectedAvailabilities = new ArrayList<Availability>();
		expectedAvailabilities.add(availabilityOne);
		expectedAvailabilities.add(availabilityTwo);
		when(service.list()).thenReturn(expectedAvailabilities);
		ResponseEntity actualAvailabilities = controller.list();
		assertEquals(expectedAvailabilities, actualAvailabilities.getBody());
		
	}
	
	
}
