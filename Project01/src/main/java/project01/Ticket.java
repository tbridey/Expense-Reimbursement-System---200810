package project01;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.Random;

public class Ticket implements Serializable{

	Random ran = new Random();
	
	private int ticketID=ran.nextInt(10000);
	private double amount;
	private Date submitted;
	private Date resolved;
	private String description;
	//private Blob reciept;
	private int author;
	private int resolver;
	private int statusID;
	private int typeID;	

	public Ticket() {}
	
	public Ticket(int ticketID, double amount, Date submitted, Date resolved, String description,
			int author, int resolver, int statusID, int typeID) {
		super();
		this.ticketID = ticketID;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		//this.reciept = null;
		this.author = author;
		this.resolver = resolver;
		this.statusID = statusID;
		this.typeID = typeID;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//
//	public Blob getReciept() {
//		return reciept;
//	}
//
//	public void setReciept(Blob reciept) {
//		this.reciept = reciept;
//	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

}
