package com.ysj.mybatisprac.domain.board.service;

import com.ysj.mybatisprac.domain.board.dto.BoardDto;
import com.ysj.mybatisprac.domain.board.dto.BoardFileDto;
import com.ysj.mybatisprac.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) throws IOException {
        if(boardDto.getBoardFile().get(0).isEmpty()) {
            boardDto.setFileAttached(0);
            boardRepository.save(boardDto);
        } else {
            boardDto.setFileAttached(1);
            // 게시글 저장 후 id 값 활용을 위해 반환 받음
            BoardDto savedBoard = boardRepository.save(boardDto);

            for(MultipartFile boardFile : boardDto.getBoardFile()) {
                // 파일 가져와서 저장용 이름 만들기
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;

                // BoardFileDTO 세팅
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setOriginalFileName(originalFileName);
                boardFileDto.setStoredFileName(storedFileName);
                boardFileDto.setBoardId(savedBoard.getId());

                // 파일 저장용 폴더에 파일 저장 처리
                String savePath = "C:/Users/Harriet.SJ/Downloads/mybatis/" + storedFileName;
                boardFile.transferTo(new File(savePath));

                // board_file_table 저장 처리
                boardRepository.saveFile(boardFileDto);
            }
        }

    }

    public List<BoardDto> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDto findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDto boardDto) {
        boardRepository.update(boardDto);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public List<BoardFileDto> findFile(Long id) {
        return boardRepository.findFile(id);
    }
}
