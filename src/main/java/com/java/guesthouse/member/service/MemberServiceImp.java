package com.java.guesthouse.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.member.dao.MemberDao;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.domain.MemberRepository;
import com.java.guesthouse.member.service.dto.MemberDto;
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
    public void memberLoginOk(ModelAndView mav) {

        Map<String, Object> map = mav.getModelMap();

        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HomeAspect.logger.info(HomeAspect.logMsg + "입력한 email: " + email + "\t\t" + "입력한 password: " + password);

        MemberDto memberDto = memberDao.memberSel(email, password);
        // HomeAspect.logger.info(HomeAspect.logMsg +"memberDto: "+
        // memberDto.toString());

        if (memberDto != null) {

            int memberCode = memberDto.getMemberCode();
            String memberLevel = memberDto.getMemberLevel();
            HomeAspect.logger.info(HomeAspect.logMsg + memberDto.toString());

            HomeAspect.logger.info(
                    HomeAspect.logMsg + "회원등급 (회원이 아닐경우 null값): " + memberLevel + "		memberCode:" + memberCode);

            if (memberLevel != null) {
                mav.addObject("memberLevel", memberLevel);
                mav.addObject("email", email);
                mav.addObject("memberCode", memberCode);
            }

            // memberDto가 null일 경우
        } else {
            String memberLevel = null;
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

        int emailChk = memberDao.kakaoEmailChk(email);
        int check = 0;

        if (emailChk == 0) {
            check = memberDao.inserKakao(email, memberImgPath, memberName);

        } else {
            check = emailChk;
        }

        HomeAspect.logger.info(HomeAspect.logMsg + "check: " + check);

        int memberCode = memberDao.getMemberCode(email);
        HomeAspect.logger.info(HomeAspect.logMsg + "memberCode: " + memberCode);

        mav.addObject("check", check);
        mav.addObject("memberLevel", memberLevel);
        mav.addObject("email", email);
        mav.addObject("memberCode", memberCode);

    }
}
