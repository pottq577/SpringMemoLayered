package com.example.layered.controller;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.service.MemoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memos") // Prefix
public class MemoController {

  private final MemoService memoService;

  public MemoController(MemoService memoService) {
    this.memoService = memoService;
  }

  @PostMapping // 요청
  public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
    return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);
  }

  @GetMapping
  public List<MemoResponseDto> findAllMemos() {
    return memoService.findAllMemos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
    return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MemoResponseDto> updateMemo(
      @PathVariable Long id,
      @RequestBody MemoRequestDto dto) {

    return new ResponseEntity<>(
        memoService.updateMemo(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);

  }

  @PatchMapping("/{id}")
  public ResponseEntity<MemoResponseDto> updateTitle(
      @PathVariable Long id,
      @RequestBody MemoRequestDto dto
  ) {

    return new ResponseEntity<>(memoService.updateTitle(id, dto.getTitle(), dto.getContents()),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

    memoService.deleteMemo(id);

    return new ResponseEntity<>(HttpStatus.OK);

  }

}