

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class MCLsearch
 */
public class MCLsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MCLsearch() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void connect () throws Exception {
    	//Load MySQL-specific drivers
    	Class.forName("org.gjt.mm.mysql.Driver");
    	//Put in information to locate database
    	String url = "jdbc:mysql://rdc04.uits.iu.edu:3264/S517DB";
    	String username = "S517";
        String password = "S517";    
        connection = DriverManager.getConnection (url, username, password);
        statement = connection.createStatement();
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head>");
		//Start to print HTML page
		//Title of page and instructions for user.
		out.println("<title>SOMETHING</title></head><body>");
		//Create a HTML form with post method
		out.println("<form method=\"post\" action =\""
					+ request.getContextPath() + "/MCLsearch\" >");
		//Create button to submit and show results
		out.println("<table border=\"0\"><tr><td valign=\"top\">");
		out.println("<input type=\"submit\" value=\"Show results\"></td></tr>");
		out.println("</table></form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Results page
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		//Start to print webpage
		out.println("<html><head>");
		//Results page title and description
		out.println("<title>SOMETHING</title></head><body>");
		try {
			System.out.println("Test1");
			connect();
			//Select all the records from user table > frequency input by user
			ResultSet rs = statement.executeQuery("select title, article_publication_date, doi_number from paper");
			System.out.println("Test2");
			while (rs.next()) {  //Read each record
				String title = rs.getString("title");
				String article_publication_date = rs.getString("article_publication_date");
				String doi_number = rs.getString("doi_number");
				out.println("Title: " + title + "<br>Publication Date: " + article_publication_date + "<br>DOI Number: " + doi_number + "<br><br>");
			}
			rs.close();
			statement.close();
			connection.close();
			out.println("</body></html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
