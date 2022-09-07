package com.ischeduler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ischeduler.exception.InterviewerExistsException;
import com.ischeduler.model.Interviewer;
import com.ischeduler.repository.InterviewerRepository;
import com.ischeduler.service.InterviewerService;

@RunWith(MockitoJUnitRunner.class)
public class InterviewerServiceTest {

	@InjectMocks
	private InterviewerService service;
	
	@Mock
	private InterviewerRepository repository;

	@Test
	public void shouldRetrieveByName() throws InterviewerExistsException {
		Interviewer expectedInterviewer = new Interviewer();
		expectedInterviewer.setId(1);
		expectedInterviewer.setName("Jose");
		when(repository.findByName(anyString())).thenReturn(expectedInterviewer);
		Interviewer actualInterviewer = service.findBy("Jose");
		assertEquals(expectedInterviewer, actualInterviewer);
	}

	@Test
	public void shouldNotRetrieveByName() throws InterviewerExistsException {
		when(repository.findByName(anyString())).thenReturn(null);
		Interviewer actualInterviewer = service.findBy("Jose");
		assertNull(actualInterviewer);
	}
	
	@Test(expected = InterviewerExistsException.class)
	public void shouldThrowExceptionWhenInterviewerAlreadyExists() throws InterviewerExistsException {
		Interviewer interviewer = new Interviewer();
		interviewer.setName("Ingrid");
		when(repository.findByName(anyString())).thenReturn(interviewer);
		service.register(interviewer);
	}
	
	@Test
	public void shouldRegister() throws InterviewerExistsException {
		Interviewer expected = new Interviewer();
		expected.setName("Ingrid");
		expected.setId(1);
		when(repository.save(any(Interviewer.class))).thenReturn(expected);
		Interviewer actual = service.register(expected);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldList() {
		Interviewer expected = new Interviewer();
		expected.setName("Ingrid");
		expected.setId(1);
		when(repository.findAll()).thenReturn(Arrays.asList(expected));
		List<Interviewer> actual = service.list();
		assertEquals(expected, actual);
	}
	
}
