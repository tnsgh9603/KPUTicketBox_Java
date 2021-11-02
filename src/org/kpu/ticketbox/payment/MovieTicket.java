package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	private int nRow; // 좌석 행
	private int nCol; // 좌석 열
	private char cStatus; // 좌석 상태 - EMPTY, RESERVED, PAY_COMPLETION
	private int nReservedId; // 예약 번호

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

	public void setSeat(int row, int col) { // 좌석번호저장
		this.nRow = row;
		this.nCol = col;
	}

	public void setnReservedId(int id) { // 예약번호저장
		this.nReservedId = id;
	}

	public void getnReservedId() { // 예약번호 읽기
		System.out.println(this.nReservedId);
	}

	public int get_reserved_ID() {
		return this.nReservedId;
	}
}