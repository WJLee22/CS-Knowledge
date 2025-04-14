package kr.ac.hansung.cse;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.dao.InstructorDao;
import kr.ac.hansung.cse.entity.Course;
import kr.ac.hansung.cse.entity.Instructor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// One-to-Many Bidirectional
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        InstructorDao instructorDao = context.getBean(InstructorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);

        Instructor instructor1 = new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
        Course course1 = new Course("웹프레임워크");
        Course course2 = new Course("오픈소스소프트웨어");

        instructor1.addCourse(course1);
        instructor1.addCourse(course2);

        // cascade=CascadeType.ALL, fetch = FetchType.LAZY
        // 강사 엔티티를 DB에 저장할 때, 강사 엔티티와 연관된 Course 엔티티도 함께 저장됨.
        instructorDao.save(instructor1);

        // 저장된 Instructor 조회 및 결과 확인.
        // 자, 근데 아까 cascade=CascadeType.ALL 옵션을 줬었지? 그렇다면 이 findById로 DB에서 select했을 때 연관된 Course 엔티티들도 함께 조회가 될까? 안될까?
        // -> 안된다. 왜냐면 fetch = FetchType.LAZY 옵션을 줬기에 같이 조회되는것이 아니라 추후 직접 요청시에 조회됨.
        // 암튼 courses를 찾지못한채 이 트랜잭션이 끝나기때문에 일단 엔티티메니저는 닫힌 상태.
        //Instructor retrievedInstructor = instructorDao.findById(instructor1.getId());
        
        // findByIdWithCourses 메서드로 instructor1을 조회하면 courses 컬렉션이 cascade 로드됨.
        // 하나의 findByIdWithCourses 트랜잭션 안에서 find로 엔티티를 조회한 후, 그 엔티티의 getCourses()를 호출하여 courses 컬렉션을 실제참조하여 lazy fetch가 이루어져 courses가 성공적으로 로드되어짐.
        Instructor retrievedInstructor = instructorDao.findByIdWithCourses(instructor1.getId());
        System.out.println("Instructor: " + retrievedInstructor.getFullName());
        // 엔티티메니저가 닫힌 상태 + courses 리스트는 null인 상태에서 courses를 조회하면 courses 정보를 찾을수없어서 LazyInitializationException 발생.
        for (Course Course : retrievedInstructor.getCourses()) {
            System.out.println("Course: " + Course.getTitle());
        }
    }
}
