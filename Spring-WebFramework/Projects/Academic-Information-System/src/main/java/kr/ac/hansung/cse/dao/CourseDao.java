package kr.ac.hansung.cse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.ac.hansung.cse.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    //GROUP BY c.year, c.semester: 동일한 year와 semester 값을 가진 레코드들을 그룹화
    //SELECT c.year, c.semester, SUM(c.credits): 각 그룹에 대해 그룹의 year, semester, 그룹 내 credits 필드의 합계를 계산한 값을 검색.
    //ORDER BY c.year ASC, c.semester ASC: 결과를 year와 semester 기준으로 오름차순 정렬.
    //즉, 이 쿼리는 학기별로 총 학점을 계산하고, 오래된 학기부터 최신 학기까지 순서대로 반환한다.
    // 같은 year와 semester 값을 가진 레코드들을 하나의 그룹으로 묶고, 각 레코드들의 년도, 학기, 학점의 합계를 검색하는 JPQL 쿼리문.
    // 반환되는 리스트 예시
    // [
    //    {2023, 2, 6}   // 2023년 2학기 총 학점
    //    {2024, 1, 7},  // 2024년 1학기 총 학점 (3 + 4)
    //    {2024, 2, 5},  // 2024년 2학기 총 학점
    //]
    // 실제 수강한 교과목들만 조회하기 위해서 WHERE절에 2025년도 2학기에 수강예정인 교과목들은 제외하는 조건절을 추가함.
    public List<Object[]> getCreditsPerSemester() {
        return entityManager.createQuery(
                "SELECT c.year, c.semester, SUM(c.credits) " +
                        "FROM Course c " +
                        "WHERE NOT (c.year = 2025 AND c.semester = 2) " +
                        "GROUP BY c.year, c.semester " +
                        "ORDER BY c.year ASC, c.semester ASC"
        ).getResultList();
    }

    // 특정 년도/학기의 상세 수강 목록 조회
    public List<Course> getCoursesByYearAndSemester(int year, int semester) {
        return entityManager.createQuery(
                        "SELECT c FROM Course c WHERE c.year = :year AND c.semester = :semester", Course.class)
                .setParameter("year", year)
                .setParameter("semester", semester)
                .getResultList();
    }

    // 전체 총 이수 학점. 2025년도 2학기 수강예정인 교과목들은 제외함.
    public Long getCredits() {
        return entityManager.createQuery(
                        "SELECT SUM(c.credits) FROM Course c " +
                                "WHERE NOT (c.year = 2025 AND c.semester = 2)", Long.class)
                .getSingleResult();
    }

    // DB의 Courses테이블에 Course 엔티티 객체를 레코드로 저장하는 메서드
    public void insert(Course course) {
        entityManager.persist(course);
    }

}