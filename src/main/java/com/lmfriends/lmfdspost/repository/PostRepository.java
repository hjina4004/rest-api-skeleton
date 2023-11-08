package com.lmfriends.lmfdspost.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmfriends.lmfdspost.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findBySlug(String slug);
  List<Post> findByUserId(String userId);
  Page<Post> findAll(Pageable pageable);
}
