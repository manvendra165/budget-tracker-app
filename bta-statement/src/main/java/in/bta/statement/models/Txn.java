package in.bta.statement.models;

import java.time.LocalDate;

public class Txn implements Comparable<Txn>{

	private Long txnId;
	private String header;
	private LocalDate txnDate;
	private TxnType type;
	private Double amount;

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

	public Txn() {
		super();
	}
	
	
	public Txn(Long txnId, String header, LocalDate txnDate, TxnType type, Double amount) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.txnDate = txnDate;
		this.type = type;
		this.amount = amount;
	}

	public int compareTo(Txn t) {
		return txnId == null?1:txnId.compareTo(t.txnId);
	}
}
