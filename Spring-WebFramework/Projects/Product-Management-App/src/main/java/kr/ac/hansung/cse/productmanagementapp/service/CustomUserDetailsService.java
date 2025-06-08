package kr.ac.hansung.cse.productmanagementapp.service;

import kr.ac.hansung.cse.productmanagementapp.entity.MyUser;
import kr.ac.hansung.cse.productmanagementapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


/*
serDetailsService 인터페이스를 구현하여 Spring Security에 사용자 인증 로직을 제공.
loadUserByUsername 메소드는 이메일을 기반으로 사용자를 조회.
데이터베이스에서 조회한 MyUser 객체를 Spring Security의 UserDetails 객체로 변환.
사용자의 역할(Role)을 Spring Security의 GrantedAuthority로 변환하여 권한 체크에 사용합니다.
*/

// 인증 관리자는 UserDetailsService 를 통해 UserDetails 객체를 획득하고
// 이 UserDetails 객체에서 인증 및 인가에 필요한 정보를 추출하여 사용한다.
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + userName + " not found"));
        return new org.springframework.security.core.userdetails.User(myUser.getEmail(),
                myUser.getPassword(), getAuthorities(myUser));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(MyUser user)
    {
        String[] userRoles = user.getRoles()
                .stream()
                .map((role) -> role.getRolename())
                .toArray(String[]::new);

        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
