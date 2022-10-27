package com.hack.app.repository;

import com.hack.app.entity.Ideas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface IdeasRepository extends JpaRepository<Ideas, Integer> {
    @Query("select i from Ideas i where i.tag like concat('%', ?1, '%')")
    List<Ideas> findByTagContains(String tag);

    @Override
    Optional<Ideas> findById(Integer integer);

    @Query("select i from Ideas i where i.sphere = ?1")
    List<Ideas> findBySphere(String sphere);

    @Override
    List<Ideas> findAll();

    @Override
    void deleteById(Integer integer);
}