package com.example.layered.repository;

import com.example.layered.entity.Memo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

// 데이터 상호작용
@Repository
public class MemoRepositoryImpl implements MemoRepository {

  // MemoController 에 있던 자료구조를 가져옴
  private final Map<Long, Memo> memoList = new HashMap<>();

  @Override
  public Memo saveMemo(Memo memo) {

    Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
    memo.setId(memoId);

    memoList.put(memoId, memo);

    return memo;

  }

}
