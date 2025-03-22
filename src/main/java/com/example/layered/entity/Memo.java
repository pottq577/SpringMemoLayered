package com.example.layered.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Memo {

  // 클래스 레벨에 @Setter 사용 시 전체 필드에 Setter 적용
  // 필드 레벨에서 사용해야 원하는 필드의 @Setter를 적용할 수 있음
  @Setter
  private Long id;
  private String title;
  private String contents;

  public Memo(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  // Dto 대신 기본 자료형으로 받아야 메소드의 유연성과 재사용성이 높아짐
  // 테스트 코드에도 도움이 됨
  public void update(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

}