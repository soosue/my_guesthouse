package com.java.guesthouse.guestdelluna.service.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseReviewDto {

    private int reserveCode;

    private long memberCode;

    private long houseCode;

    private Date revDate;

    private String revContent;

    private int revRate;

}
