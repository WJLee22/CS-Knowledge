package kr.ac.hansung.cse.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("message", "Hello, Spring Boot!");
        // 이 메서드는 / 요청이 들어오면 index.html 파일을 반환한다.
        // index.html 파일은 src/main/resources/templates 폴더에 있어야 한다. 타임리프는 템플릿 엔진이니깐.
        // 스프링부트는 기본적으로 thymeleaf 템플릿 엔진을 사용하여 서버사이드 렌더링으로 html 파일을 렌더링 후 클라이언트에게 전달한다.
        return "index"; // index.html 파일을 반환한다.
    }
}
