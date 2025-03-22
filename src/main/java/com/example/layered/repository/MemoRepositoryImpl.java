package com.example.layered.repository;

import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

  //
  @Override
  public List<MemoResponseDto> findAllMemos() {

    // init list
    List<MemoResponseDto> allMemos = new ArrayList<>();

    // DB에 있는 데이터를 하나씩 꺼내와서 allMemos에 저장
    // HashMap<Memo> -> List<MemoResponseDto>
    for (Memo memo : memoList.values()) {
      MemoResponseDto dto = new MemoResponseDto(memo);
      allMemos.add(dto);
    }

    return allMemos;

  }

  @Override
  public Memo findMemoById(Long id) {
    return memoList.get(id);
  }

  @Override
  public void deleteMemo(Long id) {
    memoList.remove(id);
  }

}
