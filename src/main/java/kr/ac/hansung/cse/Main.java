package kr.ac.hansung.cse;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.dao.InstructorDao;
import kr.ac.hansung.cse.entity.Instructor;
import kr.ac.hansung.cse.entity.InstructorDetail;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// One-To-One Unidirectional
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        InstructorDao instructorDao = context.getBean(InstructorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);

        // [1] InstructorDetail 객체 생성
        InstructorDetail detail =
                new InstructorDetail("youtube.com/TheJavaChannel", "Coding");

        // [2] Instructor 객체 생성 및 연관관계 설정
        Instructor instructor =
                new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
        instructor.setInstructorDetail(detail);  // 연관관계 설정. 강사 -> 강사상세정보

        // [3] Instructor 저장 (CascadeType.ALL 덕분에 InstructorDetail도 함께 저장됨)
        instructorDao.save(instructor);

        // [4] 저장된 Instructor 조회
        // 이때, instructor 엔티티에서 fetch타입이 디폴트값인 EAGER이므로 findById하면 바로 연관된 instructorDetail도 함께 조회됨.
        Instructor storedInstructor = instructorDao.findById(instructor.getId());
        System.out.println("Retrieved Instructor: " + storedInstructor.getFullName());

        // [5] InstructorDetail 출력 (단방향 관계에서 바로 접근 가능)
        // select 조회한 Instructor엔티티로부터 연관된 InstructorDetail 엔티티를 get.
        InstructorDetail storedDetail = storedInstructor.getInstructorDetail();
        System.out.println("Instructor Detail:");
        System.out.println("YouTube Channel: " + storedDetail.getYoutubeChannel());
        System.out.println("Hobby: " + storedDetail.getHobby());

    }
}

// One-to-Many Uni-directional
/*
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        // 스프링 컨테이너에 생성-저장되어있는 InstructorDao Bean을 가져옴
        InstructorDao instructorDao = context.getBean(InstructorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);

        // [1] Instructor 객체 생성
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
            System.out.println( "Instructor: " + course.getInstructor().getFullName() +
                    ", Course: " + course.getTitle());
        }
    }
}

/*
// Bidirectional: 데이터베이스 상에서는 단방향으로 저장되지만, 이 객체지향 프로그래밍(ORM)에서는 논리적으로 양방향으로 참조할 수 있는 구조이다.
// One-to-Many Bidirectional: 어느쪽에서든 상대편을 참조할 수 있는 구조이기에 쉽게 상대편을 참조하여 쉽게 정보를 가져올 수 있다는 장점이있다.
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
}*/
