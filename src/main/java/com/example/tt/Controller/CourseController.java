package com.example.tt.Controller;

import com.example.tt.models.Course;
import com.example.tt.request.course.AddCourseRegister;
import com.example.tt.request.course.CourseRequest;
import com.example.tt.request.course.RegisteredStudent;
import com.example.tt.request.course.getRegisteredCourse;
import com.example.tt.responce.UserResponse;
import com.example.tt.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<String> setCourse(@RequestBody CourseRequest courseRequest){
        int res = courseService.setCourse(courseRequest);
        if(res == 3) {
            return ResponseEntity.notFound().build();
        }else if(res == 2){
            return ResponseEntity.badRequest().body("Student can not add course");
        }
        return ResponseEntity.ok("Course Added successfully");
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> addInCourse(@RequestBody AddCourseRegister addCourseRegister){
        int res = courseService.takeCourse(addCourseRegister);
        if(res == 3){
            return ResponseEntity.notFound().build();
        }else if(res == 2){
            return ResponseEntity.badRequest().body("Faculty can't enroll as student in course...");
        }
        return ResponseEntity.ok("Enrolled...");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>>courseUser(@RequestBody RegisteredStudent registeredStudent) {
        return ResponseEntity.ok(courseService.getAllByCourseId(registeredStudent.getId()));
    }

    @GetMapping("/user/")
    public ResponseEntity<List<Course>>getAllCoursesById(@RequestBody getRegisteredCourse getRegisteredCourse){
        return ResponseEntity.ok(courseService.getAllRegisteredCourseById(getRegisteredCourse));
    }
}
