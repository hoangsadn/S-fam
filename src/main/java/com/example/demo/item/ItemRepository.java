package com.example.demo.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findByIdAndAppUserItemId(Long id, Long appid);

    List<Item> findAllByAppUserItem_Email(String email);

    List<Item> findAllByNameIsContainingAndAppUserItemId(String name, Long id);
}
