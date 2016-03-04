package ch17;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class SimpleBodyTag extends SimpleTagSupport {
    
    public void doTag() throws JspException {
        try {
			JspFragment fragment = getJspBody();
			fragment.invoke(null);
        } catch (Exception ex) {
            throw new JspTagException("WelcomeBodyTag: " + ex.getMessage());
        }
    }
}