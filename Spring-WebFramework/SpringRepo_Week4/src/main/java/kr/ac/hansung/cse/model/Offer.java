package kr.ac.hansung.cse.model;

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
public class Offer {
    // 이 데이터 바인딩되는 대상인, 모델 attribute 클래스의 각 필드들에 @Size, @NotNull와 같은 constraint(제약) 제약조건 어노테이션을 붙여주면,
    // 그 제약조건에 따라서 스프링이 검증을 해주고 -> 그 다음에 BindingResult 객체에 겅증결과가 담긴다.
    // Constraint(제약조건)어노테이션을 사용하여 필드에 대한 제약조건을 설정할 수 있다.
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
