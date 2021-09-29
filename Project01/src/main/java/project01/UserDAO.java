package project01;

import java.util.HashMap;

public interface UserDAO {

	boolean loginValidator(String username, String password);
	int userPage(String username);
	User getUserObj(User user);
	boolean insertTicket(Ticket ticket);
	HashMap<String, TicketItems> viewUserPending(int userID);
}
