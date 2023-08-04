package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    void delete(Long tno);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void update(TodoVO todoVO);

    //페이징이 적용된 글 목록 조회
    List<TodoVO>selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
