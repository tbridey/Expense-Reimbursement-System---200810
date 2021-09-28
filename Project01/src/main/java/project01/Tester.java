package project01;

import static org.junit.Assert.*;
import org.junit.*;


public class Tester {

	@Ignore
	public void testLoginValidator() {
		UserOracleDAO dao=new UserOracleDAO();
		boolean login = dao.loginValidator("tbridey", "endofline");
		assertTrue(login);
	}
	
	@Ignore
	public void testUserPage() {
		UserOracleDAO dao=new UserOracleDAO();
		int i = dao.userPage("tbridey");
		assertEquals(1, i);
	}
	
	@Test
	public void testGetUserObj() {
		User b=new User("tbridey","endofline","Taelor","McBride","taelor.mcbride@revature.net",1234,1);
		UserOracleDAO dao=new UserOracleDAO();
		User a=dao.getUserObj(b);
		assertEquals(b.getEmail(),a.getEmail());
	}
}
