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

import com.ischeduler.exception.InterviewerExistsException;
import com.ischeduler.model.Interviewer;
import com.ischeduler.service.InterviewerService;

@RestController
@RequestMapping("/api/interviewer")
public class InterviewerController {
	
	@Autowired
	private InterviewerService service;
	
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> register(@RequestBody Interviewer interviewer) {
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
	
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Interviewer>> list() {
		return ResponseEntity.ok(service.list());
	}
}
