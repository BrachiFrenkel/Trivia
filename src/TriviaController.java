
//package project;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static javafx.application.Platform.exit;


public class TriviaController implements Initializable {

    @FXML
    private Label questionLbl;

    @FXML
    private Button exitBtn;

    @FXML
    private Button newGameBtn;

    @FXML
    private AnchorPane optionPane;

    @FXML
    private Label scoreLbl;

    @FXML
    private Button endGameBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Label gameOverLbl;

    @FXML
    private Label winLbl;

    @FXML
    private ImageView backgroundImg;


    private TriviaGame game;

    public void initialize(URL location, ResourceBundle resources) {
        startGame();
    }

    public void startGame() {
        // create a new game
        game = new TriviaGame();
        // set the screens
        backgroundImg.setImage(new Image("file:../media/background.jpg"));
        setStartScreen();
        // set the first question
        questionLbl.setWrapText(true);
        questionLbl.setText(game.askQuestion());
        // shuffle the possible answers
        game.shufflePossibleAnswers();
        // set the buttons with the possible answers
        setAnswersToScreen();
        }

    private void setStartScreen() {
        winLbl.setVisible(false);
        scoreLbl.setVisible(true);
        questionLbl.setVisible(true);
        exitBtn.setVisible(false);
        newGameBtn.setVisible(true);
        endGameBtn.setVisible(true);
        nextBtn.setVisible(true);
        optionPane.setVisible(true);
        optionPane.setDisable(false);
        resetOptions();
        exitBtn.setVisible(false);
        newGameBtn.setVisible(false);
        scoreLbl.setText("score:\n" + game.getScore());
    }

    private void setAnswersToScreen() {
        for (Node lbl: optionPane.getChildren()){
            ((Label) lbl).setText(game.getPossibleAnswers().get(optionPane.getChildren().indexOf(lbl)));
        }
    }


    @FXML
    void chooseOptionLblOnAction(MouseEvent event) {
        String selectedAnswer = ((Label)event.getSource()).getText();
        // if answer is correct
        if (game.isCorrectAnswer(selectedAnswer)) {
            game.setScore(game.CORRECT_ANSWER_SCORE);
            scoreLbl.setText("score:\n" + game.getScore());
            ((Label)event.getSource()).setStyle("-fx-background-radius: 10px; -fx-border-color: white; -fx-border-width: 1px; -fx-border-radius: 10px; -fx-background-color: #4cb051;");
            // play sound
            Media sound = new Media(new File("../sound/goodSound.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } else {
            // if answer is wrong
            game.setScore(game.WRONG_ANSWER_SCORE);
            scoreLbl.setText("score:\n" + game.getScore());
            ((Label) event.getSource()).setStyle("-fx-background-radius: 10px; -fx-border-color: white; -fx-border-width: 1px; -fx-border-radius: 10px; -fx-background-color: #ec4352;");
            Media sound = new Media(new File("../sound/badSound.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        // set other options to disabled but in same look
        for (Node lbl: optionPane.getChildren()){
                lbl.setDisable(true);
        }
        // remove the question from the list
        game.removeQuestion(game.getCurrentQuestionIndex());
        return;
    }

    @FXML
    void nextQuestionOnAction(ActionEvent event) {
        resetOptions();
        if(game.getNumberOfQuestions() > 0) {
            questionLbl.setWrapText(true);
            questionLbl.setText(game.askQuestion());
            // shuffle the possible answers
            game.shufflePossibleAnswers();
            // set the buttons with the possible answers
            for (Node option: optionPane.getChildren()){
                ((Label) option).setText(game.getPossibleAnswers().get(optionPane.getChildren().indexOf(option)));
            }
        } else {
            setFinishScreen();
        }


    }

    @FXML
    void endGameOnAction(ActionEvent event) {
        setFinishScreen();
    }

    private void setFinishScreen() {
        winLbl.setVisible(true);
        winLbl.setGraphic(new ImageView(new Image("file:../media/victory.gif")));
        gameOverLbl.setVisible(true);
        gameOverLbl.setText("GAME OVER!\nYour score is: " + game.getScore() );
        scoreLbl.setVisible(false);
        questionLbl.setVisible(false);
        exitBtn.setVisible(true);
        newGameBtn.setVisible(true);
        endGameBtn.setVisible(false);
        nextBtn.setVisible(false);
        optionPane.setVisible(false);
        optionPane.setDisable(true);

    }

    @FXML
    void exitOnAction(ActionEvent event) {
        exit();
    }


    @FXML
    void newGameOnAction(ActionEvent event) {
        for (Node option: optionPane.getChildren()){
            ((Label) option).setDisable(false);
        }
        gameOverLbl.setVisible(false);
        scoreLbl.setVisible(true);
        questionLbl.setVisible(true);
        winLbl.setVisible(false);
        optionPane.setVisible(true);
        startGame();



    }


    public void resetOptions() {
        for (Node option: optionPane.getChildren()){
            option.setDisable(false);
            option.setDisable(false);
            ((Label) option).setStyle("-fx-background-radius: 10px; -fx-background-color: transparent; -fx-border-color: white; -fx-border-width: 1px; -fx-border-radius: 10px;");
        }
    }



}

