package com.example.layered.service;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.repository.MemoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// 실제 비즈니스 로직 관련 처리는 서비스 레이어의 구현체에서 진행
@Service
public class MemoServiceImpl implements MemoService {

  private final MemoRepository memoRepository;

  // 생성자 주입
  public MemoServiceImpl(MemoRepository memoRepository) {
    this.memoRepository = memoRepository;
  }

  @Override
  public MemoResponseDto saveMemo(MemoRequestDto dto) {

    // 요청받은 데이터로 Memo 객체 생성
    // 식별자 id는 없음 - Repository 영역이기 때문
    Memo memo = new Memo(dto.getTitle(), dto.getContents());

    // DB 저장
    Memo savedMemo = memoRepository.saveMemo(memo);

    return new MemoResponseDto(savedMemo);

  }

  @Override
  public List<MemoResponseDto> findAllMemos() {
    return memoRepository.findAllMemos();
  }

  @Override
  public MemoResponseDto findMemoById(Long id) {

    Memo memo = memoRepository.findMemoById(id);

    // ResponseEntity<>(HttpStatus.NOT_FOUND) 사용 불가,
    if (memo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dose not exist id: " + id);
    }

    return new MemoResponseDto(memo);

  }

  @Override
  public MemoResponseDto updateMemo(Long id, String title, String contents) {

    Memo memo = memoRepository.findMemoById(id);

    if (memo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dose not exist id: " + id);
    }

    if (title == null || contents == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "the title and content are required values.");
    }

    memo.update(title, contents);

    return new MemoResponseDto(memo);

  }

  @Override
  public MemoResponseDto updateTitle(Long id, String title, String contents) {

    Memo memo = memoRepository.findMemoById(id);

    if (memo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dose not exist id: " + id);
    }

    if (title == null || contents != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "the title is required values.");
    }

    memo.updateTitle(title);

    return new MemoResponseDto(memo);

  }

  @Override
  public void deleteMemo(Long id) {

    Memo memo = memoRepository.findMemoById(id);

    if (memo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dose not exist id: " + id);
    }

    memoRepository.deleteMemo(id);

  }

}
