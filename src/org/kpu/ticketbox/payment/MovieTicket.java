package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	private int nRow; // �¼� ��
	private int nCol; // �¼� ��
	private char cStatus; // �¼� ���� - EMPTY, RESERVED, PAY_COMPLETION
	private int nReservedId; // ���� ��ȣ

	public MovieTicket() {
		this.cStatus = SEAT_EMPTY_MARK;
	}

	public int getnRow() {
		return this.nRow;
	}

	public int getnCol() {
		return this.nCol;
	}

	public char getcStatus() {
		return this.cStatus;
	}

	public void setcStatus(char cStatus) {
		this.cStatus = cStatus;
	}

	public void setSeat(int row, int col) { // �¼���ȣ����
		this.nRow = row;
		this.nCol = col;
	}

	public void setnReservedId(int id) { // �����ȣ����
		this.nReservedId = id;
	}

	public void getnReservedId() { // �����ȣ �б�
		System.out.println(this.nReservedId);
	}

	public int get_reserved_ID() {
		return this.nReservedId;
	}
}