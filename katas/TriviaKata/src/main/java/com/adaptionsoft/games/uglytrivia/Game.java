package com.adaptionsoft.games.uglytrivia;

import java.util.*;
import java.util.stream.Collectors;

///////////////////////////////////////////////
///                                          //
/// Jeu.java                                    //
///                                          //
/// COpyright The TrivaGame Ltd              //
///                                          //
/// Change : 2000-08-17 : add Rock questions //
/// Change : 2002-04-01: Formatting          //
/// Bug 528491 : Fix penaltybox bug where player is stuck //
///////////////////////////////////////////////

/// <summary>
/// The Game
/// </summary>
public class Game {
	public final int MAX_FIVE = 6;
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[MAX_FIVE];
    
    LinkedList<String> _q1 = new LinkedList();
    LinkedList<String> q2 = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			_q1.addLast("Pop Question " + i);
			q2.addLast(("Science Question " + i));
			q3.addLast(("Sports Question " + i));
			Q5.addLast(createRockQuestion(i));
    	}
    	// shuffle();
    }


	private List<String> shuffle()
	{


		List<String> allQuestions = new ArrayList<>();
		allQuestions.addAll(_q1);
		allQuestions.addAll(q2);
		allQuestions.addAll(q3);


		List<String> uniqueQuestions = new LinkedList<>();
		for (String q : allQuestions) {
			q2.stream().filter(x -> uniqueQuestions.contains(x)).collect(Collectors.toSet());
			String x = q2.get(allQuestions.indexOf(q));
			if (q2.contains(x)) {
				uniqueQuestions.add(x);
			} else {
				uniqueQuestions.add(0, x);
			}
		}
		return uniqueQuestions;
	}

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				//User is getting out of penalty box
				isGettingOutOfPenaltyBox = true;

				//Write that user is getting out
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				// add roll to place
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			}
				else {
					System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
					isGettingOutOfPenaltyBox = false;
				}
			
			} else {

				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

				System.out.println(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(_q1.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(q2.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(q3.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(Q5.removeFirst());
		// shuffle();
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	LinkedList<String> q3 = new LinkedList();

	/**
	* To call when the answer is right
	* @return
	 */
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = !(purses[currentPlayer] == 6);
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = !(purses[currentPlayer] == 6);
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}

	/**
	 * To call when the answer is right
	 * @return
	 */
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		// should always return false
		return true;
	}

	LinkedList<String> Q5 = new LinkedList();


}
