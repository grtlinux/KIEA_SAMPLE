package ch17;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class SimpleWelcomeTag extends SimpleTagSupport {
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().print("SimpleTag: Welcome to My Custom Tag");
        } catch (Exception ex) {
            throw new JspTagException("SimpleTag: " + ex.getMessage());
        }
    }
}