# interview-scheduler

This project solves a Challenge for Bridge351.

### How to Run the project

since you have Maven previously installed on your computer, just run the command on the root of the project

```mvn spring-boot:run```

## Understanding the Project

This project consists basically in 3 Entities.
| Entity | Description |
|--------|-------------|
| Candidate | represents the Candidate |
| Interviewer | represents the Interviewer |
| Availability | represents slots available of each Candidate and Interviwer |

### API Calls

Basepath: 
```
http://localhost:8080/scheduler/api/
```

Endpoints:

| Http Method | Endpoint			| Body | Description |
|-------------|---------------| -------------|---------------|
| POST 		  | /candidate/register | {"name":"Carl"} | registers a Candidate |
| GET 		  | /candidate/list | None | List all Candidates |
| POST 		  | /availability/register | {"candidate":{"name":"Jose"},"initTime":"2022-01-01 10:00","endTime":"2022-01-01 15:00"} | Register Availability for a Candidate with interval of hours. Date format must be in "yyyy-MM--dd hh:mm" pattern |
| POST 		  | /interviewer/register | {"name":"Ingrid"} | registers an Interviewer |
| GET 		  | /interviewer/list | None | List all Interviewers |
| POST 		  | /availability/register | {"interviewer":{"name":"Ingrid"},"initTime":"2022-01-01 10:00","endTime":"2022-01-01 15:00"} | Register Availability for an Interviewer with interval of hours. Date format must be in "yyyy-MM--dd hh:mm" pattern |
| POST 		  | /availability/arrange | {"interviewer":{"name":"Ingrid"},"candidate":{"name":"Jose"}} | It will match periods that Interviewer and Candidates can schedule an interview |




