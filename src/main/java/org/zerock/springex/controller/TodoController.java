package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/todo")
public class TodoController {

    @Autowired(required = false)
    TodoService todoService;
//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("list/....");
//
//        model.addAttribute("dtoList" , todoService.getAll());
//    }
    
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO,BindingResult bindingResult ,Model model,RedirectAttributes redirectAttributes){
        log.info("getlist........" + pageRequestDTO);

        log.info("pageRequestDTO 객체의 페이지" +pageRequestDTO.getPage());
        log.info("검색 시 들어오는 조건 값" + pageRequestDTO.getPage() + "," + pageRequestDTO.getSize() + "," + pageRequestDTO.getLink()
            + "," + pageRequestDTO.getKeyword() + "," + pageRequestDTO.getFrom() + "," + pageRequestDTO.getTo() +","+ pageRequestDTO.getTypes()
        );

//        String strs[] = pageRequestDTO.getTypes();
//        for (int i = 0; i < 2 ; i++){
//            log.info(strs[i]);
//        }


        if(bindingResult.hasErrors()){ //에러시 기본값 page는 1로 ,size는 10으로 세팅
            pageRequestDTO=PageRequestDTO.builder().build();
            
        }
        
        model.addAttribute("responseDTO",todoService.getList(pageRequestDTO));

    }

    @GetMapping({"/read","/modify"})
    public void read(Long tno , Model model, PageRequestDTO pageRequestDTO,RedirectAttributes redirectAttributes){
        TodoDTO todoDTO=todoService.getOne(tno);
        log.info("todoDTOo 객체"+ todoDTO);
        redirectAttributes.addAttribute("page" ,pageRequestDTO.getPage());
        model.addAttribute("dto",todoDTO);
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("todo registerGET......");

    }

//    컨트롤러가 리디렉션 시나리오에 대한 특성을 선택하는 데 사용할 수 있는 모델 인터페이스의 특수화입니다. 리디렉션 속성을 추가하려는 의도는 매우 명시적이므로(즉, 리디렉션 URL에 사용하기 위해) 속성 값은 문자열로 형식화되고 쿼리 문자열에 추가되거나 조직에서 URI 변수로 확장될 수 있도록 그런 방식으로 저장될 수 있습니다.

    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult , RedirectAttributes redirectAttributes) {
        log.info("todo DTO ......" + todoDTO);

        if(bindingResult.hasErrors()){
            log.info("데이터 입력 시 유효하지 않은 값 들어옴,처음 입력한 화면으로 들어감");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

//        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @PostMapping("/remove")
    public String remove(Long tno ,RedirectAttributes redirectAttributes){
        log.info("=================remove===========");
        todoService.delete(tno);

        return "redirect:/todo/list";
    }

        @PostMapping("/modify")
        public String modifyPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult , RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO){

       if(bindingResult.hasErrors()){
           redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
           redirectAttributes.addAttribute("tno",todoDTO.getTno());
           return "redirect:/todo/modify";
       }

       log.info(todoDTO);
       todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno",todoDTO.getTno());

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
      return "redirect:/todo/read";
}



}