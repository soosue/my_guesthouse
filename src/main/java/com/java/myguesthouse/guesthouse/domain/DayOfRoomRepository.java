package com.java.myguesthouse.guesthouse.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayOfRoomRepository extends JpaRepository<DayOfRoom, Long> {
	List<DayOfRoom> findByRoomAndDateBetween(Room room, LocalDate checkInDate, LocalDate checkOutDate);
}
