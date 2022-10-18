package com.java.guesthouse.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.admin.domain.AdminDao;
import com.java.guesthouse.admin.service.dto.UpdateMemberRequest;
import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.experience.service.dto.ExperienceDto;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.member.service.dto.MemberDto;

@Service
public class AdminService {
    private final AdminDao adminDao;
    private final MemberService memberService;

    public AdminService(AdminDao adminDao, MemberService memberService) {
        this.adminDao = adminDao;
        this.memberService = memberService;
    }

    public void memberList(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);    //요청페이지 - 시작, 끝
        HomeAspect.logger.info(HomeAspect.logMsg + "요청페이지: " + currentPage);


        Long count = memberService.count();
        HomeAspect.logger.info(HomeAspect.logMsg + "총 회원 수: " + count);


        int boardSize = 10;
        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = currentPage * boardSize;

        List<MemberDto> memberList = null;

        if (count > 0) {
            memberList = adminDao.memberList(startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "이 페이지 회원 갯수: " + memberList.size());
        }

        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.addObject("count", count);
        mav.addObject("memberList", memberList);

        mav.setViewName("admin/memberList.tiles");

    }

    @Transactional
    public void updateMember(Long id, UpdateMemberRequest request) {
        Member member = memberService.findById(id);

        member.updatePointAndMemberLevel(request.point(), request.memberLevel());
    }

    // 게스트하우스 관리
    public void houseList(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);    //요청페이지 - 시작, 끝
        HomeAspect.logger.info(HomeAspect.logMsg + "요청페이지: " + currentPage);


        int count = adminDao.houseCount();
        HomeAspect.logger.info(HomeAspect.logMsg + "총 게스츠하우스 수: " + count);


        int boardSize = 10;
        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = currentPage * boardSize;

        List<MemberDto> houseList = null;

        if (count > 0) {
            houseList = adminDao.houseList(startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "이 페이지 게하 갯수: " + houseList.size());
        }

        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.addObject("count", count);
        mav.addObject("houseList", houseList);

        mav.setViewName("admin/houseList.tiles");

    }
    // 체험 관리

    public void experienceList(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null) {
            pageNumber = "1";
        }
        int currentPage = Integer.parseInt(pageNumber);
        HomeAspect.logger.info(HomeAspect.logMsg + "요청페이지: " + currentPage);


        int count = adminDao.experienceCount();
        HomeAspect.logger.info(HomeAspect.logMsg + "총 체험 수: " + count);


        int boardSize = 10;
        int startRow = (currentPage - 1) * boardSize + 1;
        int endRow = currentPage * boardSize;

        List<ExperienceDto> experienceList = null;

        if (count > 0) {
            experienceList = adminDao.experienceList(startRow, endRow);
            HomeAspect.logger.info(HomeAspect.logMsg + "이 페이지 체험 갯수: " + experienceList.size());
        }

        mav.addObject("boardSize", boardSize);
        mav.addObject("currentPage", currentPage);
        mav.addObject("count", count);
        mav.addObject("experienceList", experienceList);

        mav.setViewName("admin/experienceList.tiles");


    }

    public void experienceStateOK(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int exCode = Integer.parseInt(request.getParameter("exCode"));

        int exStateOk = adminDao.experienceStateOk(exCode);
        HomeAspect.logger.info(HomeAspect.logMsg + " exStateOk : " + exStateOk);

        mav.addObject("exCode", exCode);
        mav.addObject("exStateOk", exStateOk);

        mav.setViewName("admin/exState.tiles");

    }

    public void experienceStateNo(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int exCode = Integer.parseInt(request.getParameter("exCode"));

        int exStateNo = adminDao.experienceStateNo(exCode);

        HomeAspect.logger.info(HomeAspect.logMsg + " exStateNo : " + exStateNo);

        mav.addObject("exCode", exCode);
        mav.addObject("exStateNo", exStateNo);

        mav.setViewName("admin/exState.tiles");

    }

    @Transactional
    public void guestHouseStateOK(ModelAndView mav) {
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int houseCode = Integer.parseInt(request.getParameter("houseCode"));
        int memberCode = Integer.parseInt(request.getParameter("memberCode"));

        int ghStateOk = adminDao.guestHouseStateOk(houseCode);
        HomeAspect.logger.info(HomeAspect.logMsg + " ghStateOk : " + ghStateOk);

        if (ghStateOk == 1) {
            Member member = memberService.findById((long) memberCode);
            member.updateToHost();
        }


        mav.addObject("houseCode", houseCode);
        mav.addObject("ghStateOk", ghStateOk);

        mav.setViewName("admin/guestHouseState.tiles");

    }

    public void guestHouseStateNo(ModelAndView mav) {
        Map<String, Object> map = mav.getModelMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int houseCode = Integer.parseInt(request.getParameter("houseCode"));

        int ghStateNo = adminDao.guestHouseStateNo(houseCode);

        HomeAspect.logger.info(HomeAspect.logMsg + " ghStateNo : " + ghStateNo);

        mav.addObject("houseCode", houseCode);
        mav.addObject("ghStateNo", ghStateNo);

        mav.setViewName("admin/guestHouseState.tiles");

    }
}
