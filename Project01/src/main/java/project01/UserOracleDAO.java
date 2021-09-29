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
			
			User userObj=new User(uName,pass,fName,lName,email,uID,rID);
			
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
	public HashMap<String, HashMap<String, String>> viewUserPending(int userID) {
		final String query = 
				"SELECT er.reimb_id, er.reimb_submitted, ert.REIMB_TYPE, er.reimb_amount, er.reimb_description "
				+ "FROM ERS_REIMBURSEMENT er "
				+ "JOIN ERS_REIMBURSEMENT_TYPE ert "
				+ "	ON er.REIMB_TYPE_ID = ert.REIMB_TYPE_ID "
				+"WHERE er.REIMB_AUTHOR = ?";
		try {
			
			HashMap<String, HashMap<String, String>> tickets= new HashMap<String, HashMap<String,String>>();
			
			HashMap<String,String> ticketItem = new HashMap<String, String>();
			
			Connection conn = DatabaseConnector.connector();
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, userID);
			
			ResultSet rs = prepStmt.executeQuery();
										 
			int i=1;
			while(rs.next()) {
				
				ticketItem.put("ticketID", Integer.toString(rs.getInt(1)));
				ticketItem.put("submitted", rs.getDate(2).toString());
				ticketItem.put("type", rs.getString(3));
				ticketItem.put("amount",Double.toString(rs.getDouble(4)));
				ticketItem.put("description", rs.getString(5));
				tickets.put("ticket"+i, ticketItem);
				i++;
			}
			return tickets;
			
		}catch(SQLException e){
			System.out.println("ERROR: view user pending SQL exception");
			e.printStackTrace();
			return null;
		}
	}

}
