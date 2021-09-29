package project01;

public class TicketItems {

	private String ticketID;
	private String submitted;
	private String type;
	private String amount;
	private String description;
	
	public TicketItems(String ticketID,String submitted,String type,String amount,String description) {
		this.setTicketID(ticketID);
		this.setSubmitted(submitted);
		this.setType(type);
		this.setAmount(amount);
		this.setDescription(description);
	}
	
	public String getTicketID() {
		return ticketID;
	}
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
