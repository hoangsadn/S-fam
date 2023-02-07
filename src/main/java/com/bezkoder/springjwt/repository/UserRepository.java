package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Boolean existsByName(String name);

  Boolean existsByEmail(String email);


  @Transactional
  @Modifying
  @Query("UPDATE User a " +
          "SET a.password = ?2 WHERE a.email = ?1")
  int setPassAppUser(String email,String pass);


}
