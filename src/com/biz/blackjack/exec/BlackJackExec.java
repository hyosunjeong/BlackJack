package com.biz.blackjack.exec;

import com.biz.blackjack.BlackJack;

public class BlackJackExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlackJack bj = new BlackJack();
		int dealerS =0;
		int player1S=0;
		
		bj.makeCard();
		bj.mixCard();
		bj.cardSelect();
		
		bj.viewDealer();
		bj.viewPlayer1();
		
		bj.addPlayer1Card();
		bj.winOrLose(dealerS, player1S);
		
	}

}
