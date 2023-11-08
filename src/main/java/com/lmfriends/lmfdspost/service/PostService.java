package com.lmfriends.lmfdspost.service;

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lmfriends.lmfdspost.dto.PostCreateDto;
import com.lmfriends.lmfdspost.dto.PostUpdateDto;
import com.lmfriends.lmfdspost.entity.Post;
import com.lmfriends.lmfdspost.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  public Page<Post> findAll(Pageable pageable) {
    return postRepository.findAll(pageable);
  }

  public String makeSlug(String input) {
    while (input.endsWith("."))
      input = input.substring(0, input.length() - 1);

    Pattern WHITESPACE = Pattern.compile("[\\s]");
    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
    if (nowhitespace.length() > 16)
      nowhitespace = nowhitespace.substring(0, 16);
    return nowhitespace.toLowerCase(Locale.ENGLISH);
  }

  public String toSlug(String input) {
    String baseSlug = makeSlug(input);
    String slug = baseSlug;

    int nIndex = 1;
    Optional<Post> optionalPost = postRepository.findBySlug(slug);
    while (optionalPost.isPresent()) {
      slug = baseSlug + "-" + nIndex++;
      optionalPost = postRepository.findBySlug(slug);
    }

    return slug;
  }

  public String store(PostCreateDto dto) {
    Post post = dto.toEntity();

    String slug = toSlug(dto.getTitle());
    post.setSlug(slug);

    return postRepository.save(post).getSlug();
  }

  public Optional<Post> findBySlug(String slug) {
    return postRepository.findBySlug(slug);
  }

  public Post update(Long id, PostUpdateDto dto) {
    Optional<Post> optionalPost = postRepository.findById(id);
    Post updatedPost = null;
    if (optionalPost.isPresent()) {
      updatedPost = optionalPost.get();
      String slug = dto.getSlug();
      if (!"".equals(slug) && !updatedPost.getSlug().equals(slug))
        updatedPost.setSlug(toSlug(slug));
      if (!"".equals(dto.getTitle()))
        updatedPost.setTitle(dto.getTitle());
      if (!"".equals(dto.getContents()))
        updatedPost.setContents(dto.getContents());

      postRepository.save(updatedPost);
    }

    return updatedPost;
  }

  public String destory(Long id) {
    Optional<Post> optionalPost = postRepository.findById(id);
    if (!optionalPost.isPresent())
      return "fail";

    postRepository.delete(optionalPost.get());
    return "success";
  }
}
