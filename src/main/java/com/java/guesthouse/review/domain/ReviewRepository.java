package com.java.guesthouse.review.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.guesthouse.guestreserve.dto.ReviewDto;

public interface ReviewRepository extends JpaRepository<Review, Long>  {

    @Query(value = "select r.reserveCode, r.memberCode, r.revDate, r.revContent, r.revRate, m.email" +
            " from review r" +
            " join reservation rs on r.reserveCode = rs.reserveCode" +
            " join house h on rs.houseCode = h.houseCode" +
            " join member m on r.memberCode = m.id" +
            " where h.houseCode = :guestHouseId",
    nativeQuery = true)
    List<ReviewDto> findByGuestHouseId(@Param("guestHouseId") Long guestHouseId);

}
