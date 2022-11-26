package com.java.guesthouse.review.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void jpa_interface_projections_test() {
        Long guestHouseId = 42L;

        assertDoesNotThrow(() -> {
            reviewRepository.findByGuestHouseId(guestHouseId);
        });
    }
}
