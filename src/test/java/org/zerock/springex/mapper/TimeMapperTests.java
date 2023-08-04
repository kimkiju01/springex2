package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {
    //인터페이스라서 반드시 필요하지 않다는 문법을 적어주면 된다

    @Autowired(required = false) // (required = false) 지정하면 해당객체가 반드시 주고 받지 못하더라도 예외가 발생하지 않는다
    private TimeMapper2 timeMapper2;
    @Test
    public void testGetTime(){
        log.info("time go"+timeMapper2.getNow());
    }
}
