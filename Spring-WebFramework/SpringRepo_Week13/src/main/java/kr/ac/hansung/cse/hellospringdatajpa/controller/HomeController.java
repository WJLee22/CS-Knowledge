package kr.ac.hansung.cse.hellospringdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // http://localhost:8080/helloSpringDataJpa 로 요청올 경우에도 -> helloSpringDataJpa/products로 리다이렉트 시켜주도록 함.
    @GetMapping("/")
    public String redirectToProducts() {
        return "redirect:/products";
    }
}
