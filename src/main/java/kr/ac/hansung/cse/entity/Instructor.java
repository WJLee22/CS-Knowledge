package kr.ac.hansung.cse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
private List<Course> courses = new ArrayList<>();

    // OneToOne 관계 설정.
    // InstructorDetail 엔티티와 Instructor 엔티티는 1:1 관계를 가짐.
    // 지금 이 Instructor 엔티티는 InstructorDetail 엔티티를 외래키로 참조-가리키고 있음.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
    // 연관 관계 편의 메소드
    public void addCourse(Course course) {
        courses.add(course); // 리스트에 Course 객체 추가: Instructor -> Course (DB에서는 이 관계가 안 보임. 그저 엔티티 상에서 논리적으로만 존재)
        course.setInstructor(this); // Course 객체에 현재 이 Instructor 엔티티를 필드값으로 설정(@JoinColumn에 의해 외래키로 추가됨): Course(외래키로 참조) -> Instructor
    }
}