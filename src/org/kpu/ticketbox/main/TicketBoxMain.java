package org.kpu.ticketbox.main;

import org.kpu.ticketbox.cinema.Screen;
import java.util.*;

public class TicketBoxMain {
	public static void main(String[] args) {
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true; // 상영관 선택 메뉴 사용
		ticketBox.initScreen(); // 3개의 스크린 객체를 생성
		while (true) {
			if (bMainMenu) {
				screen = ticketBox.selectScreen();
				if (screen == null) {
					System.out.print(" 안녕히 가세요 !");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			screen.showScreenMenu();
			System.out.print("메뉴를 선택하세요 >> ");
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
					System.out.println("다시 입력해주세요!");
					break;
				}
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("");
				System.out.println("정확하게 입력해주세요 !");
			}
		}
	}
}