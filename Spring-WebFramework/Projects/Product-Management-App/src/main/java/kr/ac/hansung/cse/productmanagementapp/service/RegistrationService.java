package kr.ac.hansung.cse.productmanagementapp.service;

import kr.ac.hansung.cse.productmanagementapp.entity.MyRole;
import kr.ac.hansung.cse.productmanagementapp.entity.MyUser;

import java.util.List;

public interface RegistrationService {
    // createUser: 사용자, 사용자 정보를 바탕으로 DB에 저장.
    MyUser createUser(MyUser user, List<MyRole> userRoles);

    // 기존에 이메일이 있는지를 체크. 즉 회원가입이 되어있는지를 체크. 우리는 지금 이메일을 username으로 쓰는중.
    boolean checkEmailExists(String email);
    // 메서드 이름 기반 자동 쿼리 생성. rolename을 통해서 MyRole 객체를 찾는 메서드.
    MyRole findByRolename(String rolename);
}