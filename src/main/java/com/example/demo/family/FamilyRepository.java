package com.example.demo.family;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findByName(String name);

    Optional<Family> findByKey(String key);
}
