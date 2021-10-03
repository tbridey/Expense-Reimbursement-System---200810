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
 * Servlet implementation class ViewAllTickets
 */
public class ViewAllTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllTickets() {
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
		//String jsonString = request.getReader().readLine();
		HttpSession session = request.getSession(false);
		ObjectMapper om = new ObjectMapper();
		//System.out.println(stat.getStatus());
		
		if(session != null) {
			UserOracleDAO dao = new UserOracleDAO();
			
			HashMap<String, TicketItems> tickets = dao.viewAll();
			String jsonStr = om.writeValueAsString(tickets);
			//System.out.println(jsonStr);
			PrintWriter out = response.getWriter();
			out.append(jsonStr);
			
		}else {
			System.out.println("Session returned empty");
		}
	}

}
