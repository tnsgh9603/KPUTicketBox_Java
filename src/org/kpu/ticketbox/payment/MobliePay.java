package org.kpu.ticketbox.payment;

public class MobliePay implements Pay {
	public static final double MOBILE_COMMISSION = 0.2;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt a = new Receipt(product, amount, name, number);
		a.totalAmount = a.subTotalAmount * (1 + MOBILE_COMMISSION);
		a.payMethod = "MobilePay";
		return a;
	}
}