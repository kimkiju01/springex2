package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    //PageResponseDTO 는 여러 정보를 생성자를 이용해서 받아서 처리하는 것이 안전 , 예를 들면 pageRequest의 page,size,tododto의 목록 데이터와 전체 데이터 개수도 필요

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,List<E> dtoList,int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page/10.0)) * 10; //마지막 페이지  //
        this.start = this.end -9; //시작 페이지  //페이지가 1이면 start가 10 - 9 이므로 1이됨

        //마지막 페이지의 경우 다시 전체 개수(total)를 고려해야 함
        //만일 10개씩(size) 보여주는 경우 전체 개수가 75라면 마지막 페이지는 10이 아니라 8이 되어야 한다

        int last =  (int)(Math.ceil((total/(double)size)));  //total이 1280 이고 size가 10이면 last는 128이 나온다

        this.end = end > last ? last : end; //마지막 페이지(end)는 앞에서 구한 last 값보다 작은 경우에 last 값이 end가 되어야만 한다

        //이전 페이지의 존재여부는 시작 페이지가(start)가 1이라면 무조건 true가 되어야 한다
        //다음(next)은 마지막 페이지와(end)와 10(size) 곱한 값보다 전체 개수(total)가 더많은 보고 판단 해야 한다

        this.prev = this.start > 1; //start가 1 이면 prev가 거짓이 됨
        this.next = total > this.end * this.size;
    }


}
