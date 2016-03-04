import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		 throws ServletException, IOException{
		 	res.setContentType("text/html;charset=euc-kr");
		 	try {
		 		PrintWriter out = res.getWriter();
		 		out.println("<HTML>");
		 		out.println("<HEAD><TITLE>Hello Servlet</Title></Head>");
		 		out.println("<BODY>");
		 		out.println("гогого...");
		 		out.println("</BODY>");
		 		out.println("<HTML>");
		 		out.close();
		 	}catch(Exception e){
		 		getServletContext().log("Error in HelloServlet:",e);
		 	}
		 }
}