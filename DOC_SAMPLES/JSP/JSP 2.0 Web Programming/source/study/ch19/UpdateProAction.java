package ch19.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch12.board.BoardDataBean;
import ch12.board.BoardDBBean;

public class UpdateProAction implements CommandAction {

    public String requestPro( HttpServletRequest request,
        HttpServletResponse response) throws Throwable {

        request.setCharacterEncoding("euc-kr");

        String pageNum = request.getParameter("pageNum");

		BoardDataBean article = new BoardDataBean();
        article.setNum(Integer.parseInt(request.getParameter("num")));
        article.setWriter(request.getParameter("writer"));
        article.setEmail(request.getParameter("email"));
        article.setSubject(request.getParameter("subject"));
        article.setContent(request.getParameter("content"));
        article.setPasswd(request.getParameter("passwd"));
	    
		BoardDBBean dbPro = BoardDBBean.getInstance();
        int check = dbPro.updateArticle(article);

        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("check", new Integer(check));

        return "/ch19/updatePro.jsp";
    }
}
