package ischeduler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import ischeduler.exception.InterviewerExistsException;
import ischeduler.model.Interviewer;
import ischeduler.repository.InterviewerRepository;

@RunWith(MockitoJUnitRunner.class)
public class InterviewerServiceTest {

	@InjectMocks
	private InterviewerService interviewerService;
	
	@Mock
	private InterviewerRepository repository;

	@Test
	public void shouldRetrieveByName() throws InterviewerExistsException {
		Interviewer expectedInterviewer = new Interviewer();
		expectedInterviewer.setId(1);
		expectedInterviewer.setName("Jose");
		when(repository.findByName(anyString())).thenReturn(expectedInterviewer);
		Interviewer actualInterviewer = interviewerService.findBy("Jose");
		assertEquals(expectedInterviewer, actualInterviewer);
	}

	@Test
	public void shouldNotRetrieveByName() throws InterviewerExistsException {
		when(repository.findByName(anyString())).thenReturn(null);
		Interviewer actualInterviewer = interviewerService.findBy("Jose");
		assertNull(actualInterviewer);
	}
}
