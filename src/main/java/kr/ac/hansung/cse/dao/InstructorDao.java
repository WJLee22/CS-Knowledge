package kr.ac.hansung.cse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.ac.hansung.cse.entity.Instructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InstructorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }
    // @Transactional 어노테이션으로 인해 이 엔티티 클래스 내 모든 메소드들은 트랜잭션 처리가 됨.
    // Transaction 이 실행되면 -> 각 트랜잭션 마다 EntityManager가 하나 생성되고
    // -> 이 EntityManager는 DB와 연결하여 쿼리를 실행함 -> 그 후 트랜잭션이 종료되면 커밋 후 EntityManager는 닫힘(DB와의 연결종료).
    // 암튼 이 트랜잭션의 수행 결과로 가져온(select) 엔티티(레코드).
    // 이 엔티티에는 courses라는 List<Course> 타입의 필드가 있는데, fetch = FetchType.LAZY 옵션을 줬기에,
    // 이 findById 트랜잭션으로 Instructor 엔티티를 조회할 때 course 엔티티들도 같이 조회되지 않는다. LAZY라서 추후에 필요할때 조회됨.
    public Instructor findById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        return entityManager.createQuery("SELECT i FROM Instructor i", Instructor.class)
                .getResultList();
    }

    // findById 메서드(트랜잭션)는 LaztInitializationException을 발생시킨다는 문제가있었다.
    // 이 문제를 해결하기 위해서, findByIdWithCourses 메서드를 추가.

    // 자 보자. 이 findByIdWithCourses는 @Transactional 어노테이션이 붙은 엔티티 클래스 내의 메서드니깐 하나의 트랜잭션 단위로 실행됨.
    // so, 이전 findById의 경우엔 단순히 find로 엔티티를 조회하고 끝이었기에 이 트랜잭션이 종료되면 EntityManager가 닫히게되어 이후에 getCourses 를 해봤자 닫혀서 LazyInitializationException이 발생했었음.
    // but, 이 findByIdWithCourses는 이 안에서 모두처리하니 하나의 트랜잭션 안에서 find로 엔티티를 조회한 후, 그 엔티티의 getCourses()를 호출하여 courses 컬렉션을 로드하는 작업을 수행함.
    // FetchType.LAZY는 실제로 참조.사용될 때까지 로딩을 미루는 옵션인데, 지금 여기서 getCourses()로 FetchType.LAZY가 붙은 courses 컬렉션을 참조하려고하니 이때 collection을 로드하는 작업이 성공적으로 수행함.
    public Instructor findByIdWithCourses(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor != null) {
            instructor.getCourses().size(); // courses 컬렉션 로드. Lazy 필드를 실제 사용하려고하니, 미루어졌던 Lazy fetch는 이때 수행이되는 것임!
        }
        return instructor;
    }

}
