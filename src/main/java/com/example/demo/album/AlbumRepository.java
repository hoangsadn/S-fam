package com.example.demo.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AlbumRepository extends JpaRepository<Album,Long> {

    List<Album> findAllByNameIsContainingAndFamilyAlbumId(String name,Long id);

    List<Album> findAllByFamilyAlbumId(Long id);
}
