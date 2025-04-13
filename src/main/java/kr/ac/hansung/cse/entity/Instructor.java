package kr.ac.hansung.cse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    // 이 엔티티를 DB에 저장할 때, id는 자동으로 증가하는 Auto Increment값으로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="email")
    private String email;

    public Instructor(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
