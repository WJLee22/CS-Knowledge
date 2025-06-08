package kr.ac.hansung.cse.hellospringbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class HelloSpringBootSecurityApplicationTests {


	@Autowired
	private PasswordEncoder encoder;

	@Test
	void contextLoads() {
	}

	@Test
	void generateHashedPassword() {
		String pwd = encoder.encode("alicepw"); // plain text password -> salted hashed password
		System.out.println(pwd); // $2a$10$Wkk5YpkujSXTYcSJ0UGm8OMJK0ZR61J6Y3L/tJoeM92fWdPw61qSO
	}
}