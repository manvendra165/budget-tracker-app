package in.bta.txns.services;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bta.txns.entities.AccountHolder;
import in.bta.txns.entities.Txn;
import in.bta.txns.entities.TxnType;
import in.bta.txns.exceptions.AccountHolderException;
import in.bta.txns.exceptions.TxnException;
import in.bta.txns.repos.AccountHolderRepo;
import in.bta.txns.repos.TxnRepo;

@Service
public class TxnServiceImpl implements TxnService {

	@Autowired
	private ProfilesClient profiles;

	@Autowired
	private AccountHolderRepo ahRepo;

	@Autowired
	private TxnRepo txnRepo;

	@Override
	public double getBalance(Long ahId) throws AccountHolderException {
		// TODO Auto-generated method stub
		AccountHolder ah = ahRepo.findById(ahId).orElse(null);

		return ah == null ? 0 : ah.getCurrentBalance();
	}

	@Override
	public List<Txn> getPeriodicTxnByAhId(Long ahId, LocalDate start, LocalDate end) throws AccountHolderException {
		// TODO Auto-generated method stub
		return txnRepo.getPeriodicTxnByAhId(ahId, start, end);
	}

	@Override
	public Txn getById(Long txnId) {
		// TODO Auto-generated method stub
		return txnRepo.findById(txnId).orElse(null);
	}

	@Transactional
	@Override
	public Txn add(Txn txn) throws AccountHolderException, TxnException {
		// TODO Auto-generated method stub
		if (txn.getHolder() == null)
			throw new TxnException("Txn can not be added to a non mentioned account holder");

		long ahId = txn.getHolder().getAhId();
		AccountHolder ah = ahRepo.findById(ahId).orElse(null);

		if (ah == null) {
			if (profiles.checkAccountHolderExists(ahId)) {
				ah = new AccountHolder(ahId, 0.0, new TreeSet<>());
			} else {
				throw new TxnException("Txn can not be added to a non mentioned account holder");
			}
		}
		double cb = ah.getCurrentBalance();
		ah.setCurrentBalance(txn.getType() == TxnType.CREDIT ? cb + txn.getAmount() : cb - txn.getAmount());
		txn.setHolder(ah);
		
		ahRepo.save(ah);
		return txnRepo.save(txn);
	}

	@Transactional
	@Override
	public void deleteById(Long txnId) throws TxnException {
		// TODO Auto-generated method stub

		if (!txnRepo.existsById(txnId))
			throw new TxnException("Txn not found to update");

		Txn oldTxn = txnRepo.findById(txnId).orElse(null);
		AccountHolder ah = oldTxn.getHolder();
		double cb = ah.getCurrentBalance();
		cb = oldTxn.getType() == TxnType.CREDIT ? cb - oldTxn.getAmount() : cb + oldTxn.getAmount();
		ah.setCurrentBalance(cb);
		ahRepo.save(ah);

		txnRepo.deleteById(txnId);

	}

	@Transactional
	@Override
	public Txn update(Txn txn) throws AccountHolderException, TxnException {
		// TODO Auto-generated method stub
		if (!txnRepo.existsById(txn.getTxnId()))
			throw new TxnException("Txn not found to update");

		Txn oldTxn = txnRepo.findById(txn.getTxnId()).orElse(null);
		AccountHolder ah = oldTxn.getHolder();
		double cb = ah.getCurrentBalance();
		cb = oldTxn.getType() == TxnType.CREDIT ? cb - oldTxn.getAmount() : cb + oldTxn.getAmount();
		cb = txn.getType() == TxnType.CREDIT ? cb + txn.getAmount() : cb - txn.getAmount();

		ah.setCurrentBalance(cb);
		ahRepo.save(ah);

		return txnRepo.save(txn);
	}

}
