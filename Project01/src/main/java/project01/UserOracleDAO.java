package project01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class UserOracleDAO implements UserDAO {

	@Override
	public boolean loginValidator(String username, String password) {
		
		final String query="select ers_password from ers_users where ers_username=?";
		final String un=username;
		final String pw=password;
		try {
			Connection conn = DatabaseConnector.connector();
			
			//System.out.println(conn);
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, un);
			
			ResultSet rs = prepStmt.executeQuery();
			
			if(rs.next()) {
				if(pw.equals(rs.getString("ers_password"))) {
					return true;
				}
				else {
					//System.out.println("Error: incorrect username or password");
					return false;
				}
			}
			else {
				System.out.println("Result set returned nothing");
				return false;
			}
		}catch(SQLException e){
			System.out.println("ERROR: Validator SQL exception");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int userPage(String username) {
		final String query="select user_role_id from ers_users where ers_username=?";
		final String un=username;
		try {
			Connection conn = DatabaseConnector.connector();
			
			//System.out.println(conn);
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, un);
			
			ResultSet rs = prepStmt.executeQuery();
			
			if(rs.next()) {
				int roleID=rs.getInt(1);
				return roleID;
			}
			else {
				System.out.println("Result set returned nothing");
				return -1;
			}
		}catch(SQLException e){
			System.out.println("ERROR: user page SQL exception");
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public User getUserObj(User user) {
		final String query="select * from ers_users where ers_username=?";
		final String un=user.getUsername();
		try {
			Connection conn = DatabaseConnector.connector();
			
			//System.out.println(conn);
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, un);
			
			ResultSet rs = prepStmt.executeQuery();
			rs.next();
			
			String uName=rs.getString("ers_username");
			String pass=rs.getString("ers_password");
			String fName=rs.getString("user_first_name");
			String lName=rs.getString("user_last_name");
			String email=rs.getString("user_email");
			int uID=rs.getInt("ers_users_id");
			int rID=rs.getInt("user_role_id");
			
			User userObj=new User(uName,pass,fName,lName,email,uID,rID,null);
			
			return userObj;
			
		}catch(SQLException e){
			System.out.println("ERROR: user object SQL exception");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertTicket(Ticket t) {
		final String query = "insert into ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)"+"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = DatabaseConnector.connector();
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setInt(1, t.getTicketID());
			prepStmt.setDouble(2, t.getAmount());
			prepStmt.setDate(3, t.getSubmitted());
			prepStmt.setDate(4, null);
			prepStmt.setString(5, t.getDescription());
			prepStmt.setString(6, null);
			prepStmt.setInt(7, t.getAuthor());
			prepStmt.setInt(8, t.getResolver());
			prepStmt.setInt(9, t.getStatusID());
			prepStmt.setInt(10, t.getTypeID());
			
			prepStmt.executeUpdate();
			
			return true;
		}catch(SQLException e){
			System.out.println("ERROR: insert ticket SQL exception");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HashMap<String, TicketItems> userPendingOrApproved(int userID, int status) {
		final String query = 
				"SELECT er.reimb_id, eu.user_first_name, er.reimb_submitted, er.reimb_resolved, ert.REIMB_TYPE, er.reimb_amount, er.reimb_description "
				+"FROM ERS_REIMBURSEMENT er "
				+"JOIN ERS_REIMBURSEMENT_TYPE ert "
				+"ON er.REIMB_TYPE_ID = ert.REIMB_TYPE_ID "
				+"JOIN ERS_USERS eu "
				+"ON er.REIMB_RESOLVER = eu.ERS_USERS_ID "
				+"WHERE er.REIMB_AUTHOR = ? "
				+"AND er.REIMB_STATUS_ID = ?";
		try {
			
			HashMap<String, TicketItems> tickets= new HashMap<String, TicketItems>();
			
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, userID);
			prepStmt.setInt(2, status);
			
			ResultSet rs = prepStmt.executeQuery();
										 
			int i=1;
			while(rs.next()) {
				
				String id = Integer.toString(rs.getInt(1));
				String rev = rs.getString(2);
				String sub = (rs.getDate(3).toString());
				String res;
				rs.getDate(4);
				if(rs.wasNull()) {
					res = "";
					//System.out.println("if: "+res);
				}
				else {
					res = rs.getDate(4).toString();
					//System.out.println("else: "+res);
				}
				String type = rs.getString(5);
				String am = Double.toString(rs.getDouble(6));
				String des = rs.getString(7);
				
				TicketItems ticketItem = new TicketItems(null, id, rev, sub, res, type, am, des);
				
				tickets.put("ticket"+i, ticketItem);
				i++;
				//System.out.println(tickets);
			}
			return tickets;
			
		}catch(SQLException e){
			System.out.println("ERROR: view user pending SQL exception");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User searchEmployee(int emp_num) {
		final String query="SELECT eu.USER_FIRST_NAME, eu.USER_LAST_NAME, eu.ERS_USERNAME, eu.USER_EMAIL, eur.USER_ROLE, eu.ERS_USERS_ID "
		+"FROM ERS_USERS eu "
		+"JOIN ERS_USER_ROLES eur " 
		+"ON eu.USER_ROLE_ID = eur.ERS_USER_ROLE_ID "
		+"WHERE eu.ERS_USERS_ID = ?";
		
		try {
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, emp_num);
			
			ResultSet rs = prepStmt.executeQuery();
			rs.next();
			
			String fName=rs.getString("user_first_name");
			String lName=rs.getString("user_last_name");
			String uName=rs.getString("ers_username");
			String email=rs.getString("user_email");
			String role=rs.getString("user_role");
			int uID=rs.getInt("ers_users_id");
			
			User userObj=new User(uName,null,fName,lName,email,uID,0,role);
			
			return userObj;
			
		}catch(SQLException e){
			System.out.println("ERROR: search employee SQL exception");
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public HashMap<String, TicketItems> viewAllPending() {
		final String query = 
				"SELECT eu.USER_LAST_NAME , er. REIMB_ID , er.REIMB_SUBMITTED , ert.REIMB_TYPE , er.REIMB_AMOUNT , er.REIMB_DESCRIPTION "
				+ "FROM ERS_REIMBURSEMENT er "
				+ "JOIN ERS_REIMBURSEMENT_TYPE ert "
				+ "ON er.REIMB_TYPE_ID = ert.REIMB_TYPE_ID "
				+ "JOIN ERS_USERS eu "
				+ "ON er.REIMB_AUTHOR = eu.ERS_USERS_ID "
				+ "WHERE er.REIMB_STATUS_ID = ?";
		try {
			
			HashMap<String, TicketItems> tickets= new HashMap<String, TicketItems>();
			
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, 0);
			
			ResultSet rs = prepStmt.executeQuery();
										 
			int i=1;
			while(rs.next()) {
				
				String ln = rs.getString(1);
				String id = Integer.toString(rs.getInt(2));
				String sub = (rs.getDate(3).toString());
				String type = rs.getString(4);
				String am = Double.toString(rs.getDouble(5));
				String des = rs.getString(6);
				
				TicketItems ticketItem = new TicketItems(ln, id, null, sub, null, type, am, des);
				
				tickets.put("ticket"+i, ticketItem);
				i++;
				//System.out.println(tickets);
			}
			return tickets;
			
		}catch(SQLException e){
			System.out.println("ERROR: view user pending SQL exception");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateTicketStatus(int ticketID, String status) {
		final String query = "update ERS_REIMBURSEMENT set REIMB_STATUS_ID=? where REIMB_ID=?";
		int statusID=0;
		
		switch(status) {
		case "approved": statusID = 1;
		break;
		case "denied": statusID = 2;
		break;
		}
		
		try {
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt=conn.prepareStatement(query);
			
			prepStmt.setInt(1,statusID);
			prepStmt.setInt(2,ticketID);
			
			prepStmt.executeUpdate();
			
			return true;
			
		}catch(SQLException e){
			System.out.println("ERROR: update ticket stat SQL exception");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HashMap<String, TicketItems> viewAll() {
		final String query = 
				" SELECT er.REIMB_ID, eu.USER_LAST_NAME, er.REIMB_RESOLVER, er.REIMB_SUBMITTED, er.REIMB_RESOLVED , ert.REIMB_TYPE, er.REIMB_AMOUNT, er.REIMB_DESCRIPTION "
				+ "FROM ERS_REIMBURSEMENT er "
				+ "JOIN ERS_REIMBURSEMENT_TYPE ert "
				+ "ON er.REIMB_TYPE_ID = ert.REIMB_TYPE_ID "
				+ "JOIN ERS_USERS eu "
				+ "ON er.REIMB_AUTHOR = eu.ERS_USERS_ID ";
		try {
			
			HashMap<String, TicketItems> tickets= new HashMap<String, TicketItems>();
			
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			ResultSet rs = prepStmt.executeQuery();
										 
			int i=1;
			while(rs.next()) {
				
				String id = Integer.toString(rs.getInt(1));
				String ln = rs.getString(2);
				String rev = Integer.toString(rs.getInt(3));
				String sub = (rs.getDate(4).toString());
				String res;
				rs.getDate(5);
				if(rs.wasNull()) {
					res = "";
					//System.out.println("if: "+res);
				}
				else {
					res = rs.getDate(5).toString();
					//System.out.println("else: "+res);
				}
				String type = rs.getString(6);
				String am = Double.toString(rs.getDouble(7));
				String des = rs.getString(8);
				
				TicketItems ticketItem = new TicketItems(ln, id, rev, sub, res, type, am, des);
				
				tickets.put("ticket"+i, ticketItem);
				i++;
				//System.out.println(tickets);
			}
			return tickets;
			
		}catch(SQLException e){
			System.out.println("ERROR: view user pending SQL exception");
			e.printStackTrace();
			return null;
		}
	}
}
