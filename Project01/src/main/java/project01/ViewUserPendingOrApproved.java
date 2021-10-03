package project01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Servlet implementation class ViewUserPending
 */
public class ViewUserPendingOrApproved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserPendingOrApproved() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String jsonString = request.getReader().readLine();
		
		ObjectMapper om = new ObjectMapper();
		TicketStatus stat = om.readValue(jsonString, TicketStatus.class);
		
		//System.out.println(stat.getStatus());
		
		if(session != null) {
			int userID = (int) session.getAttribute("userID");
			UserOracleDAO dao = new UserOracleDAO();
			if(stat.getStatus().equals("pending")) {
				HashMap<String, TicketItems> tickets = dao.userPendingOrApproved(userID, 0);
				String jsonStr = om.writeValueAsString(tickets);
				//System.out.println(jsonStr);
				PrintWriter out = response.getWriter();
				out.append(jsonStr);
			}
			if(stat.getStatus().equals("approved")) {
				HashMap<String, TicketItems> tickets = dao.userPendingOrApproved(userID, 1);
				String jsonStr = om.writeValueAsString(tickets);
				//System.out.println(jsonStr);
				PrintWriter out = response.getWriter();
				out.append(jsonStr);
			}
			
		}else {
			System.out.println("Session returned empty");
		}
	}

}
