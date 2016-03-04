package ch18.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageController extends HttpServlet {

    public void doGet(HttpServletRequest request, //1단계 : 사용자의 요청을 받는 서비스 메소드
                         HttpServletResponse response)
    throws ServletException, IOException {
        requestPro(request, response);
    }

    public void doPost(HttpServletRequest request, //1단계 : 사용자의 요청을 받는 서비스 메소드
                          HttpServletResponse response)
    throws ServletException, IOException {
        requestPro(request, response);
    }
    
    private void requestPro(HttpServletRequest request, 
                                HttpServletResponse response)
    throws ServletException ,IOException{
        String message = request.getParameter("message");//2단계 : 사용자의 요청분석
        
        Object result = null;
        if (message == null || message.equals("base")) {//3단계 : 사용자의 요청에 따른 작업처리
            result = "하하하.";
        } else if (message.equals("name")) {
            result = "홍길동 입니다.";
        } else {
            result = "타입이 맞지 않습니다.";
        }
        
        request.setAttribute("result", result);//4단계 : request의 속성에 처리결과 저장
        
        // 5단계 : RequestDispatcher를 사용하여 해당 뷰로 포워딩
        RequestDispatcher dispatcher = 
			           request.getRequestDispatcher("/ch18/messageView.jsp");
        dispatcher.forward(request, response);
    }
}
