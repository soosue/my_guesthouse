package com.java.guesthouse.guestdelluna.service.dto;

public class HouseZzimDto {

    private int memberCode;
    private int houseCode;

    public HouseZzimDto() {
    }

    public HouseZzimDto(int memberCode, int houseCode) {

        this.memberCode = memberCode;
        this.houseCode = houseCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(int houseCode) {
        this.houseCode = houseCode;
    }

    public String toString() {
        return "HouseZzimDto [memberCode=" + memberCode + ", houseCode=" + houseCode + "]";
    }

}
