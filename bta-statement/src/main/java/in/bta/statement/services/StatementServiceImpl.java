package in.bta.statement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bta.statement.exceptions.StatementException;
import in.bta.statement.models.AccountHolder;
import in.bta.statement.models.Statement;
import in.bta.statement.models.Txn;
import in.bta.statement.models.TxnType;

@Service
public class StatementServiceImpl implements StatementService{

	@Autowired
	private ProfilesClient profilesClient;
	
	@Autowired
	private TxnsClient txnsClient;
	
	private double computeTotal(List<Txn> txns, TxnType type) {
		return txns.stream().filter(t -> t.getType() == type).mapToDouble(Txn::getAmount).sum();
	}
	
	
	@Override
	public Statement getSatement(long ahId, LocalDate start, LocalDate end) throws StatementException {
		// TODO Auto-generated method stub
		
		if(!profilesClient.checkAccountHolderExists(ahId))
			throw new StatementException("Account does not exixts");
		
		AccountHolder ah = profilesClient.getAccountHolder(ahId);
		List<Txn> txns = txnsClient.getTxns(ahId, start, end);
		ah.setCurrentBalance(txnsClient.getBalance(ahId));
		double totalCredit = computeTotal(txns, TxnType.CREDIT);
		double totalDebit = computeTotal(txns, TxnType.DEBIT);
		double statementBalance = totalCredit - totalDebit;
	
		return new Statement(ah, new TreeSet<>(txns), totalCredit, totalDebit, statementBalance, start, end);
	}

}
