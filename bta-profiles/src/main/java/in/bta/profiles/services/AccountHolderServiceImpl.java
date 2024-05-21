package in.bta.profiles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bta.profiles.entities.AccountHolder;
import in.bta.profiles.exceptions.AccountHolderException;
import in.bta.profiles.repos.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Override
	public AccountHolder add(AccountHolder ah) throws AccountHolderException {
		if(ah.getAhId() != null && ahRepo.existsById(ah.getAhId()))
			throw new AccountHolderException("A record with the given id is already exist");
				return ahRepo.save(ah);
	}

	@Override
	public List<AccountHolder> getAll() {
		return ahRepo.findAll();
	}

	@Override
	public AccountHolder getById(Long ahId) {
		
		return ahRepo.findById(ahId).orElse(null);
	}

	@Override
	public boolean exitsById(Long ahId) {
		
		return ahRepo.existsById(ahId);
	}
	
	

}
