package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2

public class SampleController {
    /*@RequestMapping(value = "/hi" ,method = RequestMethod.GET)
    public void hi(){
        log.info("hi");

    }*/
    @GetMapping("/nice2")
    public void hi(){
        log.info("nice2");
    }

    //스프링 mvc 컨트롤러의 특징
    //메서드의 파라미터를 기본 자료형이나 객체 자료형을 마음대로 지정
    //메소드의 리턴타입도 void,String,객체 등 다양한 타입을 사용할 수 있음
    @GetMapping("/ex7")
    public void ex7(String p1,int p2){
        log.info("p1......... " + p1);
        log.info("p2........." +p2);
    }


    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){

        //리다이렉트 할 때 쿼리 스트링(키 = 벨류) -> url에 쿼리 스트링으로 추가
        redirectAttributes.addAttribute("name","hong");
        //일회용이기 때문에 값을 바로 넘겨 줄 수 있다 -> url에는 보이지 않지만 jsp에서는 일회용으로 사용 가능하다
        redirectAttributes.addFlashAttribute("result","nice");
        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex5(){



    }
}
