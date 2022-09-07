package com.ischeduler.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ischeduler.exception.CandidateExistsException;
import com.ischeduler.model.Candidate;
import com.ischeduler.repository.CandidateRepository;
import com.ischeduler.service.CandidateService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {

	@InjectMocks
	private CandidateService service;
	
	@Mock
	private CandidateRepository repository;
	
	@Test(expected = CandidateExistsException.class)
	public void shouldThrowExceptionWhenCandidateAlreadyExists() throws CandidateExistsException {
		Candidate candidate = new Candidate();
		candidate.setName("Jose");
		when(repository.findByName(anyString())).thenReturn(candidate);
		service.register(candidate);
	}
	
	@Test
	public void shouldRegister() throws CandidateExistsException {
		Candidate expected = new Candidate();
		expected.setName("Jose");
		when(repository.save(any(Candidate.class))).thenReturn(expected);
		Candidate actual = service.register(expected);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldList() {
		Candidate expected = new Candidate();
		expected.setName("Jose");
		expected.setId(1);
		when(repository.findAll()).thenReturn(Arrays.asList(expected));
		List<Candidate> actual = service.list();
		assertEquals(expected, actual);
	}
}
