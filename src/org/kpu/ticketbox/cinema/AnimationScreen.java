package org.kpu.ticketbox.cinema;

public class AnimationScreen extends Screen {
	public AnimationScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	public void showMovieInfo() {
		System.out.println("");
		System.out.println("------------------------");
		System.out.println(this.strMovieName + " 소개");
		System.out.println("------------------------");
		System.out.println("영화관 : 애니메이션 영화 2관");
		System.out.println("줄거리 : " + this.strMovieStory);
		System.out.println("가격 : " + this.nTicketPrice + "원");
	}
}