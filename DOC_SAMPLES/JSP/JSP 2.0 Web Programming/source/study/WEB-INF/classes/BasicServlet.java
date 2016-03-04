import javax.servlet.*;
import java.io.*;
 
public class BasicServlet extends GenericServlet  
{
	public  void service(ServletRequest req, ServletResponse res) 
		throws ServletException, IOException{
		
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();

		out.println("Generic Servlet example");
	}
}
