package com.java.myguesthouse.reservation.service;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ReadyDto {
	private String cid = "TC0ONETIME";
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;

	private Integer quantity;
	private Integer totalAmount;
	private Integer taxFreeAmount;

	private String approvalUrl = "http://localhost:8080/v1/reservations/approve";
	private String cancelUrl = "http://localhost:8080/v1/reservations/cancel";
	private String failUrl = "http://localhost:8080/v1/reservations/fail";

	public ReadyDto(String partnerOrderId, String partnerUserId,
					String itemName, Integer quantity, Integer totalAmount, Integer taxFreeAmount) {
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.taxFreeAmount = taxFreeAmount;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPartnerOrderId() {
		return partnerOrderId;
	}

	public void setPartnerOrderId(String partnerOrderId) {
		this.partnerOrderId = partnerOrderId;
	}

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTaxFreeAmount() {
		return taxFreeAmount;
	}

	public void setTaxFreeAmount(Integer taxFreeAmount) {
		this.taxFreeAmount = taxFreeAmount;
	}

	public String getApprovalUrl() {
		return approvalUrl;
	}

	public void setApprovalUrl(String approvalUrl) {
		this.approvalUrl = approvalUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public String getFailUrl() {
		return failUrl;
	}

	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
	}

	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		map.add("cid", cid);
		map.add("partner_order_id", partnerOrderId);
		map.add("partner_user_id", partnerUserId);
		map.add("item_name", itemName);
		map.add("quantity", quantity + "");
		map.add("total_amount", totalAmount + "");
		map.add("tax_free_amount", taxFreeAmount + "");
		map.add("approval_url", approvalUrl);
		map.add("cancel_url", cancelUrl);
		map.add("fail_url", failUrl);
		return map;
	}
}
