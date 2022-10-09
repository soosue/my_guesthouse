package com.java.guesthouse.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.domain.MemberDao;
import com.java.guesthouse.member.domain.MemberRepository;
import com.java.guesthouse.member.service.dto.LoginRequest;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

@Service
public class MemberServiceImp implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImp.class);

    private final MemberDao memberDao;

    private final MemberRepository memberRepository;

    public MemberServiceImp(MemberDao memberDao, MemberRepository memberRepository) {
        this.memberDao = memberDao;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest) {
        logger.info(memberSaveRequest.toString());
        Member member = memberSaveRequest.toMember();
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public int checkEmail(String email) {

        return memberRepository
                .findByEmail(email)
                .isPresent() ? 1 : 0;
    }

    @Override
    public void login(LoginRequest loginRequest, HttpServletRequest request) {
        String email = loginRequest.email();
        String password = loginRequest.password();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "은 존재하지 않는 email입니다"));

        if (member.checkPassword(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("memberLevel", member.getMemberLevel());
            session.setAttribute("email", member.getEmail());
            session.setAttribute("memberCode", member.getId());
        }
    }

    @Override
    public void kakaoLogin(ModelAndView mav) {
        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String email = request.getParameter("email");
        String memberImgPath = request.getParameter("memberImgPath");
        String memberName = request.getParameter("memberName");

        String memberLevel = "A";

        int emailChk = memberDao.kakaoEmailCheck(email);
        int check = 0;

        if (emailChk == 0) {
            check = memberDao.insertKakao(email, memberImgPath, memberName);

        } else {
            check = emailChk;
        }

        HomeAspect.logger.info(HomeAspect.logMsg + "check: " + check);

        int memberCode = memberDao.findIdByEmail(email);
        HomeAspect.logger.info(HomeAspect.logMsg + "memberCode: " + memberCode);

        mav.addObject("check", check);
        mav.addObject("memberLevel", memberLevel);
        mav.addObject("email", email);
        mav.addObject("memberCode", memberCode);

    }
}
