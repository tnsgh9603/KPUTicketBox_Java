package org.kpu.ticketbox.cinema;

public class FamilyScreen extends Screen {
	public FamilyScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	public void showMovieInfo() {
		System.out.println("");
		System.out.println("------------------------");
		System.out.println(this.strMovieName + " �Ұ�");
		System.out.println("------------------------");
		System.out.println("��ȭ�� : ������ȭ 1��");
		System.out.println("�ٰŸ� : " + this.strMovieStory);
		System.out.println("���� : " + this.nTicketPrice + "��");
	}
}