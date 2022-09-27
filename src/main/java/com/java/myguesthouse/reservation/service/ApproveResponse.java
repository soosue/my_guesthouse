package com.java.myguesthouse.reservation.service;

public class ApproveResponse {
	private String aid;
	private String tid;
	private String cid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	private String item_name;
	private Integer quantity;
	private Amount amount;
	private String created_at;
	private String approved_at;

	static class Amount {
		private Integer total;
		private Integer tax_free;
		private Integer vat;
		private Integer point;
		private Integer discount;
		private Integer green_deposit;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getTax_free() {
			return tax_free;
		}

		public void setTax_free(Integer tax_free) {
			this.tax_free = tax_free;
		}

		public Integer getVat() {
			return vat;
		}

		public void setVat(Integer vat) {
			this.vat = vat;
		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}

		public Integer getDiscount() {
			return discount;
		}

		public void setDiscount(Integer discount) {
			this.discount = discount;
		}

		public Integer getGreen_deposit() {
			return green_deposit;
		}

		public void setGreen_deposit(Integer green_deposit) {
			this.green_deposit = green_deposit;
		}
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPartner_order_id() {
		return partner_order_id;
	}

	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}

	public String getPartner_user_id() {
		return partner_user_id;
	}

	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}

	public String getPayment_method_type() {
		return payment_method_type;
	}

	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getApproved_at() {
		return approved_at;
	}

	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
}
