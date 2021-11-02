package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // ����� �̸�
	String payMethod; // ���� ����
	String payNumber; // ���� ����(��ȣ)
	public String productName; // ��ȭ ��ǰ �̸�
	double subTotalAmount; // Ŀ�̼� ������ �ݾ�
	double totalAmount; // Ŀ�̼� ������ �ݾ�

	// �����ϱ� ( ��ȭ��ǰ��, ��ȭ����, ����, ���� ����)
	public Receipt(String productName, double subTotalAmount, String client, String payNumber) {
		this.productName = productName;
		this.subTotalAmount = subTotalAmount;
		this.client = client;
		this.payNumber = payNumber;
	}

	public double get_totalAmount() {
		return this.totalAmount;
	}

	public String toString() { // Ƽ�� ���� ���� ���
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