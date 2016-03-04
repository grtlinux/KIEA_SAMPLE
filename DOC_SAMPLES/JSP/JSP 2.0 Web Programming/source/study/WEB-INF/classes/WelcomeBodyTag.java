package ch17;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class WelcomeBodyTag extends TagSupport {
    
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("환영합니다 <b>");
        } catch (Exception ex) {
            throw new JspTagException("WelcomeBodyTag: " + ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
}