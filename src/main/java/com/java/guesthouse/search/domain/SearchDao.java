package com.java.guesthouse.search.domain;

import java.util.List;
import java.util.Map;

import com.java.guesthouse.experience.service.dto.ExperienceImgDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.host.service.dto.HostImgDto;
import com.java.guesthouse.search.service.dto.GetCountDto;

public interface SearchDao {

    List<HostImgDto> searchHouse(Map<String, Object> dataMap);

    //테스트 용으로 데이터 넣기 위한 함수
    int dataInputOk(HostDto hostDto);

    GetCountDto getCount(Map<String, Object> dataMap);

    HostImgDto overlay(int houseCode, Integer memberCode);

    List<ExperienceImgDto> searchEx(Map<String, Object> dataMap);

    int getExCount(Map<String, Object> dataMap);

    ExperienceImgDto exOverlay(int exCode, Integer memberCode);
}
