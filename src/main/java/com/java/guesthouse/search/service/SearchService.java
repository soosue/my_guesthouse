package com.java.guesthouse.search.service;

import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.host.service.dto.HostDto;

public interface SearchService {
    ModelAndView search(String checkIn, String checkOut, String local, String people, String searchHouseName, String pageNumber, Integer memberCode, String sort);

    //테스트 용으로 데이터 넣기 위한 함수
    void dataInputOk(HostDto hostDto);

    String overlay(int houseCode, Integer memberCode);

    ModelAndView searchEx(String checkIn, String checkOut, String local, String people, String searchExName,
                          String pageNumber, Integer memberCode, String sort);

    String exOverlay(int exCode, Integer memberCode);

}
