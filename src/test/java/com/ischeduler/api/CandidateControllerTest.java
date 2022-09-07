package com.ischeduler.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ischeduler.exception.CandidateExistsException;
import com.ischeduler.model.Candidate;
import com.ischeduler.service.CandidateService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateControllerTest {
	
	@InjectMocks
	private CandidateController controller;

	@Mock
	private CandidateService service;
	
	@Test
	public void shouldRegister() throws CandidateExistsException {
		Candidate candidateOne = new Candidate();
		candidateOne.setId(1);
		candidateOne.setName("Matheus");
		when(service.register(any(Candidate.class))).thenReturn(candidateOne);
		ResponseEntity<Object> response = controller.register(new Candidate());
		verify(service).register(any(Candidate.class));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), candidateOne);
	}
	
	@Test
	public void shouldThrowExceptionWhenUserIsAlreadyRegistered() throws CandidateExistsException {
		doThrow(CandidateExistsException.class)
			.when(service).register(any(Candidate.class));
		ResponseEntity<Object> response = controller.register(new Candidate());
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void shouldListAll() throws CandidateExistsException {
		Candidate candidateOne = new Candidate();
		candidateOne.setId(1);
		candidateOne.setName("Matheus");
		List<Candidate> expectedList = new ArrayList<Candidate>();
		expectedList.add(candidateOne);
		when(service.list()).thenReturn(expectedList);
		ResponseEntity<List<Candidate>> response = controller.list();
		verify(service).list();
		List<Candidate> actualList = response.getBody();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(expectedList, actualList);
	}
}
