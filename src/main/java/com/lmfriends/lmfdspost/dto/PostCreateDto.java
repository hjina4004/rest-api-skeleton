package com.lmfriends.lmfdspost.dto;

import com.lmfriends.lmfdspost.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {

  private String userId;
  private String title;
  private String contents;

  public Post toEntity() {
    return Post.builder()
        .title(title)
        .contents(contents)
        .userId(userId)
        .build();
  }
}
