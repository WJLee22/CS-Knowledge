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
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    // Unidircetion(단방향) - ManyToOne 관계 설정. 다수의 Course 엔티티 객체들이 하나의 Instructor 엔티티 객체를 가리키는 구조.
    // 이 여러 Course 엔티티는 하나의 Instructor 엔티티와 관계를 가짐.
    // @JoinColumn 어노테이션을 사용하여 instructor 엔티티의 id 속성을 외래키로 설정
    // 자 ,그렇다면 Course 엔티티(레코드)에서 instructor_id 속성값은 어떻게 Instructor 엔티티의 기본키인 id 속성값으로 설정되느냐?
    // 해당 기본키값을 개발자가 직접넣어주는게 아니라 instructor 객체 자체를 setInstructor()에 전달하면 JPA가 내부적으로 해당 객체의 기본키(id)를 찾아 → 외래키(instructor_id) 컬럼에 자동 할당한다.
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    public Course(String title) {
        this.title = title;
    }
}
