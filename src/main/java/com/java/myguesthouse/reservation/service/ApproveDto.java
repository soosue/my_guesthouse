package com.java.myguesthouse.reservation.service;

import com.java.myguesthouse.reservation.domain.Reservation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ApproveDto {
	private String cid = "TC0ONETIME";
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String pgToken;


	public ApproveDto(String pgToken, Long memberId) {
		this.pgToken = pgToken;
		this.partnerUserId = memberId + "";
	}

	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		map.add("cid", cid);
		map.add("tid", tid);
		map.add("partner_order_id", partnerOrderId);
		map.add("partner_user_id", partnerUserId);
		map.add("pg_token", pgToken);

		return map;
	}

	public Long getMemberId() {
		return Long.valueOf(partnerUserId);
	}

	public void add(Reservation reservation) {
		this.tid = reservation.getTid();
		this.partnerOrderId = reservation.getId() + "";
		this.partnerUserId = reservation.getMemberId() + "";
	}
}
