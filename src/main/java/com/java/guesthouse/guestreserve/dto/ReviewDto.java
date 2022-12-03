package com.java.guesthouse.guestreserve.dto;

import java.util.Date;

public interface ReviewDto {
    int getReserveCode();

    int getMemberCode();

    Date getRevDate();

    String getRevContent();

    int getRevRate();

    String getEmail();
}
