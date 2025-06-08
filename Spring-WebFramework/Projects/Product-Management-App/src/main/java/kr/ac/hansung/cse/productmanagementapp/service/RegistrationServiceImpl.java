package kr.ac.hansung.cse.productmanagementapp.service;

import kr.ac.hansung.cse.productmanagementapp.entity.MyRole;
import kr.ac.hansung.cse.productmanagementapp.entity.MyUser;
import kr.ac.hansung.cse.productmanagementapp.repository.RoleRepository;
import kr.ac.hansung.cse.productmanagementapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 회원가입 관련 비즈니스 로직을 처리하는 서비스 클래스.
//이메일 중복 체크 기능을 제공.
//역할 이름으로 역할 정보를 조회하는 기능을 제공.
//사용자 생성 시 비밀번호를 BCrypt로 암호화한 후 저장.
//사용자에게 역할을 할당하여 권한 관리를 수행.

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser createUser(MyUser user, List<MyRole> userRoles) {
        for (MyRole ur : userRoles) {
            if (roleRepository.findByRolename(ur.getRolename()).isEmpty()) {
                roleRepository.save(ur);
            }
        }

        // generate new Bcrypt hash
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setRoles(userRoles);

        MyUser newUser = userRepository.save(user);

        return newUser;
    }

    public boolean checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return true;
        }

        return false;
    }

    public MyRole findByRolename(String rolename) {
        Optional<MyRole> role = roleRepository.findByRolename(rolename);
        return role.orElseGet(() -> new MyRole(rolename));
    }

}