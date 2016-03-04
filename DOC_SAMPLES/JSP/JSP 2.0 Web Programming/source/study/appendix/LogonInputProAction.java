package appendix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import ch11.logon.LogonDataBean;
import ch11.logon.LogonDBBean;
import ch19.action.CommandAction;

public class LogonInputProAction implements CommandAction {//회원가입 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)  throws Throwable {

        request.setCharacterEncoding("euc-kr");//한글 인코딩
        
        LogonDataBean member = new LogonDataBean();//데이터처리 빈
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
        member.setJumin1(request.getParameter("jumin1"));
        member.setJumin2(request.getParameter("jumin2"));
		member.setEmail(request.getParameter("email"));
		member.setBlog(request.getParameter("blog"));
        member.setReg_date(new Timestamp(System.currentTimeMillis()));

        LogonDBBean dbPro = LogonDBBean.getInstance();//DB처리
        dbPro.insertMember(member);

        return "/appendix/logon/inputPro.jsp";//해당 뷰
    }
}
