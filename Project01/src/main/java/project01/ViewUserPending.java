package project01;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ViewUserPending
 */
public class ViewUserPending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserPending() {
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
		ObjectMapper om = new ObjectMapper();
		if(session != null) {
			int userID = (int) session.getAttribute("userID");
			UserOracleDAO dao = new UserOracleDAO();
			HashMap<String, TicketItems> tickets = dao.viewUserPending(userID);
			String jsonStr = om.writeValueAsString(tickets);
			//System.out.println(jsonStr);
			response.getWriter().append(jsonStr);
			
			
		}else {
			System.out.println("Session returned empty");
		}
	}

}
