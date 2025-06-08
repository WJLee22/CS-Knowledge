package kr.ac.hansung.cse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "offers") // offers라는 이름의 DB 테이블로 매핑
public class Offer {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 자동 증가
    private int id;

    // 이름 제약조건: 최소 2자, 최대 100자, 만약 이 조건을 만족하지 않으면 메세지 출력
    @Size(min=2, max=100, message = "Name must be between 2 and 100 chars")
    private String name;

    // 이메일 제약조건: 이메일 형식으로 입력해야함&비어있으면 안됨. 만약 이 조건을 만족하지 않으면 메세지 출력
    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "The email address cannot be empty")
    private String email;

    @Size(min=5, max=100, message = "Text must be between 5 and 100 chars")
    private String text;
}
