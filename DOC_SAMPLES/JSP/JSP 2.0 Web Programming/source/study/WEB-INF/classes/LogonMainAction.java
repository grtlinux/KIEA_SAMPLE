package appendix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch19.action.CommandAction;

public class LogonMainAction implements CommandAction {//회원관리메인 페이지

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {

        return "/appendix/logon/main.jsp";//해당 뷰
    }
}
