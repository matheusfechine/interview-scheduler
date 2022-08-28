package ischeduler.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.text.ParseException;

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

import ischeduler.model.Availability;
import ischeduler.service.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
	
	@Autowired
	private AvailabilityService service;
	
	@SuppressWarnings({ "static-access", "rawtypes" })
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity register(@RequestBody Availability availability)
			throws ParseException {
		try {
			service.add(availability);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST)
					.ok(String.format("An error occured: %s", e.getMessage())); 
		}
		return ResponseEntity.ok("Success!");
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity list() {
		return ResponseEntity.ok(service.list());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/list/interviewer/{interviewerName}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity listByInterviewer(@PathVariable("interviewerName") String name) {
		return ResponseEntity.ok(service.listByInterviewer(name));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/list/candidate/{candidateName}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity listByCandidate(@PathVariable("candidateName") String name) {
		return ResponseEntity.ok(service.listByCandidate(name));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/arrange", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity listBy(@RequestBody Availability availability) {
		return ResponseEntity.ok(service.listForArrangeBy(availability));
	}
	
}
