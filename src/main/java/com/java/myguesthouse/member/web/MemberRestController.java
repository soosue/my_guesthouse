package com.java.myguesthouse.member.web;

import com.java.myguesthouse.member.service.MemberSaveApplication;
import com.java.myguesthouse.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/v1/members")
@RestController
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity<Void> saveMember(MemberSaveApplication memberSaveApplication) {
        Long id = memberService.saveMember(memberSaveApplication);
        return ResponseEntity.created(URI.create("/v1/members/" + id)).build();
    }
}
