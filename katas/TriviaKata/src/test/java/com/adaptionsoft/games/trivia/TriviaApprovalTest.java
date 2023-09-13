package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.intellij.IntelliJReporter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@UseReporter(IntelliJReporter.class)
public class TriviaApprovalTest {
    @Test
    public void testGame1() {
        var rolls = List.of(2,4,3,2,1,6,5,2,1);
        Approvals.verify(game(rolls));
    }

    @Test
    public void testGame2() {
        var rolls = List.of(3,4,5,2,1,4,6,3,2);
        Approvals.verify(game(rolls));
    }

    List<String> game(List<Integer> rolls) {
        Game aGame = new Game();
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        var answers = new ArrayList<String>();

        for (Integer roll:rolls) {
            aGame.roll(roll);
            Boolean answer = aGame.wasCorrectlyAnswered();
            answers.add(String.valueOf(answer));
        }

        return answers;
    }
}
