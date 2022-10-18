package com.java.guesthouse.admin.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.admin.service.AdminService;
import com.java.guesthouse.admin.service.dto.MembersResponse;
import com.java.guesthouse.admin.service.dto.UpdateMemberRequest;
import com.java.guesthouse.aop.HomeAspect;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/v1/admin/members.page")
    public ModelAndView membersPage() {
        return new ModelAndView("admin/memberList.tiles");
    }

    @GetMapping("/v1/admin/members")
    public ResponseEntity<MembersResponse> getMembers(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(adminService.getMembers(pageable));
    }

    @PutMapping("/v1/admin/members/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateMember(@PathVariable Long id, @RequestBody UpdateMemberRequest request) {

        adminService.updateMember(id, request);

        return ResponseEntity.ok().build();
    }

    // 게스트하우스 관리
    @RequestMapping(value = "/admin/houseList.do", method = RequestMethod.GET)
    public ModelAndView houseList(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin guestHouseManagement");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.houseList(mav);

        return mav;
    }

    // 체험 관리
    @RequestMapping(value = "/admin/experienceList.do", method = RequestMethod.GET)
    public ModelAndView experienceList(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin experienceManagement");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.experienceList(mav);

        return mav;
    }

    @RequestMapping(value = "/admin/exState.do", method = RequestMethod.GET)
    public ModelAndView exStateOk(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin exStateOk");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.experienceStateOK(mav);

        return mav;
    }

    @RequestMapping(value = "/admin/exStateNo.do", method = RequestMethod.GET)
    public ModelAndView exStateNo(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin exStateNO");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.experienceStateNo(mav);

        return mav;
    }

    @RequestMapping(value = "/admin/state.do", method = RequestMethod.GET)
    public ModelAndView guestHouseStateOk(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin exStateOk");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.guestHouseStateOK(mav);

        return mav;
    }

    @RequestMapping(value = "/admin/stateNo.do", method = RequestMethod.GET)
    public ModelAndView guestHouseStateNo(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Admin exStateNO");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        adminService.guestHouseStateNo(mav);

        return mav;
    }
}
