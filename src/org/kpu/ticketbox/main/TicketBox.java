package org.kpu.ticketbox.main;

import org.kpu.ticketbox.cinema.*;
import java.util.*;
import org.kpu.ticketbox.util.*;

public class TicketBox {
	private FamilyScreen familyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	public static final String ADMIN_PASSWORD = "admin";

	Scanner scan = new Scanner(System.in);

	public void initScreen() {
		familyScreen = new FamilyScreen("겨울왕국", "어느 날 부턴가 의문의 목소리가 엘사를 부르고, 평화로운 아렌델 왕국을 위협한다.", 8000, 10, 10);
		animationScreen = new AnimationScreen("언어의 정원", "구두 디자이너를 꿈꾸는 고등학생 ‘다카오’는 비가 오는 날이면 도심의 정원으로 구두를 스케치하러 간다.", 10000, 10, 10);
		premiumScreen = new PremiumScreen("부산행", "정체불명의 바이러스가 전국으로 확산되고 대한민국 긴급재난경보령이 선포된 가운데,", 25000, 5, 5);
	}

	public Screen selectScreen() {
		try {
			System.out.println("");
			System.out.println("----------------------");
			System.out.println("-    상영관 선택 메인메뉴        -");
			System.out.println("----------------------");
			System.out.println("1. 가족 영화 1관");
			System.out.println("2. 애니메이션 영화 2관");
			System.out.println("3. 프리미엄 영화 3관 (커피,케잌 제공)");
			System.out.println("9. 관리자 메뉴");
			System.out.println("선택(1~3, 9)외 종료");
			System.out.println("");
			System.out.print("상영관 선택 : ");
			int num = scan.nextInt();
			switch (num) {
			case 1:
				return familyScreen;
			case 2:
				return animationScreen;
			case 3:
				return premiumScreen;
			case 9:
				this.managermode();
			default:
				return null;
			}
		}
		catch (InputMismatchException e) {
			System.out.println("");
			System.out.println("입력 오류로 인해 종료됩니다!");
			System.out.println("");
			return null;
		}
	}

	public void managermode() {
		scan.nextLine();
		System.out.print("암호 입력 : ");
		String input = scan.nextLine();
		System.out.println("");
		if (input.equals(ADMIN_PASSWORD)) {
			System.out.println("--------------------------");
			System.out.println("-----     관리자 기능         -----");
			System.out.println("--------------------------");
			System.out.println("가족 영화관 결제 총액 : " + String.format("%.1f", familyScreen.admin_mode()));
			System.out.println("애니메이션 영화관 결제 총액 : " + String.format("%.1f", animationScreen.admin_mode()));
			System.out.println("프리미엄 영화관 결제 총액 : " + String.format("%.1f", premiumScreen.admin_mode()));
			System.out.println("영화관 총 티켓 판매량 : " + Statistics.cnt);
			System.out.println("c:\\temp\\tBoxReceipt.txt 백업 시작합니다.");
			familyScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			animationScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			premiumScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			System.out.println("가족 영화관 매출 백업 완료");
			System.out.println("애니메이션 영화관 매출 백업 완료");
			System.out.println("프리미엄 영화관 매출 백업 완료");
			System.out.println("");
			System.out.println("안녕히 가세요!");
			System.exit(0);
		} 
		else {
			System.out.println("비밀번호가 일치하지 않아 종료됩니다.");
			System.out.println("");
		}
	}
}