package appendix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch19.action.CommandAction;

public class LoginFormAction implements CommandAction {//회원인증 폼 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
     
        return "/appendix/logon/loginForm.jsp";//해당 뷰
    }
}
