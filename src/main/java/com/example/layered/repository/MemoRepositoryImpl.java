package com.example.layered.repository;

import com.example.layered.entity.Memo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

// @Component와 같음 즉, Spring Bean으로 등록된다.
// 명시적으로 Repository Layer를 나타냄
// DB와 상호작용하며 데이터를 CRUD
@Repository
public class MemoRepositoryImpl implements MemoRepository {

  // MemoController 에 있던 자료구조를 가져옴
  private final Map<Long, Memo> memoList = new HashMap<>();

}
