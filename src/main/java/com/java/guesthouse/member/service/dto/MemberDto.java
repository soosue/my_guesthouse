package com.java.guesthouse.member.service.dto;

import java.time.LocalDateTime;

import com.java.guesthouse.member.domain.Member;

public class MemberDto {
    private int memberCode;
    private String memberName;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime regDate;
    private int point;
    private String memberLevel;
    private String memberImgName;
    private String memberImgPath;
    private long memberImgSize;
    private String memberInfo;

    public MemberDto() {
    }

    public MemberDto(int memberCode, String memberName, String email, String password,
                     String phone, LocalDateTime regDate,
                     int point, String memberLevel,
                     String memberImgName, String memberImgPath, long memberImgSize, String memberInfo) {

        this.memberCode = memberCode;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.regDate = regDate;
        this.point = point;
        this.memberLevel = memberLevel;
        this.memberImgName = memberImgName;
        this.memberImgPath = memberImgPath;
        this.memberImgSize = memberImgSize;
        this.memberInfo = memberInfo;
    }

    public static MemberDto from(Member member) {
        return new MemberDto(Integer.valueOf(member.getId() + ""),
                member.getName(), member.getEmail(), member.getPassword(),
                member.getPhoneNumber(), member.getCreatedAt(),
                Integer.valueOf(member.getPoint() + ""), member.getMemberLevel(),
                member.getImageName(), member.getImagePath(), member.getImageSize(), member.getInfo());
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberImgName() {
        return memberImgName;
    }

    public void setMemberImgName(String memberImgName) {
        this.memberImgName = memberImgName;
    }

    public String getMemberImgPath() {
        return memberImgPath;
    }

    public void setMemberImgPath(String memberImgPath) {
        this.memberImgPath = memberImgPath;
    }

    public long getMemberImgSize() {
        return memberImgSize;
    }

    public void setMemberImgSize(long memberImgSize) {
        this.memberImgSize = memberImgSize;
    }

    public String getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(String memberInfo) {
        this.memberInfo = memberInfo;
    }

    @Override
    public String toString() {
        return "MemberDto [memberCode=" + memberCode + ", memberName=" + memberName + ", email=" + email + ", password="
                + password + ", phone=" + phone + ", regDate=" + regDate + ", point=" + point + ", memberLevel="
                + memberLevel + ", memberImgName=" + memberImgName + ", memberImgPath=" + memberImgPath
                + ", memberImgSize=" + memberImgSize + ", memberInfo=" + memberInfo + "]";
    }


}
