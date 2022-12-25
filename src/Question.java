import java.util.ArrayList;

public class Question {
    private String question; // The question
    private String correctAnswer;   // The correct answer
    private ArrayList<String> possibleAnswers = new ArrayList<String>(); // The possible answers

    public Question(String question, String correctAnswer, ArrayList<String> possibleAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.possibleAnswers = possibleAnswers;
    }

    // getters
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

}
