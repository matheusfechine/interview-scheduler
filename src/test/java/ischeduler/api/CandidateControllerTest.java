package ischeduler.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ischeduler.exception.CandidateExistsException;
import ischeduler.model.Candidate;
import ischeduler.service.CandidateService;

public class CandidateControllerTest {
	
	@InjectMocks
	private CandidateController controller;

	@Mock
	private CandidateService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegister() throws CandidateExistsException {
		Candidate candidateOne = new Candidate();
		candidateOne.setId(1);
		candidateOne.setName("Matheus");
		when(service.register(any(Candidate.class))).thenReturn(candidateOne);
		ResponseEntity<Candidate> response = controller.register(new Candidate());
		verify(service).register(any(Candidate.class));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getName(), candidateOne.getName());
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void shouldThrowExceptionWhenUserIsAlreadyRegistered() throws CandidateExistsException {
		doThrow(CandidateExistsException.class)
			.when(service).register(any(Candidate.class));
		ResponseEntity response = controller.register(new Candidate());
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void shouldListAll() throws CandidateExistsException {
		Candidate candidateOne = new Candidate();
		candidateOne.setId(1);
		candidateOne.setName("Matheus");
		List<Candidate> expectedList = new ArrayList();
		expectedList.add(candidateOne);
		when(service.list()).thenReturn(expectedList);
		ResponseEntity response = controller.list();
		verify(service).list();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<Candidate> actualList = (List<Candidate>) response.getBody();
		assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
	}
}
