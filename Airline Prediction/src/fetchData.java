

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codes.Database;

/**
 * Servlet implementation class fetchData
 */
@WebServlet("/fetchData")
public class fetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection con = Database.getDBConnection();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchData() {
    	
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
		doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			String sql="Select * from flightdetails order by ticket_price";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			out.println("<table class='table table-hover'>");
			out.println("<thead>");
			out.println("<td class='text-center'> Departure </td>");
			out.println("<td class='text-center'> Arrival </td>");
			out.println("<td class='text-center'> Stops </td>");
			out.println("<td class='text-center'> Flight Duration </td>");
			out.println("<td class='text-center'> Departure Time </td>");
			out.println("<td class='text-center'> Arrival Time </td>");
			out.println("<td class='text-center'> Airline </td>");
			out.println("<td class='text-center'> Plane </td>");
			out.println("<td class='text-center'> Ticket Price </td>");
			out.println("</thead>");
			out.println("<tbody>");
			while(rs.next()){
				out.println("<tr>");
				out.println("<td class='text-center'>"+rs.getString("departure")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("arrival")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("stops")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("flight_duration")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("departure_time")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("arrival_time")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("airline")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("plane")+"</td>");
				out.println("<td class='text-center'>"+rs.getString("ticket_price")+"</td>");
				out.println("</tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
