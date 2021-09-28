package project01;

public interface UserDAO {

	boolean loginValidator(String username, String password);
	int userPage(String username);
	User getUserObj(User user);
	boolean insertTicket(Ticket ticket);
}
