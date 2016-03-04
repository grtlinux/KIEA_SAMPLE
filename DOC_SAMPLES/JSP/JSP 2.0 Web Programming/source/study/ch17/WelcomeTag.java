package ch17;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class WelcomeTag extends TagSupport {
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("Welcome to My Custom Tag");
        } catch (Exception ex) {
            throw new JspTagException("WelcomeTag: " + ex.getMessage());
        }
        return SKIP_BODY; 
    }
}

