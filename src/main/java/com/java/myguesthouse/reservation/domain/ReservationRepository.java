package com.java.myguesthouse.reservation.domain;

import com.java.myguesthouse.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByMemberAndState(Member member, Reservation.State state);
}
