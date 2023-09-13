package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    public static final Random RAND = new Random();

    public static final int NB_TURNS = 50;
    public static final int COINS_TO_WIN = 6;
    public final int PLAYER_MAX = 6;
    List<String> players = new ArrayList<>();
    int[] places = new int[PLAYER_MAX];
    int[] purses = new int[PLAYER_MAX];
    boolean[] inPenaltyBox = new boolean[PLAYER_MAX];

    List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayerIndex = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < NB_TURNS; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add("Science Question " + i);
            sportQuestions.add("Sports Question " + i);
            rockQuestions.add("Rock Question " + i);
        }
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

    public String getCurrentPlayerName() {
        return players.get(this.currentPlayerIndex);
    }

    public void roll() {
        this.roll(RAND.nextInt(5) + 1);
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[this.currentPlayerIndex]) {
            if (roll % 2 == 0) {
                System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
            //User is getting out of penalty box
            isGettingOutOfPenaltyBox = true;

            //Write that user is getting out
            System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
        }
        moveCurrentPlayer(roll);
        System.out.println(getCurrentPlayerName() + "'s new location is " + places[this.currentPlayerIndex]);
        System.out.println("The category is " + currentCategory());
    }

    public void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.remove(0));
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.remove(0));
        if (currentCategory() == "Sports")
            System.out.println(sportQuestions.remove(0));
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.remove(0));
    }

    private void moveCurrentPlayer(int roll) {
        places[this.currentPlayerIndex] = places[this.currentPlayerIndex] + roll;
        if (places[this.currentPlayerIndex] > 11) {
            places[this.currentPlayerIndex] = places[this.currentPlayerIndex] - 12;
        }
    }

    private String currentCategory() {
        if (places[currentPlayerIndex] == 0) return "Pop";
        if (places[currentPlayerIndex] == 4) return "Pop";
        if (places[currentPlayerIndex] == 8) return "Pop";
        if (places[currentPlayerIndex] == 1) return "Science";
        if (places[currentPlayerIndex] == 5) return "Science";
        if (places[currentPlayerIndex] == 9) return "Science";
        if (places[currentPlayerIndex] == 2) return "Sports";
        if (places[currentPlayerIndex] == 6) return "Sports";
        if (places[currentPlayerIndex] == 10) return "Sports";
        return "Rock";
    }

    public void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) {
            currentPlayerIndex = 0;
        }

    }

    public boolean hasWinner() {
        return purses[currentPlayerIndex] == COINS_TO_WIN;
    }

    public void answerQuestion(int answer) {
        if (answer != 7) {
            wasCorrectlyAnswered();
        } else {
            wrongAnswer();
        }
    }

    private void earnCoin() {
        purses[currentPlayerIndex]++;
        System.out.println(players.get(currentPlayerIndex)
                + " now has "
                + purses[currentPlayerIndex]
                + " Gold Coins.");
    }



    /**
     * To call when the answer is right
     *
     * @return
     */
    @Deprecated
    public Boolean wasCorrectlyAnswered() {
        rightAnswer();
        return hasWinner();
    }

    public void rightAnswer(){
        if (!inPenaltyBox[currentPlayerIndex] || isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            earnCoin();
        }
    }

    /**
     * To call when the answer is right
     *
     * @return
     */
    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayerIndex) + " was sent to the penalty box");
        inPenaltyBox[currentPlayerIndex] = true;
    }

}