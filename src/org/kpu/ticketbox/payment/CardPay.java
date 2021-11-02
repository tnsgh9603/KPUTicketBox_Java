package org.kpu.ticketbox.payment;

public class CardPay implements Pay {
	public static final double CARD_COMMISSION = 0.15;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt a = new Receipt(product, amount, name, number);
		a.totalAmount = a.subTotalAmount * (1 + CARD_COMMISSION);
		a.payMethod = "CardPay";
		return a;
	}
}