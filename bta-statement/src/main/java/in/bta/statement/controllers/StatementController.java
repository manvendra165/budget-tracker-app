package in.bta.statement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bta.statement.exceptions.StatementException;
import in.bta.statement.models.Statement;
import in.bta.statement.services.StatementService;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
public class StatementController {
	
	@Autowired
	private StatementService statementService;
	
	@GetMapping("/{ahId}/{year}")
	public ResponseEntity<Statement> getAnnualStatement(
			@PathVariable("ahId") Long ahId,
			@PathVariable("year") int year) 
	throws StatementException{
		LocalDate start = LocalDate.of(year, Month.JANUARY, 1);
		LocalDate end = start.with(TemporalAdjusters.lastDayOfYear());
		
		return ResponseEntity.ok(statementService.getSatement(ahId, start, end));
		
	}
	
	@GetMapping("/{ahId}/{year}/{month}")
	public ResponseEntity<Statement> getMonthlyStatement(
			@PathVariable("ahId") Long ahId,
			@PathVariable("year") int year,
			@PathVariable("month") Month month) 
	throws StatementException{
		LocalDate start = LocalDate.of(year, month, 1);
		LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());
		
		return ResponseEntity.ok(statementService.getSatement(ahId, start, end));
		
	}
	

}
