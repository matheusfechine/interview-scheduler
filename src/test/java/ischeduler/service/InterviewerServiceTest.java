package ischeduler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ischeduler.exception.InterviewerExistsException;
import ischeduler.model.Interviewer;

public class InterviewerServiceTest {

	@InjectMocks
	private InterviewerService interviewerService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRetrieveByName() throws InterviewerExistsException {
		Interviewer expectedInterviewer = new Interviewer();
		expectedInterviewer.setId(1);
		expectedInterviewer.setName("Jose");
		interviewerService.register(expectedInterviewer);
		Interviewer actualInterviewer = interviewerService.findBy("Jose");
		assertEquals(expectedInterviewer, actualInterviewer);
	}

	@Test
	public void shouldNotRetrieveByName() throws InterviewerExistsException {
		Interviewer expectedInterviewer = new Interviewer();
		expectedInterviewer.setId(1);
		expectedInterviewer.setName("Jose");
		Interviewer actualInterviewer = interviewerService.findBy("Jose");
		assertNull(actualInterviewer);
	}
}
