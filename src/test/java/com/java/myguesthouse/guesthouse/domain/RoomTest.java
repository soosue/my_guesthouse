package com.java.myguesthouse.guesthouse.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RoomTest {
    @Test
    void 룸_생성() {
        assertDoesNotThrow(() ->
                new Room(LocalDate.now(), 5)
        );
    }
}
