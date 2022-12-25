
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class TriviaGame {
    public static final String FILE_NAME = "Trivia.txt";
    public static final int CORRECT_ANSWER_SCORE = 10;
    public static final int WRONG_ANSWER_SCORE = -5;
    private QuestionsArchive triviaQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public TriviaGame() {
        try {
            triviaQuestions = new QuestionsArchive(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean isCorrectAnswer(String answer) {
        return triviaQuestions.getCorrectAnswer(currentQuestionIndex).equals(answer);
    }


    public void shufflePossibleAnswers() {
        Collections.shuffle(triviaQuestions.getPossibleAnswers(currentQuestionIndex));

    }

    public String askQuestion() {
        currentQuestionIndex = chooseRandomQuestion();
        return triviaQuestions.getQuestion(currentQuestionIndex);
    }

    public int chooseRandomQuestion() {
        return new Random().nextInt(triviaQuestions.getNumberOfQuestions());
    }


    public void setScore(int i) {
        score += i;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void removeQuestion(int currentQuestionIndex) {
        triviaQuestions.removeQuestion(currentQuestionIndex);
    }

    public int getNumberOfQuestions() {
        return triviaQuestions.getNumberOfQuestions();
    }

    public ArrayList<String> getPossibleAnswers() {
        return triviaQuestions.getPossibleAnswers(currentQuestionIndex);
    }

    public int getScore() {
        return score;
    }
}
