package project01;

public class TicketStatus {
	private int ticketID;
	private String status="";
	
	public TicketStatus() {}
	
	public TicketStatus(int ticketID, String stat) {
		this.setStatus(stat);
		this.setTicketID(ticketID);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	
}
