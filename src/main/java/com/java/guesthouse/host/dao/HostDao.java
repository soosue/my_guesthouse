package com.java.guesthouse.host.dao;

import java.util.List;

import com.java.guesthouse.experience.dto.ExperienceDto;
import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.guestreserve.dto.GuestReserveDto;
import com.java.guesthouse.host.dto.ExReservationListDto;
import com.java.guesthouse.host.dto.HostDto;
import com.java.guesthouse.host.dto.ReservationListDto;
import com.java.guesthouse.host.dto.SearchDateList;
import com.java.guesthouse.host.dto.SearchDateListCount;
import com.java.guesthouse.member.dto.MemberDto;

public interface HostDao {

    int subImgUpload(FileDto fileDto);

    int hostRegister(HostDto hostDto);

    int memberProfileImg(MemberDto memberDto);

    int memberCode(String email);

    int mainImgUpload(FileDto fileDto);

    int houseCode();

    List<HostDto> houseList(int memberCode, int startRow, int endRow);

    int hostCancel(int houseCode);

    List<String> houseNameList(int memberCode);

    int getHouseCode(String houseName);

    List<ReservationListDto> reserveViewList(int houseCode, int startRow, int endRow);

    int getHouseCount(String email);

    int getReserveCount(int houseCode);

    int getExCount(int memberCode);

    List<ExperienceDto> experienceList(int memberCode, int startRow, int endRow);

    List<GuestReserveDto> getSales(int memberCode);

    SearchDateListCount getSearchDateCount(int memberCode, String startDate, String endDate);

    List<SearchDateList> searchDateList(int memberCode, String startDate, String endDate, int startRow,
                                        int endRow);

    MemberDto selectMemberDto(int memberCode);

    List<HostDto> ahouseList(int memberCode);

    int exCancel(int exCode);

    List<String> exNameList(int memberCode);

    int getExCode(String exName);

    int getExReserveCount(int exCode);

    List<ExReservationListDto> exReserveViewList(int exCode, int startRow, int endRow);

    String getLocal(String localName);

    int houseNameCheck(String houseName);

}
