package madvirus.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 헤더의 &lt;이름, 값>을 출력하는 커스텀 태
 * @author 최범균
 */
public class HeaderListTag extends TagSupport {
    
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = 
            (HttpServletRequest)pageContext.getRequest();
        try {
            out.println("<ul>");
            Enumeration e = request.getHeaderNames();
            while(e.hasMoreElements()) {
                String headerName = (String)e.nextElement();
                out.print("<li>");
                out.print(headerName);
                out.print("=");
                out.println(request.getHeader(headerName));
            }
            out.print("</ul>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
