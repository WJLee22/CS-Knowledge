package kr.ac.hansung.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    // 로거 객체를 생성. 로거는 "kr.ac.hansung.controller.HomeController" 이름으로 설정.
    private static final Logger logger = LoggerFactory.getLogger("kr.ac.hansung.controller.HomeController");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    // 루트 URL ("/")로 들어오는 GET 요청을 처리하는 메서드.
    // get메서드 로 요청이 들어오면 home() 메서드가 호출됨.

    public String home(HttpServletRequest request) {
        // 요청 URL을 추출
        String url = request.getRequestURL().toString();
        // 클라이언트의 IP 주소를 추출
        String clientIPaddr = request.getRemoteAddr();

        // 추출한 URL과 IP 주소를 로깅
        logger.info("Request URL: {}, Client IP: {}", url, clientIPaddr);

        // home.jsp라는 뷰를 클라이언트에게 응답으로 반환
        // 즉, "/" 루트 URL로 들어온 요청에 대해 -> home.jsp를 서블릿처리를 거쳐 뷰(html)로 반환해주는 역할을 하는 메서드
        return "home";
    }

}

