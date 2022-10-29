package com.hack.app.repository;

import com.hack.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.login = ?1")
    boolean existsByLogin(String login);
    @Query("select u from User u where u.login = ?1")
    Optional<User> findUserByLogin(String login);

    @Transactional
    @Modifying
    @Query("update User u set u.name = ?1, u.info = ?2, u.status = ?3 where u.login = ?4")
    void updateNameAndInfoAndStatusByLogin(@Nullable String name, @Nullable String info, @Nullable String status, String login);

}