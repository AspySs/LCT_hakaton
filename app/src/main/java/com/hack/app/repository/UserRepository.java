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

    @Transactional
    @Modifying
    @Query("""
            update User u set u.name = ?1, u.info = ?2, u.status = ?3, u.birth_date = ?4, u.country = ?5, u.city = ?6, u.grajd = ?7, u.gender = ?8, u.contact = ?9, u.education = ?10, u.busyness = ?11, u.expWork = ?12, u.skill = ?13, u.achievements = ?14, u.command = ?15, u.role = ?16
            where u.login = ?17""")
    void updateByLogin(@Nullable String name, @Nullable String info, @Nullable String status, @Nullable String birth_date, @Nullable String country, @Nullable String city, @Nullable String grajd, @Nullable boolean gender, @Nullable String contact, @Nullable String education, @Nullable String busyness, @Nullable String expWork, @Nullable String skill, @Nullable String achievements, @Nullable boolean command, @Nullable String role, String login);

}