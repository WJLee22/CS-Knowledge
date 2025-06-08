package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    // CourseDao 빈을 의존성 주입받음.
    @Autowired
    private CourseDao courseDao;

    // getAllCreditsPerSemester() => 학기별 이수 총 학점 정보를 가져오는 메서드
    public List<Map<String, Object>> getAllCreditsPerSemester() {
        // getCreditsPerSemester 호출로 반환된 리스트의 각 요소는 객체배열로, 해당 배열의 각 요소는 년도, 학기, 취득학점을 나타냄.
        List<Object[]> creditsPerSemester = courseDao.getCreditsPerSemester();
        // DB에서 얻어온 학기별 이수 총 학점 정보를 Map 형태로 변환하여 저장할 리스트.
        List<Map<String, Object>> result = new ArrayList<>();
        // 각 학기별 정보들을 각각 Map 형태로 리스트의 요소로 추기.
        for (Object[] row : creditsPerSemester) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", row[0]);
            map.put("semester", row[1]);
            map.put("totalCredits", row[2]);
            result.add(map);
        }

        return result;
    }

    // getTotalCredits() => 전체 총 이수 학점 정보를 가져오는 메서드
    public Long getTotalCredits() {
        return courseDao.getCredits();
    }

    // getCourseDetails() => ‘상세보기’ 링크 클릭시 나타나는 특정 년도의 해당학기에 해당하는 학기별 수강 내역 레코드들을 가져오는 메서드
    public List<Course> getAllCoursesByYearAndSemester(int year, int semester) {
        return courseDao.getCoursesByYearAndSemester(year, semester);
    }

    // insertCourse() => 수강신청 폼에서 입력한 Course 객체를 DB에 저장하는 메서드
    public void insertCourse(Course course) {
        courseDao.insert(course);
    }

}
