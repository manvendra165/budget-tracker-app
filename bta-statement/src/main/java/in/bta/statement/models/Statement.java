package in.bta.statement.models;

import java.time.LocalDate;
import java.util.Set;

public class Statement {

	AccountHolder holder;
	Set<Txn> txns;
	double totalCredit;
	double totalDebit;
	double statementBalance;
	LocalDate start;
	LocalDate end;
	public Statement(AccountHolder holder, Set<Txn> txns, double totalCredit, double totalDebit,
			double statementBalance, LocalDate start, LocalDate end) {
		super();
		this.holder = holder;
		this.txns = txns;
		this.totalCredit = totalCredit;
		this.totalDebit = totalDebit;
		this.statementBalance = statementBalance;
		this.start = start;
		this.end = end;
	}
	public Statement() {
		super();
	}
	public AccountHolder getHolder() {
		return holder;
	}
	public Set<Txn> getTxns() {
		return txns;
	}
	public double getTotalCredit() {
		return totalCredit;
	}
	public double getTotalDebit() {
		return totalDebit;
	}
	public double getStatementBalance() {
		return statementBalance;
	}
	public LocalDate getStart() {
		return start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}
	public void setTxns(Set<Txn> txns) {
		this.txns = txns;
	}
	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}
	public void setTotalDebit(double totalDebit) {
		this.totalDebit = totalDebit;
	}
	public void setStatementBalance(double statementBalance) {
		this.statementBalance = statementBalance;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	
	
}
