package com.example.tt.services;

import com.example.tt.Repository.CourseRepository;
import com.example.tt.Repository.CourseTableRepository;
import com.example.tt.Repository.UserRepository;
import com.example.tt.models.Course;
import com.example.tt.models.CourseTable;
import com.example.tt.models.Role;
import com.example.tt.models.User;
import com.example.tt.request.course.AddCourseRegister;
import com.example.tt.request.course.CourseRequest;
import com.example.tt.request.course.getRegisteredCourse;
import com.example.tt.responce.UserResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
public class CourseService{
    private final CourseRepository courseRepository;
    private final CourseTableRepository courseTableRepository;
    private final UserRepository userRepository;
//    public CourseService(){
//    }
    public int setCourse(CourseRequest cc){
        User user = userRepository.findById(cc.getOffered()).orElse( null);

        if(user == null){
            return 3;
        }else if(user.getRole() == Role.STUDENT){
            return 2;
        }else{
            var course = Course.builder()
                    .name(cc.getName())
                    .offered(cc.getOffered())
                    .build();
            courseRepository.save(course);
            return 1;
        }
    }

    public int takeCourse(AddCourseRegister addCourseRegister){
        User user = userRepository.findById(addCourseRegister.getUserId()).orElse(null);
        if(user == null){
              return  3;
        }else if(user.getRole() == Role.FACULTY){
            return 2;
        }else {
            CourseTable courseTable1 = new CourseTable(addCourseRegister.getCourseId(), addCourseRegister.getUserId());
            courseTableRepository.save(courseTable1);
            return 1;
        }
    }

    public List<Course>getAllCourses(){
        return courseRepository.findAll();
    }

    public List<UserResponse> getAllByCourseId(Long id){
        return  Objects.requireNonNull(courseTableRepository.findAllByCourseId(id).orElse(null)).stream().map(
                user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .role(user.getRole())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<Course> getAllRegisteredCourseById(getRegisteredCourse registeredCourse){
        return courseTableRepository.findCourseTableByUserID(registeredCourse.getId()).orElseThrow();
    }
}
