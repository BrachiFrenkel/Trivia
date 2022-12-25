import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionsArchive {
    private ArrayList<Question> triviaQuestions = new ArrayList<>(); // The list of questions

    public QuestionsArchive(String fileName) throws FileNotFoundException {
        String question = "", correctAnswer = "";
        ArrayList<String> possibleAnswers = new ArrayList<>();
        Scanner fileReader = new Scanner(new File(fileName));
        // Read the file line by line
        while (fileReader.hasNext()) {
            question = fileReader.nextLine();
            correctAnswer = fileReader.nextLine();
            possibleAnswers = new ArrayList<>();
            // Read the possible answers
            for (int i = 0; i < 3; i++) {
                possibleAnswers.add(fileReader.nextLine());
            }
            // add also the correct answer
            possibleAnswers.add(correctAnswer);
            // Add the question to the list
            triviaQuestions.add(new Question(question, correctAnswer, possibleAnswers));
        }
    }

    public ArrayList<Question> getTriviaQuestions() {
        return triviaQuestions;
    }

    public String getQuestion(int index) {
        return triviaQuestions.get(index).getQuestion();
    }

    public String getCorrectAnswer(int index) {
        return triviaQuestions.get(index).getCorrectAnswer();
    }

    public ArrayList<String> getPossibleAnswers(int index) {
        return triviaQuestions.get(index).getPossibleAnswers();
    }

    public int getNumberOfQuestions() {
        return triviaQuestions.size();
    }

    public void removeQuestion(int index) {
        triviaQuestions.remove(index);
    }

    }