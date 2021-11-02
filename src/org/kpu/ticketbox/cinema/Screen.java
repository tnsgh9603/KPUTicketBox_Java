package org.kpu.ticketbox.cinema;

import java.util.*;
import org.kpu.ticketbox.util.*;
import org.kpu.ticketbox.payment.*;

public abstract class Screen {
	protected int nTicketPrice; // 티켓 가격
	protected int nRowMax; // 좌석 행 최대 값
	protected int nColMax; // 좌석 열 최대 값
	private int nCurrentReservedId = 100;
	protected String strMovieName; // 상영중인 영화 제목
	protected String strMovieStory; // 상영중인 영화 줄거리
	protected MovieTicket[][] seatArray; // 좌석 2차원 배열
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();

	Scanner scan = new Scanner(System.in);

	public abstract void showMovieInfo(); // 영화 정보 보여주기

	Screen(String name, String story, int price, int row, int col) { // 생성자
		this.strMovieName = name;
		this.strMovieStory = story;
		this.nTicketPrice = price;
		this.nRowMax = row;
		this.nColMax = col;
		seatArray = new MovieTicket[nRowMax + 1][nColMax + 1];
		for (int i = 1; i <= nRowMax; i++) {
			for (int j = 1; j <= nColMax; j++) {
				seatArray[i][j] = new MovieTicket();
			}
		}
	}

	public void showScreenMenu() { // 상영관 메뉴 보여주기
		System.out.println("");
		System.out.println("------------------------");
		System.out.println("영화 메뉴 - " + this.strMovieName);
		System.out.println("------------------------");
		System.out.println("1. 상영 영화 정보");
		System.out.println("2. 좌석 예약 현황");
		System.out.println("3. 좌석 예약 하기");
		System.out.println("4. 좌석 변경 하기");
		System.out.println("5. 좌석 결제 하기");
		System.out.println("6. 티켓 출력 하기");
		System.out.println("7. 메인 메뉴 이동");
		System.out.println("");
	}

	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
		System.out.println("");
		System.out.println("\t[ 좌석 예약 현황 ]");
		System.out.print("\t");
		for (int i = 1; i <= this.nRowMax; i++) {
			System.out.print("[" + i + "]");
		}
		System.out.println("");
		for (int i = 1; i <= this.nRowMax; i++) {
			System.out.print("[" + i + "]" + "\t");
			for (int k = 1; k <= this.nColMax; k++) {
				System.out.print("[" + seatArray[i][k].getcStatus() + "]");
			}
			System.out.println("");
		}
	}

	public void reserveticket() {
		try {
			System.out.println("");
			System.out.println("[ 좌석 예약 ]");
			System.out.print("좌석 행 번호 입력(1 - " + this.nRowMax + ") : ");
			int row = scan.nextInt();
			if (row < 1 || row > this.nRowMax) {
				System.out.println("\n잘못된 입력입니다!");
				return;
			}
			System.out.print("좌석 행 번호 입력(1 - " + this.nColMax + ") : ");
			int col = scan.nextInt();
			if (col < 1 || col > this.nRowMax) {
				System.out.println("\n잘못된 입력입니다!");
				return;
			}
			if (seatArray[row][col].getcStatus() == 'R' || seatArray[row][col].getcStatus() == '$') {
				System.out.println("");
				System.out.println("해당 좌석을 예약할 수 없습니다!");
			} 
			else {
				this.seatArray[row][col].setcStatus('R');
				this.seatArray[row][col].setSeat(row, col);
				this.seatArray[row][col].setnReservedId(this.nCurrentReservedId);
				System.out.println("");
				System.out.println("행[" + row + "] 열[" + col + "] " + this.nCurrentReservedId++ + " 예약 번호로 접수되었습니다.");
			}
		}
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("다시 입력해주세요!");
		}
	}

	private MovieTicket checkReservedId(int id) {
		for (int i = 1; i <= this.nRowMax; i++) {
			for (int k = 1; k <= this.nColMax; k++) {
				if (this.seatArray[i][k].get_reserved_ID() == id) {
					return seatArray[i][k];
				}
			}
		}
		return null;
	}

	public void changeTicket() {
		try {
			System.out.println("");
			System.out.println("[ 좌석 변경 ]");
			System.out.print("좌석 예약 번호 입력 : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n예약번호는 100번부터 시작합니다!");
				return;
			}
			MovieTicket ticket = checkReservedId(reserved_ID);
			boolean flag = false;
			int a = 0, b = 0;
			for (int i = 1; i <= this.nRowMax; i++) {
				if (flag) {
					break;
				} 
				else {
					for (int k = 1; k <= this.nColMax; k++) {
						if (this.seatArray[i][k].get_reserved_ID() == reserved_ID) {
							flag = true;
							a = i;
							b = k;
							break;
						}
					}
				}
			}
			if(this.seatArray[a][b].getcStatus() == '$'){
				System.out.println("\n결제된 좌석은 변경할 수 없습니다!");
				return;
			}
			if (ticket != null) {
				System.out.print("좌석 행 번호 입력(1 - " + this.nRowMax + ") : ");
				int row = scan.nextInt();
				if (row < 1 || row > this.nRowMax) {
					System.out.println("\n잘못된 입력입니다!");
					return;
				}
				System.out.print("좌석 행 번호 입력(1 - " + this.nColMax + ") : ");
				int col = scan.nextInt();
				if (col < 1 || col > this.nRowMax) {
					System.out.println("\n잘못된 입력입니다!");
					return;
				}
				if (this.seatArray[row][col].getcStatus() == 'R' || this.seatArray[row][col].getcStatus() == '$') {
					System.out.println("\n해당 좌석으로는 변경이 불가능합니다!");
				} 
				else {
					this.seatArray[a][b].setcStatus('-');
					this.seatArray[row][col].setcStatus('R');
					this.seatArray[row][col].setnReservedId(this.seatArray[a][b].get_reserved_ID());
					System.out.println("예약번호 " + this.seatArray[a][b].get_reserved_ID() + " 행[" + row + "] 열[" + col
							+ "] 좌석으로 변경되었습니다.");
				}
			} 
			else {
				System.out.println("\n좌석 예약번호가 올바르지 않습니다!");
			}
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("다시 입력해주세요!");
		}
	}

	public void payment() {
		try {
			System.out.println("");
			System.out.println("[ 좌석 예약 결제 ]");
			System.out.print("예약 번호 입력 : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n예약번호는 100번부터 시작합니다!");
				return;
			}
			scan.nextLine();
			boolean flag = false;
			int a = 0, b = 0;
			for (int i = 1; i <= this.nRowMax; i++) {
				if (flag) {
					break;
				} 
				else {
					for (int k = 1; k <= this.nColMax; k++) {
						if (this.seatArray[i][k].get_reserved_ID() == reserved_ID) {
							flag = true;
							a = i;
							b = k;
							break;
						}
					}
				}
			}
			if (!flag) {
				System.out.println("\n예약 번호가 올바르지 않습니다!");
				return;
			}
			if (this.seatArray[a][b].getcStatus() == '$') {
				System.out.println("\n이미 결제 되었습니다!");
				return;
			}
			System.out.println("");
			System.out.println("-------------------");
			System.out.println("     결제 방식 선택            ");
			System.out.println("-------------------");
			System.out.println("1. 은행 이체");
			System.out.println("2. 카드 결제");
			System.out.println("3. 모바일 결제");
			System.out.print("결제 방식 입력(1~3) : ");
			int howtopay = scan.nextInt();
			scan.nextLine();
			System.out.println("");
			if (howtopay == 1) {
				System.out.println("[ 은행 이체 ]");
				System.out.print("이름 입력 : ");
				String name = scan.nextLine();
				System.out.print("은행 번호 입력(12자리 수) : ");
				String bank_number = scan.nextLine();
				BankTransfer bank = new BankTransfer();
				Receipt receipt = bank.charge(this.strMovieName, (double) this.nTicketPrice, name, bank_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("은행 결제가 완료되었습니다. : " + String.format("%.1f", receipt.get_totalAmount()) + "원");
			}
			else if (howtopay == 2) {
				System.out.println("[ 카드 결제 ]");
				System.out.print("이름 입력 : ");
				String name = scan.nextLine();
				System.out.print("카드 번호 입력(12자리 수) : ");
				String card_number = scan.nextLine();
				CardPay card = new CardPay();
				Receipt receipt = card.charge(this.strMovieName, (double) this.nTicketPrice, name, card_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("카드 결제가 완료되었습니다. : " + String.format("%.1f", receipt.get_totalAmount()) + "원");
			} 
			else if (howtopay == 3) {
				System.out.println("[ 모바일 이체 ]");
				System.out.print("이름 입력 : ");
				String name = scan.nextLine();
				System.out.print("핸드폰 번호 입력(11자리 수) : ");
				String phone_number = scan.nextLine();
				MobliePay mobile = new MobliePay();
				Receipt receipt = mobile.charge(this.strMovieName, (double) this.nTicketPrice, name, phone_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("모바일 결제가 완료되었습니다. : " + String.format("%.1f", receipt.get_totalAmount()) + "원");
			}
			else {
				System.out.println("잘못된 입력입니다.");
			}
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("다시 입력해주세요!");
		}
	}

	public void printTicket() {
		try {
			System.out.println("");
			System.out.println("[ 좌석 티켓 출력 ]");
			System.out.print("예약 번호 입력 : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n예약번호는 100번부터 시작합니다!");
				return;
			}
			int a = 0, b = 0;
			boolean flag = false;
			for (int i = 1; i <= this.nRowMax; i++) {
				if (flag) {
					break;
				}
				else {
					for (int k = 1; k <= this.nColMax; k++) {
						if (this.seatArray[i][k].get_reserved_ID() == reserved_ID) {
							flag = true;
							a = i;
							b = k;
							break;
						}
					}
				}
			}
			if (!flag) {
				System.out.println("\n예약 번호가 올바르지 않습니다!");
				return;
			}
			if (this.seatArray[a][b].getcStatus() == 'R') {
				System.out.println("\n아직 결제되지 않았습니다!");
				return;
			}
			System.out.println(receiptMap.get(reserved_ID).toString());
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("다시 입력해주세요!");
		}
	}

	public double admin_mode() {
		return Statistics.sum(receiptMap);
	}

	public void create_text(String filename) {
		BackupWriter back_up = new BackupWriter();
		back_up.backupFile(filename, receiptMap);
	}
}