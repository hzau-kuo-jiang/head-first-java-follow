package ch02;

import java.util.stream.IntStream;

public class GuessGame {
	Player[] players = new Player[3];
	
	public void startGame() {
		
		IntStream.range(0, players.length).forEach(i -> players[i] = new Player());
		
		int targetNumber = (int) (Math.random() * 10);
		System.out.println("I'm thinking of a number between 0 and 9...");
		
		while (true) {
			System.out.println("Number to guess is " + targetNumber);
			
			IntStream.range(0, players.length).forEach(i -> players[i].guess());
			
			IntStream.range(0, players.length).forEach(i -> System.out.println("Player " + (i + 1) + " guessed " + players[i].number));
			
			boolean flag = IntStream.range(0, players.length).anyMatch(i -> players[i].number == targetNumber);
			
			if (flag) {
				System.out.println("We have a winner!");
				IntStream.range(0, players.length).forEach(i -> System.out.println("Player " + (i + 1) + " got it right? " + (players[i].number == targetNumber)));
				System.out.println("Game is over.");
				break;
			} else {
				System.out.println("Players will have to try again.");
			}
			
		}
	}
}
