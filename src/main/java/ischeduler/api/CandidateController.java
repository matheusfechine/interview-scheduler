package ischeduler.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ischeduler.exception.CandidateExistsException;
import ischeduler.model.Candidate;
import ischeduler.service.CandidateService;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService service;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity register(@RequestBody Candidate candidate) {
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
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity list() {
		return ResponseEntity.ok(service.list());
	}
}
