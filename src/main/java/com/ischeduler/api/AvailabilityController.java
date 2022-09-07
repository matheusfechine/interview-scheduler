package com.ischeduler.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ischeduler.model.Availability;
import com.ischeduler.service.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
	
	@Autowired
	private AvailabilityService service;
	
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> register(@RequestBody Availability availability)
			throws ParseException {
		try {
			service.add(availability);
		} catch (Exception e) {
			return ResponseEntity
					.badRequest()
					.body((String.format("An error occured: %s", e.getMessage())));	
			}
		return ResponseEntity.ok("Success!");
	}
	
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Availability>> list() {
		return ResponseEntity.ok(service.list());
	}
	
	@GetMapping(value = "/list/interviewer/{interviewerName}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Availability>> listByInterviewer(@PathVariable("interviewerName") String name) {
		return ResponseEntity.ok(service.listByInterviewer(name));
	}
	
	@GetMapping(value = "/list/candidate/{candidateName}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Availability>> listByCandidate(@PathVariable("candidateName") String name) {
		return ResponseEntity.ok(service.listByCandidate(name));
	}
	
	@PostMapping(value = "/arrange", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Availability>> arrange(@RequestBody Availability availability) {
		return ResponseEntity.ok(service.listForArrangeBy(availability));
	}
	
}
