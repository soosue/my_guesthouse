package com.java.guesthouse.host.service;

import org.springframework.web.servlet.ModelAndView;

public interface HostService {
    void hostRegisterPage(ModelAndView mav);

    void hostRegister(ModelAndView mav);

    void hostPage(ModelAndView mav);

    void reservationView(ModelAndView mav);

    void salesView(ModelAndView mav);

    void houseManagement(ModelAndView mav);

    void hostCancel(ModelAndView mav);

    void reservationOkView(ModelAndView mav);

    void exManagement(ModelAndView mav);

    void searchDate(ModelAndView mav);

    void houseManagementView(ModelAndView mav);

    void exManagementView(ModelAndView mav);

    void exCancel(ModelAndView mav);

    void exReservationView(ModelAndView mav);

    void exReservationOkView(ModelAndView mav);

    void houseNameCheck(ModelAndView mav);


}
