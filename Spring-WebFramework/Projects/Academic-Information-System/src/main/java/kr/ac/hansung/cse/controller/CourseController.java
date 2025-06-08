package kr.ac.hansung.cse.controller;

import jakarta.validation.Valid;
import kr.ac.hansung.cse.model.Course;
import kr.ac.hansung.cse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {

    // CreditsService 빈을 의존성 주입받음.
    @Autowired
    private CourseService courseService;

    @GetMapping("/credits")
    public String showCredits(Model model) {
        // creditsService의 getCreditsPerSemester메서드를 통해 학기별 이수 총 학점 정보를 가져옴.
        model.addAttribute("creditsPerSemester", courseService.getAllCreditsPerSemester());
        // creditsService의 getTotalCredits메서드를 통해 총 학점 정보를 가져옴.
        model.addAttribute("totalCredits", courseService.getTotalCredits());
        // credits.html 뷰를 반환
        return "credits";
    }

    @GetMapping("/details")
    public String showDetails(@RequestParam("year") int year, @RequestParam("semester") int semester, Model model) {
        // creditsService의 getCourseDetails메서드를 통해 특정 년도와 학기에 해당하는 교과목 레코드들을 가져옴.
        model.addAttribute("courses", courseService.getAllCoursesByYearAndSemester(year, semester));
        model.addAttribute("year", year);
        model.addAttribute("semester", semester);
        return "details"; // details.jsp
    }

    // 사용자가 ② 수강 신청하기 버튼을 클릭하면 아래의 컨트롤러 메서드가 호출됨.
    @GetMapping("/enroll")
    public String courseEnroll(Model model) {
        //왜 비어있는 course객체를 모델에 담아주냐면, enroll.html에서 비어있는 초기상태의 폼 태그를 만들기 위해서이다.
        model.addAttribute("course", new Course());
        return "enroll";
    }

    // 사용자가 수강신청 폼을 작성하고 제출하면 아래의 컨트롤러 메서드가 호출됨.
    @PostMapping("/docreate")
    public String doCreate(Model model, @Valid Course course, BindingResult result) {
        // 유효성 검사 결과에 오류가 있는 경우
        if (result.hasErrors())
            return "enroll"; // enroll.html로 돌아감

        // 유효성 검사 통과 시, CourseService -> CourseDao(JPA - EntityManager)를 통해 DB에 레코드저장
        courseService.insertCourse(course);
        return "enrollComplete";
    }

    @GetMapping("/enrolledCourses")
    public String showEnrolledCourses(Model model) {
        // courseService의 getAllCoursesByYearAndSemester메서드를 통해 2025년도 2학기에 수강할 교과목에 대해 수강신청한 모든 교과목 레코드들을 가져옴.
        model.addAttribute("courses", courseService.getAllCoursesByYearAndSemester(2025, 2));
        return "enrolledCourses"; // enrolledCourses.jsp
    }

}
