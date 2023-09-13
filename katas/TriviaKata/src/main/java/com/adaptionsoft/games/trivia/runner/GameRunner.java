package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

    public static final Random RAND = new Random();

    public static void main(String[] args) {
        var aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {
            aGame.roll();
            aGame.askQuestion();
            aGame.answerQuestion(generateAnswer());
        } while (!aGame.hasWinner());

    }

    private static int generateAnswer() {
        return RAND.nextInt(9);
    }
}