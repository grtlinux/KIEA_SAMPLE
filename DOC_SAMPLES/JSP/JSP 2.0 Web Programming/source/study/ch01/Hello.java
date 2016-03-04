import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Hello extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		 throws ServletException, IOException{
		 	res.setContentType("text/html;charset=euc-kr");
		 	PrintWriter out = res.getWriter();
			String name=HangulConversion.toKor(req.getParameter("name"));
			out.println("<HTML>");
		 	out.println("<HEAD><TITLE>form&servlet</Title></Head>");
		 	out.println("<BODY>");
		 	out.println(name+" 님의 방문을 환영합니다.");
		 	out.println("</BODY>");
		 	out.println("<HTML>");
		 	out.close();
		 }
}