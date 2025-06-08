package kr.ac.hansung.cse.productmanagementapp.controller;

import kr.ac.hansung.cse.productmanagementapp.entity.MyRole;
import kr.ac.hansung.cse.productmanagementapp.entity.MyUser;
import kr.ac.hansung.cse.productmanagementapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/*
회원가입 컨트롤러. 즉 회원가입에 대한 요청을 처리하는 클래스.
회원가입 폼 표시(GET 요청)와 제출(POST 요청)을 처리.
회원가입 시 이메일 중복 체크를 수행하여 이미 등록된 이메일인 경우 오류 메시지를 표시.
모든 새 사용자에게 기본적으로 "ROLE_USER" 권한을 부여.
특별한 이메일 주소(admin@hansung.ac.kr)로 가입하는 경우 "ROLE_ADMIN" 권한도 추가로 부여.
*/
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // 비어있는 MyUser 객체를 생성하고, 모델에 추가하여 회원가입 폼을 보여줌
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        MyUser user = new MyUser();
        model.addAttribute("user", user);

        return "signup";
    }

    // 회원가입 폼에서 입력한 정보를 바탕으로 회원가입 처리
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") MyUser user, Model model) {

        // 이메일 중복 체크. 기존에 등록된 이메일이 있는지 확인
        // 즉, 이미 가입된 이메일이 있는 경우 회원가입을 허가하지 않음.
        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            List<MyRole> userRoles = new ArrayList<>();

            // 기본적으로 ROLE_USER 권한 부여
            MyRole role = registrationService.findByRolename("ROLE_USER");
            userRoles.add(role);

            // 특정 이메일 주소인 경우 ADMIN 권한 추가
            if ("admin@hansung.ac.kr".equals(user.getEmail())) {
                MyRole roleAdmin = registrationService.findByRolename("ROLE_ADMIN");
                userRoles.add(roleAdmin);
            }
            // DB에 사용자 정보 저장
            registrationService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}
