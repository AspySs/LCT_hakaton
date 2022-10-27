package com.hack.app.repository;

import com.hack.app.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    @Query("select count(c) from Comments c where c.idea_id = ?1")
    long countByIdea_id(Integer idea_id);

    @Query("select c from Comments c where c.idea_id = ?1")
    List<Comments> findByIdea_id(Integer idea_id);

    @Override
    void deleteById(Integer integer);

    @Override
    List<Comments> findAll();
}