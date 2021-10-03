package project01;

public class TicketItems {

	private String author;
	private String ticketID;
	private String resolver;
	private String submitted;
	private String resolved;
	private String type;
	private String amount;
	private String description;
	
	public TicketItems(String author, String ticketID,String resolver,String submitted,String resolved,String type,String amount,String description) {
		this.setAuthor(author);
		this.setTicketID(ticketID);
		this.setResolver(resolver);
		this.setSubmitted(submitted);
		this.setResolved(resolved);
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

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
