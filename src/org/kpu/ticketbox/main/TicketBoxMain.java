package org.kpu.ticketbox.main;

import org.kpu.ticketbox.cinema.Screen;
import java.util.*;

public class TicketBoxMain {
	public static void main(String[] args) {
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true; // �󿵰� ���� �޴� ���
		ticketBox.initScreen(); // 3���� ��ũ�� ��ü�� ����
		while (true) {
			if (bMainMenu) {
				screen = ticketBox.selectScreen();
				if (screen == null) {
					System.out.print(" �ȳ��� ������ !");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			screen.showScreenMenu();
			System.out.print("�޴��� �����ϼ��� >> ");
			try {
				int select = scan.nextInt();
				switch (select) {
				case 1:
					screen.showMovieInfo();
					break;
				case 2:
					screen.showSeatMap();
					break;
				case 3:
					screen.reserveticket();
					break;
				case 4:
					screen.changeTicket();
					break;
				case 5:
					screen.payment();
					break;
				case 6:
					screen.printTicket();
					break;
				case 7:
					bMainMenu = true;
					break;
				default :
					System.out.println("");
					System.out.println("�ٽ� �Է����ּ���!");
					break;
				}
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("");
				System.out.println("��Ȯ�ϰ� �Է����ּ��� !");
			}
		}
	}
}