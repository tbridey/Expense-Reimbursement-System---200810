package project01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class NewTicket
 */
public class NewTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTicket() {
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
		// TODO Auto-generated method stub
		System.out.println("entered NewTicket()");
		String jsonString = request.getReader().readLine();
		System.out.println(jsonString);
		ObjectMapper om = new ObjectMapper();
		Ticket t = om.readValue(jsonString, Ticket.class);
		
		UserOracleDAO dao = new UserOracleDAO();
		boolean newTicket = dao.insertTicket(t);
		
		PrintWriter out=response.getWriter();
		String jsonStr = om.writeValueAsString(newTicket);
		out.append(jsonStr);
	}

}
