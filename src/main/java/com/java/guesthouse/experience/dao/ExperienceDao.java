package com.java.guesthouse.experience.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.java.guesthouse.exfile.dto.ExFileDto;
import com.java.guesthouse.experience.dto.ExperienceDto;
import com.java.guesthouse.experience.dto.ExperienceMainDto;
import com.java.guesthouse.experience.dto.GuestHouseMainDto;
import com.java.guesthouse.exremain.dto.ExRemainDto;
import com.java.guesthouse.exreserve.dto.ExReserveDto;
import com.java.guesthouse.exreview.dto.ExReviewDto;
import com.java.guesthouse.exreview.dto.ExReviewListDto;
import com.java.guesthouse.guestdelluna.dto.PointAccumulate;
import com.java.guesthouse.guestdelluna.dto.PointUse;
import com.java.guesthouse.host.dto.HostDto;
import com.java.guesthouse.member.dto.MemberDto;

public interface ExperienceDao {

    int subImgUpload(ExFileDto exFileDto);

    int memberCode(String email);

    int exHostRegister(ExperienceDto experienceDto);

    int mainImgUpload(ExFileDto exFileDto);

    int exCode();

    List<HostDto> hostChkList(int memberCode);

    int getReviewCnt(int exCode);

    List<ExReviewListDto> getExReviewList(int startRow, int endRow, int exCode);

    //예약번호가 있는지
    int reserveCodeCnt(int memberCode, int exCode);

    int writeReview(ExReviewDto exReviewDto);

    ExReviewDto exReviewUpdate(int memberCode, int exReserveCode);

    int exReviewUpdateOk(ExReviewDto exReviewDto);

    int exReviewDelete(int exReserveCode);

    ExperienceDto exPage(int exCode);

    List<ExFileDto> exPageImgList(int exCode);

    MemberDto exHostInfo(int memberCode);

    int exPayment(int exPeople, int exCode);

    int getPoint(String email);

    int insertExReserve(ExReserveDto exReserveDto);

    int getExReserveCode(int exCode, int memberCode, Date exDate);

    int pointUpdate(int memberCode, int plusPoint);

    // 후기 작성시 해당 멤버의 예약번호 가져옴
    List<ExReserveDto> exReserveCode(int exCode, int memberCode);

    // 댓글 작성을 했을 경우 또 작성할 수 없게 예약코드가 리뷰 테이블에 존재하는 지 확인
    int reviewChk(int exReserveCode);

    //달력 체험하는 인원 세기
    List<ExReserveDto> reserveList(int exCode, Date exDate);

    // 하루에 수용 가능한 인원
    int getPeople(int exCode);

    // 체험코드로 남은!!!
    List<ExRemainDto> getExRemain(int exCode);

    int insertExRemain(Date exDate, int exPeople, int exCode);

    int message(int memberCode, String msgContent, Date msgDate, String msgCheck);

    int resPointUp(PointAccumulate pointAccumulate);

    int usePointUp(PointUse pointUse);

    // 카카오페이
    MemberDto getMemberInfo(String email);

    // 체험 메인
    List<ExperienceMainDto> searchMainEx();

    // 게하 메인
    List<GuestHouseMainDto> searchMain();

    List<String> exDisableDates(Map<String, Object> map);


}
