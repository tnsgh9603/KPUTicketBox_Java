package org.kpu.ticketbox.payment;

public class BankTransfer implements Pay {
	public static final double BANK_TRANSFER_COMMISSION = 0.1;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt a = new Receipt(product, amount, name, number);
		a.totalAmount = a.subTotalAmount * (1 + BANK_TRANSFER_COMMISSION);
		a.payMethod = "BankTransfer";
		return a;
	}
}