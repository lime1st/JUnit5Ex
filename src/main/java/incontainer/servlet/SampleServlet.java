package incontainer.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SampleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //  HttpServletRequest 는 인터페이스이므로 new 예약어를 사용해서 생성할 수 없다.
    //  또한, HttpServletRequest 객체의 생애 주기나 구혆은 컨테이너(이 경우 서블릿 컨테이너)에서 제공하는 것이지
    //  개발자가 하는 것이 아니다. HttpSession 도 마찬가지다.
    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String authenticationAttribute = (String) session.getAttribute("authenticated");

        return Boolean.valueOf(authenticationAttribute).booleanValue();
    }
}
