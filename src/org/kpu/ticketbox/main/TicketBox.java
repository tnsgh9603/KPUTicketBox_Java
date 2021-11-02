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
		familyScreen = new FamilyScreen("�ܿ�ձ�", "��� �� ���ϰ� �ǹ��� ��Ҹ��� ���縦 �θ���, ��ȭ�ο� �Ʒ��� �ձ��� �����Ѵ�.", 8000, 10, 10);
		animationScreen = new AnimationScreen("����� ����", "���� �����̳ʸ� �޲ٴ� ����л� ����ī������ �� ���� ���̸� ������ �������� ���θ� ����ġ�Ϸ� ����.", 10000, 10, 10);
		premiumScreen = new PremiumScreen("�λ���", "��ü�Ҹ��� ���̷����� �������� Ȯ��ǰ� ���ѹα� ����糭�溸���� ������ ���,", 25000, 5, 5);
	}

	public Screen selectScreen() {
		try {
			System.out.println("");
			System.out.println("----------------------");
			System.out.println("-    �󿵰� ���� ���θ޴�        -");
			System.out.println("----------------------");
			System.out.println("1. ���� ��ȭ 1��");
			System.out.println("2. �ִϸ��̼� ��ȭ 2��");
			System.out.println("3. �����̾� ��ȭ 3�� (Ŀ��,�ɟ� ����)");
			System.out.println("9. ������ �޴�");
			System.out.println("����(1~3, 9)�� ����");
			System.out.println("");
			System.out.print("�󿵰� ���� : ");
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
			System.out.println("�Է� ������ ���� ����˴ϴ�!");
			System.out.println("");
			return null;
		}
	}

	public void managermode() {
		scan.nextLine();
		System.out.print("��ȣ �Է� : ");
		String input = scan.nextLine();
		System.out.println("");
		if (input.equals(ADMIN_PASSWORD)) {
			System.out.println("--------------------------");
			System.out.println("-----     ������ ���         -----");
			System.out.println("--------------------------");
			System.out.println("���� ��ȭ�� ���� �Ѿ� : " + String.format("%.1f", familyScreen.admin_mode()));
			System.out.println("�ִϸ��̼� ��ȭ�� ���� �Ѿ� : " + String.format("%.1f", animationScreen.admin_mode()));
			System.out.println("�����̾� ��ȭ�� ���� �Ѿ� : " + String.format("%.1f", premiumScreen.admin_mode()));
			System.out.println("��ȭ�� �� Ƽ�� �Ǹŷ� : " + Statistics.cnt);
			System.out.println("c:\\temp\\tBoxReceipt.txt ��� �����մϴ�.");
			familyScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			animationScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			premiumScreen.create_text("c:\\temp\\tBoxReceipt.txt");
			System.out.println("���� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println("�ִϸ��̼� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println("�����̾� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println("");
			System.out.println("�ȳ��� ������!");
			System.exit(0);
		} 
		else {
			System.out.println("��й�ȣ�� ��ġ���� �ʾ� ����˴ϴ�.");
			System.out.println("");
		}
	}
}