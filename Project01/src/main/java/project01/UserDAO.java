package project01;

import java.util.HashMap;

public interface UserDAO {

	boolean loginValidator(String username, String password);
	int userPage(String username);
	User getUserObj(User user);
	boolean insertTicket(Ticket ticket);
	HashMap<String, TicketItems> userPendingOrApproved(int userID, int status);
	User searchEmployee(int emp_num);
	HashMap<String, TicketItems> viewAllPending();
	boolean updateTicketStatus(int ticketID, String status);
	HashMap<String, TicketItems> viewAll();
	
}
