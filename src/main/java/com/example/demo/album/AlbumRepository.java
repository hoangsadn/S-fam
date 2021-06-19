package com.example.demo.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AlbumRepository extends JpaRepository<Album,Long> {
}