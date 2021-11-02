package org.kpu.ticketbox.cinema;

import java.util.*;
import org.kpu.ticketbox.util.*;
import org.kpu.ticketbox.payment.*;

public abstract class Screen {
	protected int nTicketPrice; // Ƽ�� ����
	protected int nRowMax; // �¼� �� �ִ� ��
	protected int nColMax; // �¼� �� �ִ� ��
	private int nCurrentReservedId = 100;
	protected String strMovieName; // ������ ��ȭ ����
	protected String strMovieStory; // ������ ��ȭ �ٰŸ�
	protected MovieTicket[][] seatArray; // �¼� 2���� �迭
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();

	Scanner scan = new Scanner(System.in);

	public abstract void showMovieInfo(); // ��ȭ ���� �����ֱ�

	Screen(String name, String story, int price, int row, int col) { // ������
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

	public void showScreenMenu() { // �󿵰� �޴� �����ֱ�
		System.out.println("");
		System.out.println("------------------------");
		System.out.println("��ȭ �޴� - " + this.strMovieName);
		System.out.println("------------------------");
		System.out.println("1. �� ��ȭ ����");
		System.out.println("2. �¼� ���� ��Ȳ");
		System.out.println("3. �¼� ���� �ϱ�");
		System.out.println("4. �¼� ���� �ϱ�");
		System.out.println("5. �¼� ���� �ϱ�");
		System.out.println("6. Ƽ�� ��� �ϱ�");
		System.out.println("7. ���� �޴� �̵�");
		System.out.println("");
	}

	public void showSeatMap() { // �󿵰� �¼� ���� ��Ȳ �����ֱ�
		System.out.println("");
		System.out.println("\t[ �¼� ���� ��Ȳ ]");
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
			System.out.println("[ �¼� ���� ]");
			System.out.print("�¼� �� ��ȣ �Է�(1 - " + this.nRowMax + ") : ");
			int row = scan.nextInt();
			if (row < 1 || row > this.nRowMax) {
				System.out.println("\n�߸��� �Է��Դϴ�!");
				return;
			}
			System.out.print("�¼� �� ��ȣ �Է�(1 - " + this.nColMax + ") : ");
			int col = scan.nextInt();
			if (col < 1 || col > this.nRowMax) {
				System.out.println("\n�߸��� �Է��Դϴ�!");
				return;
			}
			if (seatArray[row][col].getcStatus() == 'R' || seatArray[row][col].getcStatus() == '$') {
				System.out.println("");
				System.out.println("�ش� �¼��� ������ �� �����ϴ�!");
			} 
			else {
				this.seatArray[row][col].setcStatus('R');
				this.seatArray[row][col].setSeat(row, col);
				this.seatArray[row][col].setnReservedId(this.nCurrentReservedId);
				System.out.println("");
				System.out.println("��[" + row + "] ��[" + col + "] " + this.nCurrentReservedId++ + " ���� ��ȣ�� �����Ǿ����ϴ�.");
			}
		}
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("�ٽ� �Է����ּ���!");
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
			System.out.println("[ �¼� ���� ]");
			System.out.print("�¼� ���� ��ȣ �Է� : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n�����ȣ�� 100������ �����մϴ�!");
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
				System.out.println("\n������ �¼��� ������ �� �����ϴ�!");
				return;
			}
			if (ticket != null) {
				System.out.print("�¼� �� ��ȣ �Է�(1 - " + this.nRowMax + ") : ");
				int row = scan.nextInt();
				if (row < 1 || row > this.nRowMax) {
					System.out.println("\n�߸��� �Է��Դϴ�!");
					return;
				}
				System.out.print("�¼� �� ��ȣ �Է�(1 - " + this.nColMax + ") : ");
				int col = scan.nextInt();
				if (col < 1 || col > this.nRowMax) {
					System.out.println("\n�߸��� �Է��Դϴ�!");
					return;
				}
				if (this.seatArray[row][col].getcStatus() == 'R' || this.seatArray[row][col].getcStatus() == '$') {
					System.out.println("\n�ش� �¼����δ� ������ �Ұ����մϴ�!");
				} 
				else {
					this.seatArray[a][b].setcStatus('-');
					this.seatArray[row][col].setcStatus('R');
					this.seatArray[row][col].setnReservedId(this.seatArray[a][b].get_reserved_ID());
					System.out.println("�����ȣ " + this.seatArray[a][b].get_reserved_ID() + " ��[" + row + "] ��[" + col
							+ "] �¼����� ����Ǿ����ϴ�.");
				}
			} 
			else {
				System.out.println("\n�¼� �����ȣ�� �ùٸ��� �ʽ��ϴ�!");
			}
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("�ٽ� �Է����ּ���!");
		}
	}

	public void payment() {
		try {
			System.out.println("");
			System.out.println("[ �¼� ���� ���� ]");
			System.out.print("���� ��ȣ �Է� : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n�����ȣ�� 100������ �����մϴ�!");
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
				System.out.println("\n���� ��ȣ�� �ùٸ��� �ʽ��ϴ�!");
				return;
			}
			if (this.seatArray[a][b].getcStatus() == '$') {
				System.out.println("\n�̹� ���� �Ǿ����ϴ�!");
				return;
			}
			System.out.println("");
			System.out.println("-------------------");
			System.out.println("     ���� ��� ����            ");
			System.out.println("-------------------");
			System.out.println("1. ���� ��ü");
			System.out.println("2. ī�� ����");
			System.out.println("3. ����� ����");
			System.out.print("���� ��� �Է�(1~3) : ");
			int howtopay = scan.nextInt();
			scan.nextLine();
			System.out.println("");
			if (howtopay == 1) {
				System.out.println("[ ���� ��ü ]");
				System.out.print("�̸� �Է� : ");
				String name = scan.nextLine();
				System.out.print("���� ��ȣ �Է�(12�ڸ� ��) : ");
				String bank_number = scan.nextLine();
				BankTransfer bank = new BankTransfer();
				Receipt receipt = bank.charge(this.strMovieName, (double) this.nTicketPrice, name, bank_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("���� ������ �Ϸ�Ǿ����ϴ�. : " + String.format("%.1f", receipt.get_totalAmount()) + "��");
			}
			else if (howtopay == 2) {
				System.out.println("[ ī�� ���� ]");
				System.out.print("�̸� �Է� : ");
				String name = scan.nextLine();
				System.out.print("ī�� ��ȣ �Է�(12�ڸ� ��) : ");
				String card_number = scan.nextLine();
				CardPay card = new CardPay();
				Receipt receipt = card.charge(this.strMovieName, (double) this.nTicketPrice, name, card_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("ī�� ������ �Ϸ�Ǿ����ϴ�. : " + String.format("%.1f", receipt.get_totalAmount()) + "��");
			} 
			else if (howtopay == 3) {
				System.out.println("[ ����� ��ü ]");
				System.out.print("�̸� �Է� : ");
				String name = scan.nextLine();
				System.out.print("�ڵ��� ��ȣ �Է�(11�ڸ� ��) : ");
				String phone_number = scan.nextLine();
				MobliePay mobile = new MobliePay();
				Receipt receipt = mobile.charge(this.strMovieName, (double) this.nTicketPrice, name, phone_number);
				this.receiptMap.put(this.seatArray[a][b].get_reserved_ID(), receipt);
				this.seatArray[a][b].setcStatus('$');
				System.out.println("����� ������ �Ϸ�Ǿ����ϴ�. : " + String.format("%.1f", receipt.get_totalAmount()) + "��");
			}
			else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("�ٽ� �Է����ּ���!");
		}
	}

	public void printTicket() {
		try {
			System.out.println("");
			System.out.println("[ �¼� Ƽ�� ��� ]");
			System.out.print("���� ��ȣ �Է� : ");
			int reserved_ID = scan.nextInt();
			if (reserved_ID < 100) {
				System.out.println("\n�����ȣ�� 100������ �����մϴ�!");
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
				System.out.println("\n���� ��ȣ�� �ùٸ��� �ʽ��ϴ�!");
				return;
			}
			if (this.seatArray[a][b].getcStatus() == 'R') {
				System.out.println("\n���� �������� �ʾҽ��ϴ�!");
				return;
			}
			System.out.println(receiptMap.get(reserved_ID).toString());
		} 
		catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println("");
			System.out.println("�ٽ� �Է����ּ���!");
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