package com.example.demo.location;

import com.example.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    Optional<Location> findByAppUser(AppUser appUser);

    Optional<Location> findByAppUser_Email(String email);

}
