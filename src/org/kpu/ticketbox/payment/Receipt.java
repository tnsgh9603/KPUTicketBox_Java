package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // 사용자 이름
	String payMethod; // 결제 수단
	String payNumber; // 결제 정보(번호)
	public String productName; // 영화 상품 이름
	double subTotalAmount; // 커미션 제외한 금액
	double totalAmount; // 커미션 포함한 금액

	// 결제하기 ( 영화상품명, 영화가격, 고객명, 결제 정보)
	public Receipt(String productName, double subTotalAmount, String client, String payNumber) {
		this.productName = productName;
		this.subTotalAmount = subTotalAmount;
		this.client = client;
		this.payNumber = payNumber;
	}

	public double get_totalAmount() {
		return this.totalAmount;
	}

	public String toString() { // 티켓 결제 내용 출력
		return "\n-----------------------\n--      Receipt      --\n-----------------------" + "\n[ Client : \t"
				+ this.client + "]\n[ Product : \t" + this.productName + "]\n[ PayMethod : \t" + this.payMethod
				+ "]\n[ Paynumber : \t" + this.payNumber + "]\n[ SubTotal : \t" + this.subTotalAmount
				+ "]\n[ Total : \t" + String.format("%.1f", this.totalAmount) + "]";
	}

	public String toBackupString() {
		return this.client + "," + this.productName + "," + this.payMethod + "," + this.payNumber + ","
				+ String.format("%.1f", this.subTotalAmount) + "," + String.format("%.1f", this.totalAmount) + "\n";
	}
}