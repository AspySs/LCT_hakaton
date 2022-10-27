package com.hack.app.repository;

import com.hack.app.entity.Reactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReactionsRepository extends JpaRepository<Reactions, Integer> {
    @Query("select count(r) from Reactions r where r.reaction = true and r.idea.id = ?1")
    long countByReactionTrueAndIdea_Id(Integer id);

    @Query("select count(r) from Reactions r where r.reaction = false and r.idea.id = ?1")
    long countByReactionFalseAndIdea_Id(Integer id);

    @Query("select count(r) from Reactions r where r.reaction = true and r.comment.id = ?1")
    long countByReactionTrueAndComment_Id(Integer id);

    @Query("select count(r) from Reactions r where r.reaction = false and r.comment.id = ?1")
    long countByReactionFalseAndComment_Id(Integer id);

    @Query("select (count(r) > 0) from Reactions r where r.user_id = ?1 and r.idea.id = ?2")
    boolean existsByUser_idAndIdea_Id(Integer user_id, Integer id);

    @Query("select (count(r) > 0) from Reactions r where r.user_id = ?1 and r.comment.id = ?2")
    boolean existsByUser_idAndComment_Id(Integer user_id, Integer id);

    @Override
    void deleteById(Integer integer);

    @Query("select r from Reactions r where r.user_id = ?1 and r.idea.id = ?2")
    Optional<Reactions> findByUser_idAndIdea_Id(Integer user_id, Integer id);

    @Query("select r from Reactions r where r.user_id = ?1 and r.comment.id = ?2")
    Optional<Reactions> findByUser_idAndComment_Id(Integer user_id, Integer id);
}