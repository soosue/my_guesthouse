package com.java.myguesthouse.reservation.web;

import com.java.myguesthouse.reservation.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Void> reserve(@RequestBody ReservationSaveDto reservationSaveDto) {
		Long id = reservationService.reserve(reservationSaveDto);
		ReservationDto reservationDto = reservationService.findReservationById(id);

		String redirectUrl = paymentService.payByKakaoPay(reservationDto);
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
	}

	@GetMapping("/approve")
	public ResponseEntity<Void> approve(@RequestParam(name = "pg_token") String pgToken) {
		// 현재 로그인 된 사용자의 ID로 조회
		paymentService.approve(new ApproveDto(pgToken, 1L));

		return ResponseEntity.ok().build();
	}

	@GetMapping("/cancel")
	public ResponseEntity<Void> cancel() {
		return null;
	}

	@GetMapping("/fail")
	public ResponseEntity<Void> fail() {
		return null;
	}

}
