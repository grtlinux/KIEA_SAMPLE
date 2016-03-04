package appendix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch11.logon.LogonDataBean;
import ch11.logon.LogonDBBean;
import ch19.action.CommandAction;

public class LogonModifyFormAction implements CommandAction {//회원정보 수정 폼 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response) throws Throwable {

        String id = request.getParameter("id");

        LogonDBBean dbPro = LogonDBBean.getInstance();
        LogonDataBean member =  dbPro.getMember(id);

		//해당 뷰에서 사용할 속성
        request.setAttribute("member", member);

        return "/appendix/logon/modifyForm.jsp";//해당뷰
    }
}
