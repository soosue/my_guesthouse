package com.java.myguesthouse.reservation.service;

import com.java.myguesthouse.reservation.domain.Reservation;
import com.java.myguesthouse.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public Long reserve(ReservationSaveDto reservationSaveDto) {
		// TODO lock the guest house

		// check the validations for date, guests count

		// insert reservation
		Reservation reservation = new Reservation();

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
