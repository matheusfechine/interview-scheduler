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

import ischeduler.exception.InterviewerExistsException;
import ischeduler.model.Interviewer;
import ischeduler.service.InterviewerService;

@RestController
@RequestMapping("/api/interviewer")
public class InterviewerController {
	
	@Autowired
	private InterviewerService service;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity register(@RequestBody Interviewer interviewer) {
		Interviewer registered;
		try {
			registered = service.register(interviewer);
		} catch (InterviewerExistsException e) {
			return ResponseEntity
					.badRequest()
					.body(String.format("Error: '%s'", e.getMessage()));
		}
		return ResponseEntity.ok(registered);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity list() {
		return ResponseEntity.ok(service.list());
	}
}
