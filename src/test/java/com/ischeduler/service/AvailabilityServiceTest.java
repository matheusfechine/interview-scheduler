package com.ischeduler.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ischeduler.exception.AvailabilityExpeption;
import com.ischeduler.exception.CandidateNotFoundException;
import com.ischeduler.exception.InterviewerNotFoundException;
import com.ischeduler.model.Availability;
import com.ischeduler.model.Candidate;
import com.ischeduler.model.Interviewer;
import com.ischeduler.repository.AvailabilityRepository;
import com.ischeduler.service.AvailabilityService;
import com.ischeduler.service.InterviewerService;
import com.ischeduler.validation.Validations;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityServiceTest {

	@InjectMocks
	private AvailabilityService service;

	@Mock
	private Validations validation;
	
	@Mock
	private AvailabilityRepository repository;
	
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
	public void shouldAddAvailability() throws AvailabilityExpeption, InterviewerNotFoundException, CandidateNotFoundException {
		List<Availability> expectedAvailabilities = new ArrayList<Availability>();
		DateTime scheduledDay = new DateTime(baseDate);
		scheduledDay.withDayOfMonth(1).withMonthOfYear(1).withYear(2022);
		expectedAvailabilities.add(availabilityOne);
		expectedAvailabilities.add(availabilityTwo);
		expectedAvailabilities.add(availabilityThree);
		
		when(repository.findAll()).thenReturn(expectedAvailabilities);
		
		List<Availability> actualAvailabilities = service.add(new Availability());

		assertEquals(expectedAvailabilities, actualAvailabilities);

	}

	@Test
	public void shouldListByInterviewer() throws AvailabilityExpeption, InterviewerNotFoundException, CandidateNotFoundException {
		List<Availability> expectedAvailabilities = new ArrayList<Availability>();
		DateTime scheduledDay = new DateTime(baseDate);
		scheduledDay.withDayOfMonth(1).withMonthOfYear(1).withYear(2022);
		Availability availabilityFour = new Availability();
		availabilityFour.setInterviewer(new Interviewer());
		availabilityFour.getInterviewer().setName("Jose");
		availabilityFour.setInitTime(baseDate.withHourOfDay(10).toDate());
		availabilityFour.setEndTime(baseDate.withHourOfDay(11).toDate());
		expectedAvailabilities.add(availabilityFour);
		
		when(repository.findByInterviewerName(anyString())).thenReturn(expectedAvailabilities);
		
		List<Availability> actualAvailabilities = service.listByInterviewer("Jose");
		
		assertEquals(expectedAvailabilities, actualAvailabilities);
	}
	
	@Test
	public void shouldArrangeInterviewerAndCandidateSlots() throws AvailabilityExpeption, InterviewerNotFoundException, CandidateNotFoundException {
		DateTime scheduledDay = new DateTime(baseDate);
		scheduledDay.withDayOfMonth(1).withMonthOfYear(1).withYear(2022);
		Availability candidateCarlToBeRegistered = new Availability();
		Candidate carl = new Candidate();
		carl.setName("Carl");
		candidateCarlToBeRegistered.setCandidate(carl);
		candidateCarlToBeRegistered.setInitTime(scheduledDay.withHourOfDay(10).withMinuteOfHour(0).toDate());
		candidateCarlToBeRegistered.setEndTime(scheduledDay.withHourOfDay(11).withMinuteOfHour(0).toDate());
		
		when(repository.findByCandidateName(anyString())).thenReturn(Arrays.asList(candidateCarlToBeRegistered));
		
		Availability interviewerIngridToBeRegistered = new Availability();
		Interviewer ingrid = new Interviewer();
		ingrid.setName("Ingrid");
		interviewerIngridToBeRegistered.setInterviewer(ingrid);
		interviewerIngridToBeRegistered.setInitTime(scheduledDay.withHourOfDay(10).withMinuteOfHour(0).toDate());
		interviewerIngridToBeRegistered.setEndTime(scheduledDay.withHourOfDay(13).withMinuteOfHour(0).toDate());
		
		when(repository.findByInterviewerName(anyString())).thenReturn(Arrays.asList(interviewerIngridToBeRegistered));
		
		Availability search = new Availability();
		search.setCandidate(carl);
		search.setInterviewer(ingrid);
		
		List<Availability> actualAvailability = service.listForArrangeBy(search);
		assertEquals(Arrays.asList(candidateCarlToBeRegistered), actualAvailability);
		
	}
	
}
