package com.example.layered.repository;

import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateMemoRepository implements MemoRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateMemoRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public MemoResponseDto saveMemo(Memo memo) {
    // INSERT Query 를 직접 작성하지 않아도 됨
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("memo").usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("title", memo.getTitle());
    parameters.put("contents", memo.getContents());

    // 저장 후 생성된 key 값, ID를 Number 타입으로 반환하는 메소드
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

    return new MemoResponseDto(key.longValue(), memo.getTitle(), memo.getContents());

  }

  @Override
  public List<MemoResponseDto> findAllMemos() {
    return jdbcTemplate.query("SELECT * FROM memo", memoRowMapper());
  }

  @Override
  public Memo findMemoById(Long id) {
    return null;
  }

  @Override
  public void deleteMemo(Long id) {

  }

  private RowMapper<MemoResponseDto> memoRowMapper() {
    return new RowMapper<MemoResponseDto>() {
      @Override
      public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MemoResponseDto(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("contents")
        );
      }
    };

  }

}
