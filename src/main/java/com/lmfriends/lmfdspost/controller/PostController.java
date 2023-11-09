package com.lmfriends.lmfdspost.controller;

import java.util.HashMap;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmfriends.lmfdspost.dto.PostCreateDto;
import com.lmfriends.lmfdspost.dto.PostUpdateDto;
import com.lmfriends.lmfdspost.dto.ResponseDto;
import com.lmfriends.lmfdspost.entity.Post;
import com.lmfriends.lmfdspost.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostController {

  private final PostService postService;

  @GetMapping("/posts")
  public ResponseEntity<ResponseDto<Page<Post>>> index(
      @PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
    Page<Post> listPost = postService.findAll(pageable);
    return new ResponseEntity<>(ResponseDto.res("success", listPost), HttpStatus.OK);
  }

  @PostMapping("/posts")
  public ResponseEntity<ResponseDto<JSONObject>> store(PostCreateDto dto) {
    String postSlug = postService.store(dto);

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("slug", postSlug);
    JSONObject jsonObject = new JSONObject(map);

    return new ResponseEntity<>(ResponseDto.res("success", jsonObject), HttpStatus.OK);
  }

  @GetMapping("/posts/{slug}")
  public ResponseEntity<ResponseDto<Post>> show(@PathVariable(value = "slug") String slug) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    Post found = null;
    Optional<Post> optionalPost = postService.show(slug);
    if (optionalPost.isPresent()) {
      found = optionalPost.get();
      httpStatus = HttpStatus.OK;
    }
    return new ResponseEntity<>(ResponseDto.res("success", found), httpStatus);
  }

  @PutMapping("/posts/{slug}")
  public ResponseEntity<ResponseDto<Post>> update(@PathVariable(value = "slug") String slug, PostUpdateDto dto) {
    Post post = postService.update(slug, dto);
    String message = post != null ? "success" : "fail";
    HttpStatus httpStatus = post != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    return new ResponseEntity<>(ResponseDto.res(message, post), httpStatus);
  }

  @DeleteMapping("/posts/{slug}")
  public ResponseEntity<JSONObject> destory(@PathVariable(value = "slug") String slug) {
    String result = postService.destory(slug);
    HttpStatus httpStatus = "success".equals(result) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    JSONObject jsonObject = new JSONObject(map);

    return new ResponseEntity<>(jsonObject, httpStatus);
  }
}
