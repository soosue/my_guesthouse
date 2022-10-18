package com.java.guesthouse.admin.domain;

import java.util.List;

import com.java.guesthouse.experience.service.dto.ExperienceDto;
import com.java.guesthouse.member.service.dto.MemberDto;

public interface AdminDao {

    // 게스트하우스 관리
    int houseCount();

    List<MemberDto> houseList(int startRow, int endRow);

    int guestHouseStateOk(int houseCode);

    int guestHouseStateNo(int houseCode);

    // 체험 관리
    int experienceCount();

    List<ExperienceDto> experienceList(int startRow, int endRow);

    int experienceStateOk(int exCode);

    int experienceStateNo(int exCode);

}
