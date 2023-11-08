package com.lmfriends.lmfdspost.dto;

import com.lmfriends.lmfdspost.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {

  private String slug;
  private String title;
  private String contents;

  public Post toEntity() {
    return Post.builder()
        .slug(slug)
        .title(title)
        .contents(contents)
        .build();
  }
}
