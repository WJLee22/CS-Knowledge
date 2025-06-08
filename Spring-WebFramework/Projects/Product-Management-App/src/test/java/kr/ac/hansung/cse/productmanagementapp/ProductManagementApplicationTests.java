package kr.ac.hansung.cse.productmanagementapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ProductManagementApplicationTests {


	@Autowired
	private PasswordEncoder encoder;

	@Test
	void contextLoads() {
	}

	@Test
	void generateHashedPassword() {
		String pwd = encoder.encode("alicepw"); // plain text password -> salted hashed password
		System.out.println(pwd);
	}
}