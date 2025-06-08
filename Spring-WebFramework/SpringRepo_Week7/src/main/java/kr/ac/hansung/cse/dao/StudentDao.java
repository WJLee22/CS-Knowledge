package kr.ac.hansung.cse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.ac.hansung.cse.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Student student ) {
        entityManager.persist(student);
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAll() {
        return entityManager.createQuery("SELECT c FROM Student c", Student.class).getResultList();
    }

    //하나의 트랜잭션 안에서 student 엔티티도 가져오고 그 엔티티의 getCourses()를 호출하여 연관된 courses 컬렉션도 가져오는 1타2피 메서드.
    public Student findByIdWithCourses(Long id) {
        Student student = entityManager.find(Student.class, id);
        // 강제로 초기화하기 위해 컬렉션에 접근
        if (student != null) {
            // Lazy 필드를 실제 사용하려고하니, 미루어졌던 Lazy fetch는 이때 수행이되는 것임!
            // 즉, student 엔티티가 연관된 course 엔티티들을 가져오기 위해서 courses 컬렉션을 간단히 참조함으로써 이때 courses 컬렉션을 로드하는 작업을 수행함.
            student.getCourses().size(); // 컬렉션 초기화.
        }
        return student;
    }
}