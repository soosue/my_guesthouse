package com.java.myguesthouse.reservation.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoPay implements InitializingBean {
	@Value("${payment.kakao.app_admin_key}")
	private String appAdminKey;

	private WebClient webClient;

	public ReadyResponse ready(ReadyDto readyDto) {
		return webClient.post()
			.uri("/v1/payment/ready")
			.body(BodyInserters.fromFormData(readyDto.toMap()))
			.retrieve()
			.toEntity(ReadyResponse.class)
			.block()
			.getBody();
	}

	public ApproveResponse approve(ApproveDto approveDto) {
		return webClient.post()
			.uri("/v1/payment/approve")
			.body(BodyInserters.fromFormData(approveDto.toMap()))
			.retrieve()
			.toEntity(ApproveResponse.class)
			.block()
			.getBody();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		webClient = WebClient.builder()
			.baseUrl("https://kapi.kakao.com")
			.defaultHeader("Authorization", "KakaoAK " + appAdminKey)
			.defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
			.build();
	}

	public static class ReadyResponse {
		private String tid;
		private String tms_result;
		private String next_redirect_app_url;
		private String next_redirect_mobile_url;
		private String next_redirect_pc_url;
		private String android_app_scheme;
		private String ios_app_scheme;
		private String created_at;

		public String getTid() {
			return tid;
		}

		public void setTid(String tid) {
			this.tid = tid;
		}

		public String getTms_result() {
			return tms_result;
		}

		public void setTms_result(String tms_result) {
			this.tms_result = tms_result;
		}

		public String getNext_redirect_app_url() {
			return next_redirect_app_url;
		}

		public void setNext_redirect_app_url(String next_redirect_app_url) {
			this.next_redirect_app_url = next_redirect_app_url;
		}

		public String getNext_redirect_mobile_url() {
			return next_redirect_mobile_url;
		}

		public void setNext_redirect_mobile_url(String next_redirect_mobile_url) {
			this.next_redirect_mobile_url = next_redirect_mobile_url;
		}

		public String getNext_redirect_pc_url() {
			return next_redirect_pc_url;
		}

		public void setNext_redirect_pc_url(String next_redirect_pc_url) {
			this.next_redirect_pc_url = next_redirect_pc_url;
		}

		public String getAndroid_app_scheme() {
			return android_app_scheme;
		}

		public void setAndroid_app_scheme(String android_app_scheme) {
			this.android_app_scheme = android_app_scheme;
		}

		public String getIos_app_scheme() {
			return ios_app_scheme;
		}

		public void setIos_app_scheme(String ios_app_scheme) {
			this.ios_app_scheme = ios_app_scheme;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}
	}
}
