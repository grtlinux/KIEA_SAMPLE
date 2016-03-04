package appendix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch19.action.CommandAction;

public class LogoutAction implements CommandAction {//·Î±×¾Æ¿ô Ã³¸®

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {

        return "/appendix/logon/logout.jsp";//ÇØ´ç ºä
    }
}
