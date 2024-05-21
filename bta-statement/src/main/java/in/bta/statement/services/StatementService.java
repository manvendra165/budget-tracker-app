package in.bta.statement.services;

import java.time.LocalDate;

import in.bta.statement.exceptions.StatementException;
import in.bta.statement.models.Statement;

public interface StatementService {
	Statement getSatement (long ahId, LocalDate start,LocalDate end) throws StatementException;

}
