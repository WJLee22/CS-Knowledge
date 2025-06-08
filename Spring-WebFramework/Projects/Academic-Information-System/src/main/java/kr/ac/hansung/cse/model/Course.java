package kr.ac.hansung.cse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "courses") // courses라는 이름의 DB 테이블로 매핑
public class Course {
    @NotNull(message = "- 수강년도 입력은 필수입니다")
    @Min(value = 2025, message = "- 수강년도는 2025년이어야 합니다")
    @Max(value = 2025, message = "- 수강년도는 2025년이어야 합니다")
    private Integer year; // 수강년도

    @NotNull(message = "- 학기 입력은 필수입니다")
    @Min(value = 1, message = "- 학기는 1 또는 2만 가능합니다")
    @Max(value = 2, message = "- 학기는 1 또는 2만 가능합니다")
    private Integer semester; // 학기

    @Id
    @NotEmpty(message = "- 교과코드 입력은 필수입니다")
    @Pattern(regexp = "^[A-Z][A-Z0-9]{6}$", message = "- 교과코드는 총 7자리이며, 영문 대문자로 시작하고 나머지는 영문 대문자 또는 숫자로 구성되어야 합니다")
    private String courseCode; // 교과코드가 기본키

    @NotEmpty(message = "- 교과목명 입력은 필수입니다")
    private String courseName; // 교과목명

    // select태그
    @NotEmpty(message = "- 교과구분 선택은 필수입니다")
    private String courseType; // 교과구분

    @NotEmpty(message = "- 담당교수 입력은 필수입니다")
    private String professor; // 담당교수

    @NotNull(message = "- 학점 입력은 필수입니다")
    @Min(value = 1, message = "- 학점은 최소 1학점 이상이어야 합니다")
    @Max(value = 3, message = "- 학점은 최대 3학점 이하이어야 합니다")
    private Integer credits; // 학점

}
