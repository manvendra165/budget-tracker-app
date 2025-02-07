package in.bta.txns.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="txns")
public class Txn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txnId;
	@NotBlank(message = "header is a mandate field")
	private String header;
	
	@NotNull(message = "txnDate is a mandate field")
	@PastOrPresent(message = "txnDate Can not be of future")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate txnDate;
	
	@Enumerated
	@NotNull(message ="type is a mandate field")
	private TxnType type;
	
	@NotNull(message ="type is a mandate field")
	@Min(value = 0, message = "amnt can not be negative")
	private Double amount;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private AccountHolder holder;

	public Long getTxnId() {
		return txnId;
	}

	public String getHeader() {
		return header;
	}

	public LocalDate getTxnDate() {
		return txnDate;
	}

	public TxnType getType() {
		return type;
	}

	public Double getAmount() {
		return amount;
	}

	public AccountHolder getHolder() {
		return holder;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setTxnDate(LocalDate txnDate) {
		this.txnDate = txnDate;
	}

	public void setType(TxnType type) {
		this.type = type;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}

	public Txn(Long txnId, @NotBlank(message = "header is a mandate field") String header,
			@NotNull(message = "txnDate is a mandate field") @PastOrPresent(message = "txnDate Can not be of future") LocalDate txnDate,
			TxnType type,
			@NotNull(message = "type is a mandate field") @Min(value = 0, message = "amnt can not be negative") Double amount,
			AccountHolder holder) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.txnDate = txnDate;
		this.type = type;
		this.amount = amount;
		this.holder = holder;
	}

	public Txn() {
		super();
	}
	
	public int compareTo(Txn t) {
		return txnId == null?1:txnId.compareTo(t.txnId);
	}
}
