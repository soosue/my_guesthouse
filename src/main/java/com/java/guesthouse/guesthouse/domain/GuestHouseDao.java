package com.java.guesthouse.guesthouse.domain;

import java.util.Date;
import java.util.List;

import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.MsgDto;
import com.java.guesthouse.guestdelluna.service.dto.PointAccumulate;
import com.java.guesthouse.guestdelluna.service.dto.PointUse;
import com.java.guesthouse.guestreserve.dto.GHouseReviewListDto;
import com.java.guesthouse.guestreserve.dto.GuestReserveDto;
import com.java.guesthouse.guestreserve.dto.RemainDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.member.service.dto.MemberDto;

public interface GuestHouseDao {
    HostDto guestHouseRead(int houseCode);

    List<FileDto> guestHouseImg(int houseCode);

    int getMemberCode(String email);

    HostDto getHostInfo(int houseCode);

    int getPoint(String email);

    MemberDto getHostList(int hostCode);

    String getHouseName(int houseCode);

    int insertReserveInfo(GuestReserveDto guestReserveDto);

    int getReserveCode(int houseCode, int memberCode, Date checkIn);

    int updatePoint(int memberPoint, int memberCode);

    int insertResPoint(PointAccumulate pointAccumulate);

    int insertUsePoint(PointUse pointUse);

    int insertRemain(Date checkIn, int people, int houseCode);

    List<RemainDto> getRemain(int houseCode);

    MemberDto getMemberInfo(String email);

    int insertMsg(MsgDto msgDto);

    int getReviewCnt(int houseCode);

    List<GHouseReviewListDto> getReviewList(int startRow, int endRow, int houseCode);

    int reserveCodeCnt(int memberCode, int houseCode);

    List<GuestReserveDto> reserveCode(int houseCode, int memberCode);

    int reviewChk(int reserveCode);

    int writeReview(HouseReviewDto reviewDto);

    HouseReviewDto reviewUpdate(int memberCode, int reserveCode);

    int reviewUpdateOk(HouseReviewDto reviewDto);

    int reviewDelete(int reserveCode);
}
