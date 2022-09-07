package com.ischeduler.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ischeduler.exception.CandidateExistsException;
import com.ischeduler.model.Candidate;
import com.ischeduler.service.CandidateService;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService service;
	
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> register(@RequestBody Candidate candidate) {
		Candidate registered;
		try {
			registered = service.register(candidate);
		} catch (CandidateExistsException e) {
			return ResponseEntity
					.badRequest()
					.body(String.format("Error: '%s'", e.getMessage()));
		}
		return ResponseEntity.ok(registered);
	}
	
	@GetMapping(value = "/ping", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Candidate> health() {
		return new ResponseEntity<Candidate>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Candidate>> list() {
		return ResponseEntity.ok(service.list());
	}
	
	
}
