import java.io.IOException; //it is use for handaling IO error
import java.io.PrintWriter; //it is use to send to browser
import java.sql.Connection; //connect to database
import java.sql.DriverManager;//it load jdbc driver in the memory
import java.sql.PreparedStatement; // to prevending SQL injection
import java.sql.ResultSet; //store data return  from selected query
import jakarta.servlet.http.HttpServlet;// is an base class for creating http servlet
import jakarta.servlet.http.HttpServletRequest;// get the data from the client
import jakarta.servlet.http.HttpServletResponse; //send the data to client

public class BookServlet extends HttpServlet {

    // Handles GET request
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)// handal the get method from the browser/get request
            throws IOException {

        // Set response type
        res.setContentType("text/html"); // its tells the browser that the respons is html
        PrintWriter out = res.getWriter(); // its creat an output string  to html to browser

        // Get search keyword from HTML form
        String key = req.getParameter("key");//read the search keyword send from the html-from input name key

        try {
      Class.forName("org.postgresql.Driver");

Connection con = DriverManager.getConnection(
    "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require&connectTimeout=10",
    "postgres.uysvmbjwgnbtiiffefsi",
    "DGHVSHVBCXGS"
);
 String sql = "SELECT * FROM public.books WHERE title ILIKE ? OR author ILIKE ?"; // select all the record from the book tablel; ILIKE is used in case insencsitive search

           PreparedStatement ps = con.prepareStatement(sql); // its used for parsial searching
                 ps.setString(1, "%" + key + "%");
          ps.setString(2, "%" + key + "%");

            // Execute query
            ResultSet rs = ps.executeQuery();

            // Display result in HTML table
            out.println("<h2>Book List</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Title</th><th>Author</th><th>Price</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("author") + "</td>");
                out.println("<td>" + rs.getInt("price") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            // Close connection
            con.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e + "</p>");
        }
    }
}
