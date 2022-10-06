package com.java.guesthouse.admin.service;

import org.springframework.web.servlet.ModelAndView;

public interface AdminService {
    /* 회원 관리*/
    void memberList(ModelAndView mav);

    void memberRead(ModelAndView mav);

    void memberUpdateOk(ModelAndView mav);

    /* 게스트하우스 관리*/
    void houseList(ModelAndView mav);

    /* 체험 관리*/
    void experienceList(ModelAndView mav);

    void experienceStateOK(ModelAndView mav);

    void experienceStateNo(ModelAndView mav);

    void guestHouseStateOK(ModelAndView mav);

    void guestHouseStateNo(ModelAndView mav);


}
