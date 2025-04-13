package kr.ac.hansung.cse;


import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.dao.InstructorDao;
import kr.ac.hansung.cse.entity.Course;
import kr.ac.hansung.cse.entity.Instructor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// One-to-Many Uni-directional
public class Main {
    public static void main(String[] args) {
        // 설정파일을 바탕으로 Spring IoC 컨테이너를 생성
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        // 스프링 컨테이너에 생성-저장되어있는 InstructorDao Bean을 가져옴
        InstructorDao instructorDao = context.getBean(InstructorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);

        // [1] Instructor 객체 생성 -> 이때의 Instructor 엔티티의 라이프사이클은 Transient 상태
        Instructor instructor1 = new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
        Instructor instructor2 = new Instructor("Jaemon Lee", "jmlee@hansung.ac.kr");

        // [2] Instructor 먼저 저장 (DB에 insert + id 생성) -> 이때의 Instructor 엔티티의 라이프사이클은 Persistent/Managed 상태
        // 이때 Instructor 엔티티를 먼저 저장하는 이유가 무엇이냐?
        // -> Course 엔티티는 Instructor 엔티티를 외래키로 참조-가리키고 있기 때문에 Course 엔티티를 저장하기 전에 Instructor 엔티티를 먼저 저장해주어야 참조할 대상인 이 Instructor의 기본키가 존재하기되니, 순서가 맞지.
        // 만약 Course 엔티티를 먼저 저장하고 Instructor 엔티티를 저장하면 Course 엔티티의 외래키인 instructor_id 속성값을 가리킬 대상이없으니깐 null로 설정되어버린다.
        instructorDao.save(instructor1);
        instructorDao.save(instructor2);

        // [3] Course 객체 생성
        Course course1 = new Course("웹프레임워크");
        Course course2 = new Course("오픈소스소프트웨어");
        Course course3 = new Course("iOS 프로그래밍");
        Course course4 = new Course("안드로이드 프로그래밍");

        // [4] 각 Course에 Instructor 설정 (연관관계 주입) -> 이때 외래키인 instructor_id 속성값이  해당 Instructor 엔티티의 기본키(id)값으로 assign.설정됨.
        // 즉, 여러개의 course 엔티티들이 하나의 instructor 엔티티를 외래키를 통해 가리키는 구조인 단방향 ManyToOne 관계를 설정.
        course1.setInstructor(instructor1);
        course2.setInstructor(instructor1);
        course3.setInstructor(instructor2);
        course4.setInstructor(instructor2);

        // [5] Course 저장 (instructor_id 포함된 상태로 INSERT)
        courseDao.save(course1);
        courseDao.save(course2);
        courseDao.save(course3);
        courseDao.save(course4);

        // 저장된 데이터 조회
        List<Instructor> instructors = instructorDao.findAll();
        List<Course> courses = courseDao.findAll();

        System.out.println("Instructors:");
        for (Instructor Instructor : instructors) {
            System.out.println(Instructor.getFullName());
        }

        System.out.println("\nCourses:");
        for (Course course : courses) {
            System.out.println("Instructor: " + course.getInstructor().getFullName() +
                    ", Course: " + course.getTitle());
        }
    }
}
