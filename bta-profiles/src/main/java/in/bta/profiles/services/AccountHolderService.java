package in.bta.profiles.services;

import java.util.List;

import in.bta.profiles.entities.AccountHolder;
import in.bta.profiles.exceptions.AccountHolderException;

public interface AccountHolderService {
	AccountHolder add(AccountHolder ah) throws AccountHolderException;
	List<AccountHolder> getAll();
	AccountHolder getById(Long ahId);
	boolean exitsById(Long ahId);
	

}
