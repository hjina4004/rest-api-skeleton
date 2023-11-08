# REST API Skeleton V1.0
개발환경
* Spring Boot 3.1.4
* Java 17
* Spring Data JPA


## REST api

* post 목록 [GET]
  ```
  /api/v1/posts?page=0&size=10&sort=id,desc
  ```

* post 생성 [POST]
  ```
  /api/v1/posts

  {
    title:"첫번째 포스트입니다",
    contents:"안녕하세요.\n첫번째 테스트입니다.",
    userId:1
  }
  ```

* post 조회 [GET]
  ```
  /api/v1/posts/{slug}
  ```

* post 수정 [PUT]
  ```
  /api/v1/posts/{id}

  {
    title:"세번째 포스트입니다.",
    contents:"안녕하세요.\n세번째 테스트입니다.",
    slug:"post-1"
  }
  ```

* post 삭제 [DELETE]
  ```
  /api/v1/posts/{id}
  ```
