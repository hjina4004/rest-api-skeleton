package com.lmfriends.lmfdspost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDto<T> {

  private String message;
  private T data;

  public ResponseDto(final String message) {
    this.message = message;
    this.data = null;
  }

  public static <T> ResponseDto<T> res(final String message) {
    return res(message, null);
  }

  public static <T> ResponseDto<T> res(final String message, final T t) {
    return ResponseDto.<T>builder()
        .data(t)
        .message(message)
        .build();
  }
}