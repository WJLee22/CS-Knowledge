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
}
