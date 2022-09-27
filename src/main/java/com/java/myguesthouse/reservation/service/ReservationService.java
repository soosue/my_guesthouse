package com.java.myguesthouse.reservation.service;

import com.java.myguesthouse.guesthouse.domain.Room;
import com.java.myguesthouse.guesthouse.domain.RoomRepository;
import com.java.myguesthouse.member.domain.Member;
import com.java.myguesthouse.member.domain.MemberRepository;
import com.java.myguesthouse.reservation.domain.Reservation;
import com.java.myguesthouse.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	private final MemberRepository memberRepository;

	public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, MemberRepository memberRepository) {
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
		this.memberRepository = memberRepository;
	}

	public Long reserve(ReservationSaveDto reservationSaveDto) {
		Room room = roomRepository.findById(reservationSaveDto.getRoomId())
			.orElseThrow(IllegalArgumentException::new);

		Member member = memberRepository.findById(reservationSaveDto.getMemberId())
			.orElseThrow(IllegalArgumentException::new);

		// check the validations for date, guests count

		// insert reservation
		Reservation reservation = new Reservation(room, member, reservationSaveDto.getCheckInDate(), reservationSaveDto.getCheckOutDate(), reservationSaveDto.getGuestsCount(), room.getCostPerDay());

		reservationRepository.save(reservation);
		return reservation.getId();
	}

	public ReservationDto findReservationById(Long id) {
		return ReservationDto.from(
			reservationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException())
		);
	}
}
