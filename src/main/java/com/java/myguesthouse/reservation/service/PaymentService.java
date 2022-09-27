package com.java.myguesthouse.reservation.service;

import com.java.myguesthouse.guesthouse.domain.DayOfRoom;
import com.java.myguesthouse.guesthouse.domain.DayOfRoomRepository;
import com.java.myguesthouse.member.domain.Member;
import com.java.myguesthouse.member.domain.MemberRepository;
import com.java.myguesthouse.reservation.domain.Reservation;
import com.java.myguesthouse.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PaymentService {
	private final KakaoPay kakaoPay;
	private final ReservationRepository reservationRepository;
	private final MemberRepository memberRepository;
	private final DayOfRoomRepository dayOfRoomRepository;

	public PaymentService(KakaoPay kakaoPay, ReservationRepository reservationRepository, MemberRepository memberRepository, DayOfRoomRepository dayOfRoomRepository) {
		this.kakaoPay = kakaoPay;
		this.reservationRepository = reservationRepository;
		this.memberRepository = memberRepository;
		this.dayOfRoomRepository = dayOfRoomRepository;
	}

	public String payByKakaoPay(ReservationDto reservationDto) {
		KakaoPay.ReadyResponse readyResponse = kakaoPay.ready(new ReadyDto(
			reservationDto.getId() + "",
			reservationDto.getMemberId() + "",
			reservationDto.getName(), 1, reservationDto.getTotalAmount(), 0));

		// reservation과 tid를 연결해주어야한다.
		Reservation reservation = reservationRepository.findById(reservationDto.getId())
			.orElseThrow(() -> new IllegalArgumentException());

		reservation.updateTid(readyResponse.getTid());


		return readyResponse.getNext_redirect_pc_url();
	}

	public void approve(ApproveDto approveDto) {
		// TODO lock the room
		Member member = memberRepository.findById(approveDto.getMemberId())
			.orElseThrow(IllegalArgumentException::new);
		List<Reservation> reservations = reservationRepository.findByMemberAndState(member, Reservation.State.TEMPORAL);

		Reservation reservation = reservations.get(0);
		approveDto.add(reservation);

		ApproveResponse approveResponse = kakaoPay.approve(approveDto);
		reservation.reserved();

		// 해당 예약 방의 DayOfRoom.remainCount-- 해주어야함.
		List<DayOfRoom> dayOfRooms = dayOfRoomRepository.findByRoomAndDateBetween(reservation.getRoom(), reservation.getCheckInDate(), reservation.getCheckOutDate());

		dayOfRooms.forEach(DayOfRoom::countRemainCount);
	}
}
