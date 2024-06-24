package com.awbd.repositories;

import com.awbd.entities.Comments;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentsRepository extends PagingAndSortingRepository<Comments, Long> {



}
