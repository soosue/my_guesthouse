package com.java.myguesthouse.reservation.web;

import com.java.myguesthouse.reservation.service.PaymentService;
import com.java.myguesthouse.reservation.service.ReservationDto;
import com.java.myguesthouse.reservation.service.ReservationSaveDto;
import com.java.myguesthouse.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/reservations")
@RestController
public class ReservationController {
	private final ReservationService reservationService;
	private final PaymentService paymentService;

	public ReservationController(ReservationService reservationService, PaymentService paymentService) {
		this.reservationService = reservationService;
		this.paymentService = paymentService;
	}

	@PostMapping("")
	public ResponseEntity<Void> reserve(ReservationSaveDto reservationSaveDto) {
		Long id = reservationService.reserve(reservationSaveDto);
		ReservationDto reservationDto = reservationService.findReservationById(id);

		String redirectUrl = paymentService.payByKakaoPay(reservationDto);
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
	}
}
