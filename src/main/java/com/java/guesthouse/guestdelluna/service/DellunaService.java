package com.java.guesthouse.guestdelluna.service;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.guestdelluna.domain.DellunaDao;
import com.java.guesthouse.guestdelluna.service.dto.*;
import com.java.guesthouse.host.service.dto.ExReviewListDto;
import com.java.guesthouse.host.service.dto.HostExListDto;
import com.java.guesthouse.host.service.dto.HostHouseListDto;
import com.java.guesthouse.host.service.dto.HouseReviewListDto;
import com.java.guesthouse.review.domain.ReviewRepository;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DellunaService {

    private final DellunaDao dellunaDao;
    private final ReviewRepository reviewRepository;

    // 찜목록불러오기
    public void zzimlist(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);
        String pageNumber = request.getParameter("pageNumber");

        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 적립페이지 : " + currentPage);

        int countExpZzim = dellunaDao.countExpZzim(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode + "의 체험 찜 개수 : " + countExpZzim);

        int countHouseZzim = dellunaDao.countHouseZzim(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode + "의 게하 찜 개수 : " + countHouseZzim);

        int boardSize = 5;

        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        List<NewExpZzimDto> newExpZzimDto = null;
        if (countExpZzim > 0) {
            newExpZzimDto = dellunaDao.newExpZzimDto(memberCode, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "체험찜 로우넘 리스트 : " + newExpZzimDto);
        }

        List<NewHouseZzimDto> newHouseZzimDto = null;
        if (countHouseZzim > 0) {
            newHouseZzimDto = dellunaDao.newHouseZzimDto(memberCode, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "하우스찜 로우넘 리스트 : " + newHouseZzimDto);
        }

        MemberDto memberDto = dellunaDao.selectForUpdate(email);

        mav.addObject("countHouseZzim", countHouseZzim);
        mav.addObject("countExpZzim", countExpZzim);
        mav.addObject("memberDto", memberDto);
        mav.setViewName("guestdelluna/zzimlist.tiles");

    }

    public void zzimExpAjax(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == "")
            pageNumber = "1";

        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 적립페이지 : " + currentPage);

        int countExpZzim = dellunaDao.countExpZzim(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode + "의 체험 찜 개수 : " + countExpZzim);

        int countHouseZzim = 10000000;

        int boardSize = 5;

        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        HomeAspect.logger.info(HomeAspect.logMsg + startRow + "," + endRow);

        List<NewExpZzimDto> newExpZzimDto = null;
        if (countExpZzim > 0) {
            newExpZzimDto = dellunaDao.newExpZzimDto(memberCode, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "체험찜 로우넘 리스트 : " + newExpZzimDto);
        }

        mav.addObject("newExpZzimDto", newExpZzimDto);
        mav.addObject("countExpZzim", countExpZzim);
        mav.addObject("countHouseZzim", countHouseZzim);
        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.setViewName("guestdelluna/zzimExp.empty");

    }

    public void zzimHouseAjax(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        String usePageNumber = request.getParameter("usePageNumber");
        if (usePageNumber == "")
            usePageNumber = "1";

        int useCurrentPage = Integer.parseInt(usePageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 페이지 : " + useCurrentPage);

        int countHouseZzim = dellunaDao.countHouseZzim(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode + "의 게하 찜 개수 : " + countHouseZzim);

        int countExpZzim = 10000000;

        int boardSize = 5;

        int startRow = (useCurrentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        HomeAspect.logger.info(HomeAspect.logMsg + startRow + "," + endRow);

        List<NewHouseZzimDto> newHouseZzimDto = null;
        if (countHouseZzim > 0) {
            newHouseZzimDto = dellunaDao.newHouseZzimDto(memberCode, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "하우스찜 로우넘 리스트 : " + newHouseZzimDto);
        }

        mav.addObject("newHouseZzimDto", newHouseZzimDto);
        mav.addObject("countExpZzim", countExpZzim);
        mav.addObject("countHouseZzim", countHouseZzim);
        mav.addObject("boardSize", boardSize);
        mav.addObject("useCurrentPage", useCurrentPage);
        mav.setViewName("guestdelluna/zzimHouse.empty");
    }

    public String zzimExpCancle(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int exCode = Integer.parseInt(request.getParameter("value"));
        HomeAspect.logger.info(HomeAspect.logMsg + "eeeeeeeeeeeeeeeeexcode" + exCode);

        int check = dellunaDao.deleteExpZzim(exCode, memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "체험찜삭제 ㅊㅋ : " + check);

        return null;
    }

    // 체험후기수정
    public void reviewUpdateOk(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int exReserveCode = Integer.parseInt(request.getParameter("expUpResCode"));
        HomeAspect.logger.info(HomeAspect.logMsg + "리케스트로 받은 체험예약번호는 : " + exReserveCode);

        String revContent = request.getParameter("expRevContent");
        HomeAspect.logger.info(HomeAspect.logMsg + "리케스트로 받은 수정내뇽 : " + revContent);

        int check = dellunaDao.updateExpReview(memberCode, exReserveCode, revContent);

    }

    public void reviewDelete(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int exReserveCode = Integer.parseInt(request.getParameter("value"));
        HomeAspect.logger.info(HomeAspect.logMsg + "exResCode : " + exReserveCode);

        int check = dellunaDao.deleteReview(exReserveCode, memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + check);

        mav.addObject("check", check);
        mav.setViewName("guestdelluna/reviewDeleteOk.tiles");

    }

    public void revExpAjax(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == "")
            pageNumber = "1";

        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 적립페이지 : " + currentPage);

        int countExpReview = dellunaDao.expReviewCount(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "체험 후기 개수 : " + countExpReview);

        int countHouseReview = 10000000;

        int boardSize = 5;

        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        HomeAspect.logger.info(HomeAspect.logMsg + startRow + "," + endRow);

        List<NewExpReviewDto> myExpreviewList = null;

        if (countExpReview > 0) {
            myExpreviewList = dellunaDao.myExpreviewList(memberCode, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "내가 쓴 체험후기 : " + myExpreviewList.toString());
        }

        mav.addObject("countExpReview", countExpReview);
        mav.addObject("countHouseReview", countHouseReview);
        mav.addObject("myExpreviewList", myExpreviewList);
        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.setViewName("guestdelluna/reviewExp.empty");
    }

    public void getMyGuestHouseReviews(ModelAndView mav, Long memberId, int page) {

        int countHouseReview = reviewRepository.countByMemberId(memberId);;
        int countExpReview = 10000000;

        int boardSize = 5;
        int startRow = (page - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        List<NewHouseReviewDto> guestHouseReviews = dellunaDao.getMyGuestHouseReviews(memberId, startRow, endRow);

        mav.addObject("countExpReview", countExpReview);
        mav.addObject("countHouseReview", countHouseReview);
        mav.addObject("myHousereviewList", guestHouseReviews);
        mav.addObject("boardSize", boardSize);
        mav.addObject("useCurrentPage", page);
    }

    // 내가 예약한 게스트하우스 , 찜 목록 조회
    public void reserveCheck(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        // 게하 예약수 조회
        int countHouse = dellunaDao.countHouse(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "캉누트하우스" + countHouse);

        // 체험 예약수를 조회
        int countExp = dellunaDao.countExp(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + countExp);

        List<NewExpReserveDto> newExpReserveDto = dellunaDao.newNewExpReserve(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "newExpReserveDto : " + newExpReserveDto);

        List<NewHouseReserveDto> newHouseReserveDto = dellunaDao.newNewHouseReserve(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "newHouseReserveDto : " + newHouseReserveDto);

        mav.addObject("newExpReserveDto", newExpReserveDto);
        mav.addObject("newHouseReserveDto", newHouseReserveDto);
        mav.addObject("countExp", countExp);
        mav.addObject("countHouse", countHouse);
        mav.addObject("memberCode", memberCode);
        mav.setViewName("guestdelluna/myReserveList.tiles");

    }

    public void updateMember(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        MemberDto memberDto = dellunaDao.selectForUpdate(email);

        if (memberDto.getMemberImgName() != null) {

            int index = memberDto.getMemberImgName().indexOf("_") + 1;
            memberDto.setMemberImgName(memberDto.getMemberImgName().substring(index));
            HomeAspect.logger.info(HomeAspect.logMsg + memberDto.toString());
        }

        mav.addObject("memberDto", memberDto);
        mav.setViewName("guestdelluna/memberUpdate.tiles");

    }

    public void updateMemberOk(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
        MemberDto memberDto = (MemberDto) map.get("memberDto");
        HomeAspect.logger.info(HomeAspect.logMsg + memberDto.toString());

        MultipartFile upFile = request.getFile("file");
        HomeAspect.logger.info(HomeAspect.logMsg + "file : " + upFile);
        long fileSize = upFile.getSize();
        HomeAspect.logger.info(HomeAspect.logMsg + fileSize);
        int check = 0;
        if (fileSize != 0) {

            String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
            HomeAspect.logger.info(HomeAspect.logMsg + fileName);
            File path = new File("C:\\profile");
            path.mkdir();

            if (path.exists() && path.isDirectory()) {

                File file = new File(path, fileName);

                try {
                    upFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");

                MemberDto searchFile = dellunaDao.selectForUpdate(email);
                HomeAspect.logger.info(HomeAspect.logMsg + searchFile.toString());

                memberDto.setMemberImgName(fileName);
                memberDto.setMemberImgSize(fileSize);
                memberDto.setMemberImgPath(file.getAbsolutePath());

            }

        }

        String memberInfo = request.getParameter("memberInfo");
        HomeAspect.logger.info(HomeAspect.logMsg + "멤버정보 : " + memberInfo);
        memberDto.setMemberInfo(memberInfo);
        check = dellunaDao.updateMember(memberDto);

        HomeAspect.logger.info(HomeAspect.logMsg + check);
        HomeAspect.logger.info(HomeAspect.logMsg + memberDto.toString());
        mav.addObject("check", check);
        mav.setViewName("guestdelluna/memberUpdateOk.tiles");

    }

    public void deleteMember(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        int memberCode = dellunaDao.selectMemberCode(email);
        MemberDto memberDto = dellunaDao.selectForUpdate(email);

        mav.addObject("memberCode", memberCode);
        mav.addObject("memberDto", memberDto);
        mav.addObject("email", email);
        mav.setViewName("guestdelluna/memberDelete.tiles");
    }

    public void deleteMemberOk(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HomeAspect.logger.info(HomeAspect.logMsg + email + " / " + password);

        int check = dellunaDao.memberDeleteOk(email, password);
        HomeAspect.logger.info(HomeAspect.logMsg + check);

        mav.addObject("check", check);
        mav.setViewName("guestdelluna/memberDeleteOk.tiles");
    }

    public void myReserveExp(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        int memberCode = dellunaDao.selectMemberCode(email);

        int exCode = Integer.parseInt(request.getParameter("exCode"));
        HomeAspect.logger.info(HomeAspect.logMsg + exCode);

        ExpReservationDto expReservationDto = dellunaDao.myResExp(exCode);
        HomeAspect.logger.info(HomeAspect.logMsg + expReservationDto.toString());

        mav.addObject("expReservationDto", expReservationDto);
        mav.addObject("exCode", exCode);

    }

    // 하우시 예약취소
    public String reserveCancle(ModelAndView mav) throws Throwable {
        // TODO Auto-generated method stub

        // 게스트하우스 예약 취소
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        // reserveCode를 받아와서 해당하는 거 변경
        int houseReserveCode = Integer.parseInt(request.getParameter("value"));
        HomeAspect.logger.info(HomeAspect.logMsg + "houseReserveCode 캔슬: " + houseReserveCode);

        int check = dellunaDao.updateState(houseReserveCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "ㅊㅋ : " + check);

        return null;

    }

    // 체험예약취소
    public String expCancle(ModelAndView mav) throws Throwable {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        // expReserveCode를 받아와서 해당하는 거 변경
        int expReserveCode = Integer.parseInt(request.getParameter("eXvalue"));
        HomeAspect.logger.info(HomeAspect.logMsg + "expReserveCode 캔슬: " + expReserveCode);

        int check = dellunaDao.updateExpState(expReserveCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "ㅊㅋ : " + check);

        return null;

    }

    public void listPay(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        // 게하 예약리스트(state가 예약완료인것)을 조회
        // dto를 받아서 dto를 넘겨주면 foreach만큼 돌린다
        String state = "예약완료";

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 결제페이지 : " + currentPage);

        int countPayExp = dellunaDao.countPayExp(memberCode, state);
        HomeAspect.logger.info(HomeAspect.logMsg + "countPayExp : " + countPayExp);

        int countPayHouse = dellunaDao.countPayHouse(memberCode, state);
        HomeAspect.logger.info(HomeAspect.logMsg + "countPayHouse : " + countPayHouse);

        int boardSize = 5;

        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        List<NewExpResDto> newExpResDto = null;
        if (countPayExp > 0) {
            newExpResDto = dellunaDao.newExpResDto(memberCode, state, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "내가 결제한 체험들의 리스트 : " + newExpResDto.toString());
        }

        List<NewHouseResDto> newHouseResDto = null;
        if (countPayHouse > 0) {
            newHouseResDto = dellunaDao.newHouseResDto(memberCode, state, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "내가 결제한 하우스들의 리스트 : " + newHouseResDto.toString());
        }

        MemberDto memberDto = dellunaDao.selectForUpdate(email);

        mav.addObject("countPayHouse", countPayHouse);
        mav.addObject("countPayExp", countPayExp);
        mav.addObject("memberDto", memberDto);
        mav.setViewName("guestdelluna/myPayList.tiles");

    }

    public void payExpAjax(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        String state = "예약완료";

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == "")
            pageNumber = "1";

        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 적립페이지 : " + currentPage);

        int countPayExp = dellunaDao.countPayExp(memberCode, state);
        HomeAspect.logger.info(HomeAspect.logMsg + "countPayExp : " + countPayExp);

        int countPayHouse = 10000000;

        int boardSize = 5;

        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        HomeAspect.logger.info(HomeAspect.logMsg + startRow + "," + endRow);

        List<NewExpResDto> newExpResDto = null;

        if (countPayExp > 0) {
            newExpResDto = dellunaDao.newExpResDto(memberCode, state, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "내가 예약한 체험들의 리스트 : " + newExpResDto.toString());
        }

        mav.addObject("countPayExp", countPayExp);
        mav.addObject("newExpResDto", newExpResDto);
        mav.addObject("countPayHouse", countPayHouse);
        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.setViewName("guestdelluna/payExp.empty");
    }

    public void payHouseAjax(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        String state = "예약완료";

        String usePageNumber = request.getParameter("usePageNumber");
        if (usePageNumber == "")
            usePageNumber = "1";

        int useCurrentPage = Integer.parseInt(usePageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "현재 페이지 : " + useCurrentPage);

        int countPayExp = 10000000;

        int countPayHouse = dellunaDao.countPayHouse(memberCode, state);
        HomeAspect.logger.info(HomeAspect.logMsg + "countPayHouse : " + countPayHouse);

        int boardSize = 5;

        int startRow = (useCurrentPage - 1) * boardSize + 1;
        int endRow = startRow + boardSize - 1;

        HomeAspect.logger.info(HomeAspect.logMsg + startRow + "," + endRow);

        List<NewHouseResDto> newHouseResDto = null;
        if (countPayHouse > 0) {
            newHouseResDto = dellunaDao.newHouseResDto(memberCode, state, startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "내가 예약한 하우스들의 리스트 : " + newHouseResDto.toString());
        }


        mav.addObject("countPayExp", countPayExp);
        mav.addObject("countPayHouse", countPayHouse);
        mav.addObject("boardSize", boardSize);
        mav.addObject("useCurrentPage", useCurrentPage);
        mav.addObject("newHouseResDto", newHouseResDto);
        mav.setViewName("guestdelluna/payHouse.empty");
    }

    public String deleteExpPayList(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        // 찜 예약코드를 받아옴
        int exReserveCode = Integer.parseInt(request.getParameter("exValue"));
        HomeAspect.logger.info(HomeAspect.logMsg + "결제내역에서 예약취소를 누르면 가져오는 체함예약코드는 : " + exReserveCode);

        int check = dellunaDao.deletePayListExp(exReserveCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "삭제가 잘 됐다면 넘어온 체크값은 : " + check);

        return null;
    }

    public String deleteExpPayHouse(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int houseReserveCode = Integer.parseInt(request.getParameter("houseValue"));
        HomeAspect.logger.info(HomeAspect.logMsg + "결제내역에서 예약취소를 누르면 가져오는 게스타하우스예약코드는 :" + houseReserveCode);

        int check = dellunaDao.deletePayListHouse(houseReserveCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "삭제가 됐다면 넘어온 체크값은 : " + check);

        return null;

    }

    public void myInfo(ModelAndView mav) {
        // TODO Auto-generated method stub

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        String memberLevel = (String) session.getAttribute("memberLevel");
        HomeAspect.logger.info(HomeAspect.logMsg + memberLevel);
        HomeAspect.logger.info(HomeAspect.logMsg + session.getAttribute("memberCode"));
        int memberCode;
        if (request.getParameter("memberCode") != null) {
            memberCode = Integer.parseInt(request.getParameter("memberCode"));
        } else {
            memberCode = Integer.parseInt(session.getAttribute("memberCode") + "");
        }
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        // MemberDto memberDto = dellunaDao.selectForUpdate(email);
        MemberDto memberDto = dellunaDao.selectMemberDto(memberCode);

        mav.addObject("memberDto", memberDto);

        String housePageNumber = request.getParameter("housePageNumber");
        if (housePageNumber == null)
            housePageNumber = "1";

        // host가 가지고있는 숙소 리스트 별점 등 가져오기
        List<HostHouseListDto> hostHouseList = dellunaDao.getHouseList(memberCode);
        for (int i = 0; i < hostHouseList.size(); i++) {
            HomeAspect.logger.info(HomeAspect.logMsg + "hostHouseList: " + hostHouseList.get(i).toString());
        }

        // host가 가지고 있는 체험 리스트 별점 등 가져오기
        List<HostExListDto> hostExList = dellunaDao.getExList(memberCode);
        for (int i = 0; i < hostExList.size(); i++) {
            HomeAspect.logger.info(HomeAspect.logMsg + "hostExList: " + hostExList.get(i).toString());
        }

        // host의 숙소 후기 리스트
        List<HouseReviewListDto> houseReviewList = dellunaDao.getHouseReviewList(memberCode);
        for (int i = 0; i < houseReviewList.size(); i++) {
            HomeAspect.logger.info(HomeAspect.logMsg + "houseReviewList: " + houseReviewList.get(i).toString());
        }

        // host의 체험 후기 리스트
        List<ExReviewListDto> exReviewList = dellunaDao.getExReviewList(memberCode);
        for (int i = 0; i < exReviewList.size(); i++) {
            HomeAspect.logger.info(HomeAspect.logMsg + "exReviewList: " + exReviewList.get(i).toString());
        }

        //숙소 후기 개수
        int houseReviewCount = dellunaDao.getHouseReviewCount(memberCode);

        //체험 후기 개수
        int exReviewCount = dellunaDao.getExReviewCount(memberCode);

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null)
            pageNumber = "1";

        int currentPage = Integer.parseInt(pageNumber);

        int housecurrentPage = Integer.parseInt(housePageNumber);

        mav.addObject("exReviewCount", exReviewCount);
        mav.addObject("houseReviewCount", houseReviewCount);
        mav.addObject("hostHouseList", hostHouseList);
        mav.addObject("hostExList", hostExList);
        mav.addObject("houseReviewList", houseReviewList);
        mav.addObject("exReviewList", exReviewList);
        mav.addObject("memberLevel", memberLevel);
        mav.addObject("memberDto", memberDto);
        // mav.addObject(attributeName, attributeValue);

        // 전체메시지개수 출력
        String msgCheck = "읽지 않음";
        int cntAllMsg = dellunaDao.allMsg(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "총 메시지 개수 : " + cntAllMsg);

        // 전체 메시지 리스트 받아옴
        List<MsgDto> allMsgDto = dellunaDao.allMsgDto(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "모든 메시지 리스트 : " + allMsgDto);

        // 읽지 않은 메시지 개수 출력
        int cntMsg = dellunaDao.selectMSG(memberCode, msgCheck);
        HomeAspect.logger.info(HomeAspect.logMsg + "읽지 않은 메시지 개수는 : " + cntMsg);

        // 읽지않은 메시지 리스트 받아옴
        List<MsgDto> msgDto = dellunaDao.listMsg(memberCode, msgCheck);
        HomeAspect.logger.info(HomeAspect.logMsg + "읽지 않은 메시지 리스트들 : " + msgDto);


        mav.addObject("allMsgDto", allMsgDto);
        mav.addObject("cntAllMsg", cntAllMsg);
        mav.addObject("cntMsg", cntMsg);
        mav.addObject("msgDto", msgDto);
        mav.addObject("currentPage", currentPage);
        mav.addObject("housecurrentPage", housecurrentPage);
        mav.addObject("memberDto", memberDto);
        // mav.addObject(attributeName, attributeValue);

        mav.setViewName("guestdelluna/myInfo.tiles");

    }

    public void deleteAllMsg(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int check = dellunaDao.deleteAllMsg(memberCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "메시지삭제됐나요 : " + check);

    }

    public String scroll(ModelAndView mav) {
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        HttpServletResponse response = (HttpServletResponse) map.get("response");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        int memberCode = Integer.parseInt(request.getParameter("memberCode"));
        HomeAspect.logger.info(HomeAspect.logMsg + "memberCode: " + memberCode);

        String pageNumber = request.getParameter("page");
        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "currentPage: " + currentPage);

        int boardSize = 5;
        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = currentPage * boardSize;

        String status = request.getParameter("status");
        HomeAspect.logger.info(HomeAspect.logMsg + status);

        List<HouseReviewListDto> houseReviewList = null;
        String jsonText = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        if (status.equals("house")) {
            houseReviewList = dellunaDao.getHouseReviewListScroll(memberCode, startRow, endRow);
            for (int i = 0; i < houseReviewList.size(); i++) {
                HomeAspect.logger.info(HomeAspect.logMsg + "houseReviewList: " + houseReviewList.get(i).toString());
            }

            JSONArray arr = new JSONArray();
            for (HouseReviewListDto houseReviewListDto : houseReviewList) {
                HashMap<String, String> commonMap = new HashMap<>();
                commonMap.put("code", Integer.toString(houseReviewListDto.getHouseCode()));
                commonMap.put("name", houseReviewListDto.getHouseName());
                commonMap.put("mainImgName", houseReviewListDto.getMainImgName());
                commonMap.put("revContent", houseReviewListDto.getRevContent());
                commonMap.put("revRate", Integer.toString(houseReviewListDto.getRevRate()));

                commonMap.put("revDate", dateFormat.format(houseReviewListDto.getRevDate()));
                commonMap.put("memberCode", Integer.toString(houseReviewListDto.getMemberCode()));

                commonMap.put("memberImgName", houseReviewListDto.getMemberImgName());
                commonMap.put("memberName", houseReviewListDto.getMemberName());

                arr.add(commonMap);
                HomeAspect.logger.info(HomeAspect.logMsg + "houseReviewList: " + commonMap.toString());
            }

            jsonText = JSONValue.toJSONString(arr);
            HomeAspect.logger.info(HomeAspect.logMsg + "jsonText: " + jsonText);
        }

        List<ExReviewListDto> exReviewList = null;
        if (status.equals("ex")) {
            exReviewList = dellunaDao.getExReviewListScroll(memberCode, startRow, endRow);
            for (int i = 0; i < exReviewList.size(); i++) {
                HomeAspect.logger.info(HomeAspect.logMsg + "exReviewList: " + exReviewList.get(i).toString());
            }
            JSONArray arr = new JSONArray();
            for (ExReviewListDto exReviewListDto : exReviewList) {
                HashMap<String, String> commonMap = new HashMap<String, String>();
                commonMap.put("code", Integer.toString(exReviewListDto.getExCode()));
                commonMap.put("name", exReviewListDto.getExName());
                commonMap.put("mainImgName", exReviewListDto.getMainImgName());
                commonMap.put("revContent", exReviewListDto.getRevContent());
                commonMap.put("revRate", Integer.toString(exReviewListDto.getRevRate()));

                commonMap.put("revDate", dateFormat.format(exReviewListDto.getRevDate()));
                commonMap.put("memberCode", Integer.toString(exReviewListDto.getMemberCode()));

                commonMap.put("memberImgName", exReviewListDto.getMemberImgName());
                commonMap.put("memberName", exReviewListDto.getMemberName());

                arr.add(commonMap);
                HomeAspect.logger.info(HomeAspect.logMsg + "exReviewListDto: " + commonMap.toString());
            }

            jsonText = JSONValue.toJSONString(arr);
            HomeAspect.logger.info(HomeAspect.logMsg + "jsonText: " + jsonText);

        }
//
//		JSONArray arr = new JSONArray();
//		for (HouseReviewListDto houseReviewListDto : houseReviewList) {
//			HashMap<String, Object> commonMap = new HashMap<>();
//			commonMap.put("houseName", houseReviewListDto.getHouseName());
//			commonMap.put("mainImgName", houseReviewListDto.getMainImgName());
//			commonMap.put("revContent", houseReviewListDto.getRevContent());
//			commonMap.put("revRate", houseReviewListDto.getRevRate());
//			commonMap.put("revDate", houseReviewListDto.getRevDate());
//			commonMap.put("memberImgName", houseReviewListDto.getMemberImgName());
//			commonMap.put("memberName", houseReviewListDto.getMemberName());
//
//			arr.add(commonMap);
//			HomeAspect.logger.info(HomeAspect.logMsg + "houseReviewList: " + commonMap.toString());
//		}
//
//		String jsonText = JSONValue.toJSONString(arr);
//
//		return jsonText;

//		mav.addObject("exReviewList", exReviewList);
//		mav.addObject("houseReviewList", houseReviewList);
        // mav.setViewName("guestdelluna/scroll.empty");

        return jsonText;

    }

    public void msgDelete(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int msgCode = Integer.parseInt(request.getParameter("msgCode"));
        HomeAspect.logger.info(HomeAspect.logMsg + "넘어온 메시지 코드 : " + msgCode);

        int check = dellunaDao.deleteMsg(memberCode, msgCode);
        HomeAspect.logger.info(HomeAspect.logMsg + "체크 값 : " + check);

    }

    public void msgUpdate(ModelAndView mav) {
        // TODO Auto-generated method stub
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        HomeAspect.logger.info(HomeAspect.logMsg + email);

        int memberCode = dellunaDao.selectMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + memberCode);

        int msgCode = Integer.parseInt(request.getParameter("msgCode"));
        HomeAspect.logger.info(HomeAspect.logMsg + "넘어온 메시지 코드 : " + msgCode);

        String msgCheck = "읽음";

        int check = dellunaDao.updateMsg(memberCode, msgCode, msgCheck);
        HomeAspect.logger.info(HomeAspect.logMsg + "수신 확인이 잘 됐다면 : " + check);

    }

    public void doExZzim(String memberCode, String exCode, String zzim) {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("memberCode", memberCode);
        dataMap.put("exCode", exCode);

        int check = dellunaDao.doExZzim(dataMap, zzim);
        HomeAspect.logger.info(HomeAspect.logMsg + "check: " + check);

    }

}
