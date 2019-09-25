package GUI;

import GamePlay.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DiceUpController {
    @FXML
    private VBox Col0;
    @FXML
    private VBox Col1;
    @FXML
    private VBox Col2;
    @FXML
    private VBox Col3;
    @FXML
    private VBox Col4;
    @FXML
    private VBox Col5;
    @FXML
    private VBox Col6;
    @FXML
    private VBox Col7;
    @FXML
    private VBox Col8;
    @FXML
    private VBox Col9;
    @FXML
    private VBox Col10;
    @FXML
    private VBox Col11;
    @FXML
    private VBox Col12;
    @FXML
    private VBox Col13;
    @FXML
    private VBox Col14;
    @FXML
    private VBox Col15;
    @FXML
    private VBox Col16;
    @FXML
    private VBox Col17;
    @FXML
    private VBox Col18;
    @FXML
    private VBox Col19;
    @FXML
    private VBox Col20;
    @FXML
    private VBox Col21;
    @FXML
    private VBox Col22;
    @FXML
    private VBox Col23;
    @FXML
    private ImageView imageview_1;
    @FXML
    private ImageView imageview_2;

    private VBox[] columns;

    private Game currGame;
    //public DiceUpController () {
    @FXML
    protected void initialize() {
        columns = new VBox[24];
        columns[0] = Col0;
        columns[1] = Col1;
        columns[2] = Col2;
        columns[3] = Col3;
        columns[4] = Col4;
        columns[5] = Col5;
        columns[6] = Col6;
        columns[7] = Col7;
        columns[8] = Col8;
        columns[9] = Col9;
        columns[10] = Col10;
        columns[11] = Col11;
        columns[12] = Col12;
        columns[13] = Col13;
        columns[14] = Col14;
        columns[15] = Col15;
        columns[16] = Col16;
        columns[17] = Col17;
        columns[18] = Col18;
        columns[19] = Col19;
        columns[20] = Col20;
        columns[21] = Col21;
        columns[22] = Col22;
        columns[23] = Col23;

        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 1");

        currGame = new Game(p1, p2);
        System.out.println("Col0 length: " + Col0.getChildren().size());
    }

    public void updateBoard() {
        Board currBoard = currGame.getBoard();
        Column[] dataColumns = currBoard.getColumns();
        Column[] dataMidColumns = currBoard.getColumns();

        for (int i = 0; i < dataColumns.length; i++) {
            ArrayList<Chip> currDataChips = dataColumns[i].getChips();

            for (int j = 0; j < currDataChips.size(); j++) {
                Chip currChipToAdd = currDataChips.get(j);
                Label chipLabel = new Label("Chip " + currChipToAdd.getId());

                ((VBox) columns[i]).getChildren().add(chipLabel);
            }
        }
    }

    public void rollDice(){
        Dice dice1 = new Dice();
        dice1.roll();
        int num1 = dice1.getNum();
        Dice dice2 = new Dice();
        dice1.roll();
        int num2 = dice1.getNum();

        int[] diceResult = new int[]{num1, num2};

        for (int i = 0; i < diceResult.length; i++) {
            System.out.println("The " + i + " dice's result is: " + diceResult[i]);
        }


        javafx.scene.image.Image dice_1 = new javafx.scene.image.Image("/images/dice1.jpeg");
        javafx.scene.image.Image dice_2 = new javafx.scene.image.Image("/images/dice2.jpeg");
        javafx.scene.image.Image dice_3 = new javafx.scene.image.Image("/images/dice3.jpeg");
        javafx.scene.image.Image dice_4 = new javafx.scene.image.Image("/images/dice4.jpeg");
        javafx.scene.image.Image dice_5 = new javafx.scene.image.Image("/images/dice5.jpeg");
        javafx.scene.image.Image dice_6 = new javafx.scene.image.Image("/images/dice6.jpeg");

        switch (diceResult[0]){
            case 1:
                imageview_1.setImage(dice_1);
                break;
            case 2:
                imageview_1.setImage(dice_2);
                break;
            case 3:
                imageview_1.setImage(dice_3);
                break;
            case 4:
                imageview_1.setImage(dice_4);
                break;
            case 5:
                imageview_1.setImage(dice_5);
                break;
            case 6:
                imageview_1.setImage(dice_6);
                break;
        }

        switch (diceResult[1]){
            case 1:
                imageview_2.setImage(dice_1);
                break;
            case 2:
                imageview_2.setImage(dice_2);
                break;
            case 3:
                imageview_2.setImage(dice_3);
                break;
            case 4:
                imageview_2.setImage(dice_4);
                break;
            case 5:
                imageview_2.setImage(dice_5);
                break;
            case 6:
                imageview_2.setImage(dice_6);
                break;
        }
    }
}