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

/**
 * Servlet implementation class ApproveOrDenyTicket
 */
public class ApproveOrDenyTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveOrDenyTicket() {
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
		TicketStatus ticket = om.readValue(jsonString, TicketStatus.class);
		
		System.out.println(ticket.getStatus()+" "+ticket.getTicketID());
		
		if(session != null) {
			UserOracleDAO dao = new UserOracleDAO();
			if(ticket.getStatus().equals("approved")) {
				boolean success = dao.updateTicketStatus(ticket.getTicketID(), ticket.getStatus());
				String jsonStr = om.writeValueAsString(success);
				//System.out.println(jsonStr);
				PrintWriter out = response.getWriter();
				out.append(jsonStr);
			}
			if(ticket.getStatus().equals("denied")) {
				boolean success = dao.updateTicketStatus(ticket.getTicketID(), ticket.getStatus());
				String jsonStr = om.writeValueAsString(success);
				//System.out.println(jsonStr);
				PrintWriter out = response.getWriter();
				out.append(jsonStr);
			}
			
		}else {
			System.out.println("Session returned empty");
		}
	}

}
