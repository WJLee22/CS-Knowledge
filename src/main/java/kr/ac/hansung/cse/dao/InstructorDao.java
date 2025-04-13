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

    public Instructor findById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        return entityManager.createQuery("SELECT i FROM Instructor i", Instructor.class)
                .getResultList();
    }
}
