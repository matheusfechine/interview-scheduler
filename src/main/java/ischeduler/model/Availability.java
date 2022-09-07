package ischeduler.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @EqualsAndHashCode @ToString
public class Availability {

	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERVIEWER", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Interviewer interviewer;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CANDIDATE", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Candidate candidate;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date initTime;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date endTime;
	
	
	
}
