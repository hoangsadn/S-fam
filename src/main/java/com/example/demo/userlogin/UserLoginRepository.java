package com.example.demo.userlogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserLoginRepository
        extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserLogin a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


    @Transactional
    @Modifying
    @Query("UPDATE UserLogin a " +
            "SET a.password = ?2 WHERE a.email = ?1")
    int setPassAppUser(String email,String pass);

}
