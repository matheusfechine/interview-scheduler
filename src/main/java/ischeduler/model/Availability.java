package ischeduler.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class Availability {

	private Interviewer interviewer;
	private Candidate candidate;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date initTime;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date endTime;
	
	
	
}
