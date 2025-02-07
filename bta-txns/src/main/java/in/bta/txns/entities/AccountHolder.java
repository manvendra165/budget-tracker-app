package in.bta.txns.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name="ahs")
public class AccountHolder {

	@Id
	private Long ahId;
	private Double currentBalance;
	
	@JsonProperty(access= Access.WRITE_ONLY)
	@OneToMany(mappedBy = "holder", cascade =CascadeType.ALL)
	private Set<Txn> txns;

	public Long getAhId() {
		return ahId;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public Set<Txn> getTxns() {
		return txns;
	}

	public void setAhId(Long ahId) {
		this.ahId = ahId;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setTxns(Set<Txn> txns) {
		this.txns = txns;
	}

	public AccountHolder(Long ahId, Double currentBalance, Set<Txn> txns) {
		super();
		this.ahId = ahId;
		this.currentBalance = currentBalance;
		this.txns = txns;
	}

	public AccountHolder() {
		super();
	}
	
	
}
