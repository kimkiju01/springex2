package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

//    @Test
//    public void testSelelctAll(){
//        List<TodoVO> voList =todoMapper.selectAll();
//        voList.forEach(vo -> log.info(vo));
//    }

@Test
public void testSelectAll() {

    List<TodoVO> voList = todoMapper.selectAll();

    voList.forEach(vo ->  log.info(vo));

}

@Test
public void testSelectOne(){
    TodoVO VO = todoMapper.selectOne(4l);
    log.info(VO);
}
    @Test
    public void testGetTime(){

        log.info("zzzzzzz" +todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("스프ssddd")
                .dueDate(LocalDate.of(2022,10,10))
                .writer("user01aaa")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testDelete(){

        todoMapper.delete(1L);
    }

    @Test
    public void testUpdate(){
       TodoVO todoVO= TodoVO.builder()
                .tno(3L)
                .title("test3333")
                .dueDate(LocalDate.of(2023,9,10))
                .writer("user03333")
                .finished(true)
                .build();
        todoMapper.update(todoVO);
    }

    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t","w"})
                .keyword("test")
                .finished(false)
                .from(LocalDate.of(2023,8,1))
                .to(LocalDate.of(2023,8,12))
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));

    }

    @Test
    public void testCount(){


        PageRequestDTO pageRequestDTO=PageRequestDTO.builder()
                .build();

        int count=todoMapper.getCount(pageRequestDTO);
        log.info(count);
    }






}
