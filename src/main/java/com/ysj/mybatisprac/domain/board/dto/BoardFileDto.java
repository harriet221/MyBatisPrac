package com.ysj.mybatisprac.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDto {
    private Long id;
    private Long boardId;
    private String originalFileName;
    private String storedFileName;
}
