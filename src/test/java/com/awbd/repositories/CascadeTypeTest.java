package com.awbd.repositories;

import com.awbd.entities.Comments;
import com.awbd.entities.Courses;
import com.awbd.entities.Lessons;
import com.awbd.entities.Users;
import com.awbd.enums.CourseTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
public class CascadeTypeTest {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    public void updateContent(){
        Optional<Courses> courseOpt = coursesRepository.findById(1L);
        Courses course = courseOpt.get();
        course.getComments().getFirst().setContent("Awesome learning curve");
        course.setType(CourseTypeEnum.SELECT);

        coursesRepository.save(course);

        courseOpt = coursesRepository.findById(1L);
        course = courseOpt.get();
        assertEquals(CourseTypeEnum.SELECT, course.getType());
        assertEquals("Awesome learning curve", course.getComments().getFirst().getContent());
    }

    @Test
    public void insertCourse(){
        Courses course = new Courses();
        course.setTitle("Learn SELECT");
        course.setType(CourseTypeEnum.SELECT);
        course.setId(999L);
        course.setDescription("Test description of course");

        Optional<Users> userOpt = usersRepository.findById(1L);
        Users user = userOpt.get();

        List<Comments> comments = new ArrayList<>();
        Comments comment = new Comments();
        comment.setCourse(course);
        comment.setUser(user);
        comment.setContent("Makes it so easy to learn how to use SELECT");

        comments.add(comment);

        course.setComments(comments);

        coursesRepository.save(course);

        Optional<Courses> courseOpt = coursesRepository.findByTitle("Learn SELECT");
        course = courseOpt.get();
        assertEquals(CourseTypeEnum.SELECT, course.getType());
        assertEquals("Makes it so easy to learn how to use SELECT", course.getComments().getFirst().getContent());
    }

    @Test
    public void deleteCourse(){
        coursesRepository.deleteById(2L);

        List<Comments> comments = commentsRepository.findByCourseId(2L);
        assertTrue(comments.isEmpty());
    }
}
