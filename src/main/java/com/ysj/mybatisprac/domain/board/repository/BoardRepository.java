package com.ysj.mybatisprac.domain.board.repository;

import com.ysj.mybatisprac.domain.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public void save(BoardDto boardDto) {
        sql.insert("Board.save", boardDto); // board-mapper.xml의 namespace.id
    }

    public List<BoardDto> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDto findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDto boardDto) {
        sql.update("Board.update", boardDto);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }
}
