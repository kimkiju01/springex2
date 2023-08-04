package org.zerock.springex.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService todoService;

    @Test
    public void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test....3")
                .dueDate(LocalDate.now())
                .writer("user3")
                .build();

        todoService.register(todoDTO);
    }

//    @Test
//    public void testDelete(){
//        todoService.delete(7);
//    }

    @Test
    public void testUpdate() {
        TodoDTO todoDTO=TodoDTO.builder()
                .tno(3L)
                .title("test.....3")
                .dueDate(LocalDate.of(2024,05,01))
                .writer("kimkiju")
                .finished(true)
                .build();
        todoService.modify(todoDTO);

    }
    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO=PageRequestDTO.builder()
                .page(3)
                .size(10)
                .build();

        PageResponseDTO pageResponseDTO=todoService.getList(pageRequestDTO);
        pageResponseDTO.getDtoList().forEach(dto -> log.info(dto));
    }
}
