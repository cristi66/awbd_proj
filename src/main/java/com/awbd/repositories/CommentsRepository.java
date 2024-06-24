package com.awbd.repositories;

import com.awbd.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("DELETE FROM Comments c WHERE c.course.id = :course_id")
    void deleteByCourseId(@Param("course_id") Long Id);

    List<Comments> findByCourseId(Long id);
}
