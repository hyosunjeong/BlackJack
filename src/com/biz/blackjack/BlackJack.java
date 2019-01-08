package com.biz.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

	private static final String String = null;
	List<String> cardList; // 52장의 카드 리스트
	List<String> dealer; // 딜러 카드
	List<String> player1; // 플레이어 카드
	Scanner scan;

	public BlackJack() {
		cardList = new ArrayList();
		dealer = new ArrayList();
		player1 = new ArrayList();

		scan = new Scanner(System.in);

	}

	/*
	 * 카드는 조커를 제외한 52장 ◆,♥,♠,♣ A,2~10,K(킹),Q(퀸),J(잭)
	 *
	 * 점수를 구할 때 공백을 기준으로 문자열 분리를 하기 위해서
	 * 배열위치 + " " + 문자
	 */

	public void makeCard() {
		// TODO 52장의 카드 만들기
		String[] cardS = { "◆", "♥", "♠", "♣" };
		String cardN = " ";

		for (int i = 0; i < cardS.length; i++) { // ◆,♥,♠,♣
			for (int j = 1; j <= 13; j++) { // A,2~10,J,A,K

				String card = cardS[i] + " " + ("" + j);
				if (j == 1) {
					card = cardS[i] + " " + "A";
				}
				if (j == 11) {
					card = cardS[i] + " " + "J";
				}
				if (j == 12) {
					card = cardS[i] + " " + "Q";
				}
				if (j == 13) {
					card = cardS[i] + " " + "K";
				}
				cardList.add(card);
			}

		}
		// System.out.println(cardList);

	}

	/*
	 * 카드섞기
	 */
	public void mixCard() {
		Collections.shuffle(cardList);

		// System.out.println(cardList);

	}

	/*
	 * 2~10: 숫자 그대로의 점수 K/Q/J: 10점 A:1점
	 */
	public int makeScore(String strCard) {

		String[] strNum = strCard.split(" ");
		String strScore = strNum[1];
		int intScore = 0;

		if (strScore.equalsIgnoreCase("J"))
			return 10;
		if (strScore.equalsIgnoreCase("Q"))
			return 10;
		if (strScore.equalsIgnoreCase("K"))
			return 10;
		if (strScore.equalsIgnoreCase("A"))
			return 1;

		intScore = Integer.valueOf(strScore);
		return intScore;

	}

	/*
	 * 카드 2장을 뽑는다. 
	 * player1과 dealer가 받은 카드는 카드리스트에서 제거한다.
	 */
	public void cardSelect() {

		for (int i = 0; i < 2; i++) {

			player1.add(cardList.get(0));
			cardList.remove(0);
			
			dealer.add(cardList.get(0));
			cardList.remove(0);
		
		}

	}

	/*
	 * 딜러 카드 보기
	 */
	public void viewDealer() {
		String dealerCard = "";
		for (String s : dealer) {
			dealerCard += s + "  ";
		}
		System.out.println("dealer : " + dealerCard);
		dealerScore();
	}

	/*
	 * 플레이어 카드 보기
	 */
	public void viewPlayer1() {
		System.out.println("===============================");
		String player1Card = "";

		for (String s : player1) {
			player1Card += s + "  ";

		}
		System.out.println("player1 : " + player1Card);
		player1Score();

	}

	/*
	 * 딜러의 카드 합 구하기
	 */
	public int dealerScore() {
		int intDSum = 0;
		for (String s : dealer) {
			String strDSum = s;
			intDSum += makeScore(strDSum);
		}
		System.out.println("딜러의 점수: " + intDSum);
		System.out.println("-------------------------------");
		if (intDSum <= 21)
			return intDSum;
		return 0; // 21점을 초과하면 0을 return

	}

	/*
	 * 플레이어 카드 합 구하기
	 */
	public int player1Score() {

		int intPSum = 0;

		for (String s : player1) {
			String strPSum = s;
			intPSum += makeScore(strPSum);
		}
		System.out.println("플레이어 점수: " + intPSum);
		System.out.println("-------------------------------");
		if (intPSum <= 21)
			return intPSum;
		return 0;
	}

	/*
	 * 플레이어 카드 추가
	 */

	public void addPlayer1Card() {
		String yesno = "";
		while (true) {
			System.out.println("-------------------------------");
			System.out.println("카드를 더 받으시겠습니까?");
			System.out.print("YES or NO로 입력해주세요>> ");

			yesno = scan.nextLine();

			if (yesno.equalsIgnoreCase("yes")) {
				player1.add(cardList.get(0));
				cardList.remove(0);

				viewPlayer1();
			}

			
			// 딜러 카드 추가
			addDealerCard();

			if (yesno.equalsIgnoreCase("no") && dealerScore() >= 17) 
				break;
			
			if (dealerScore() == 0 || player1Score() == 0) // (21이상인 값을 0으로 return했음) 
				break;										//둘 중 하나라도 21이상인 값이 나오면 break

			if (dealerScore() == 21 || player1Score() == 21) // 둘 중 하나라도 21인 값이 나오면 break
				break;
		}
	}

	/*
	 * 딜러 카드 추가 
	 * 딜러카드이 합계가 16 이하이면 카드 한 장 추가 후 받은 카드 제거
	 */
	public void addDealerCard() {
		if (dealerScore() <= 16) {
			dealer.add(cardList.get(0));
			cardList.remove(0);
			System.out.println("딜러 카드의 합이 16 이하 이므로 한 장 더 추가합니다.");
			viewDealer();
		}
	}

	/*
	 * 승패 결정
	 */
	public void winOrLose(int dealerS, int player1S) {

		player1S = player1Score();
		dealerS = dealerScore();

			// 딜러와 플레이어 동점일 경우
			if (dealerS == player1S) {
				System.out.println("draw");
				return;
			}
			
			// 딜러의 총합이 플레이어보다 높을 경우
			if (dealerS > player1S) {
				System.out.println("dealer Win!");
				return;
			}

			// 플레이어의 총합이 딜러보다 높을경우
			if (player1S > dealerS) {
				System.out.println("player1 Win!");
				return;
			
			}
	
	}
}
			

