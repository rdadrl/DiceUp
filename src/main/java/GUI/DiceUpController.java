package GUI;

import GamePlay.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DiceUpController {
    private int selectedChipColumn = 0;

    //List View for keeping logs of game
    @FXML
    private ListView LogBox;

    @FXML
    private Button roll_Dice;

    //Middle Columns
    @FXML
    private VBox ColMidP1;
    @FXML
    private VBox ColMidP2;

    //Game Columns
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

    //Dice images
    @FXML
    private ImageView imageview_1;
    @FXML
    private ImageView imageview_2;
    @FXML
    private ImageView imageview_3;
    @FXML
    private ImageView imageview_4;
    @FXML
    private Label user_Message;

    private VBox[] columns;

    private Game currGame;
    //public DiceUpController () {
    @FXML
    protected void initialize() {
        columns = new VBox[26];
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
        columns[24] = ColMidP1;
        columns[25] = ColMidP2;

        for(int i = 0; i < columns.length; i++) {
            user_Message.setText("");
            int columnId = i;
            columns[i].setOnMouseClicked(event -> {
                LogBox.getItems().add("Attempting to move from column " + selectedChipColumn + " to " + columnId + ".");
                try {
                    currGame.move(selectedChipColumn, columnId);
                    LogBox.getItems().add("Move Valid");

                    int movePlayed = Math.abs(selectedChipColumn - columnId);
                    if (movePlayed == iv1Val) {
                        imageview_1.setOpacity(0.4);
                        iv1Val = 0;
                    }
                    else if (movePlayed == iv2Val) {
                        imageview_2.setOpacity(0.4);
                        iv2Val = 0;
                    }
                    else if (movePlayed == iv3Val) {
                        imageview_3.setOpacity(0.4);
                        iv3Val = 0;
                    }
                    else if (movePlayed == iv4Val) {
                        imageview_4.setOpacity(0.4);
                        iv4Val = 0;
                    }

                    //turn switched
                    if (iv1Val == 0 && iv2Val == 0 && iv3Val == 0 && iv4Val == 0) {
                        LogBox.getItems().add(" - - " + currGame.getTurn().getName() + "'s Move - -");
                        roll_Dice.setDisable(false);
                    }

                    updateBoard();
                }
                catch (IllegalAccessException e) {
                    user_Message.setText("Invalid Chip");
                    LogBox.getItems().add("Attempted column is unavailable.");
                }
                catch (IllegalStateException e) {
                    user_Message.setText("Invalid Game Phase");
                    LogBox.getItems().add("Unable to take chips due remaining outer chips.");
                }
                catch (IllegalArgumentException e) {
                    user_Message.setText("Invalid Move");
                    LogBox.getItems().add("Move Invalid");
                } catch (Exception e) {
                    LogBox.getItems().add("Unknown Error Occured");
                }
            });
        }

        Player p1 = new Player("Player 1");
        p1.setColor(Color.BROWN);

        Player p2 = new Player("Player 2");
        p2.setColor(Color.WHITESMOKE);
        currGame = new Game(p1, p2);
        LogBox.getItems().add(" - - - New Game - - -");
        LogBox.getItems().add(p1.getName() + " vs " + p2.getName());
        LogBox.getItems().add(" - - " + currGame.getTurn().getName() + "'s Move - -");

        updateBoard();
    }

    private final String p1Color = "SaddleBrown";
    private final String p2Color = "Ivory";

    public void updateBoard() {
        Board currBoard = currGame.getBoard();
        Column[] dataColumns = currBoard.getColumns();

        for (int i = 0; i < dataColumns.length; i++) {
            ArrayList<Chip> currDataChips = dataColumns[i].getChips();
            if (i < 26) columns[i].getChildren().removeAll(columns[i].getChildren());
            for (int j = 0; j < currDataChips.size(); j++) {
                Chip currChipToAdd = currDataChips.get(j);
                ChipElement chipUI = new ChipElement(currChipToAdd.getId());
                int ColumnId = i;
                chipUI.setOnAction(event -> {
                    int chipId = currChipToAdd.getId();
                    int ColmId = ColumnId;
                    System.out.println("Chip " + chipId + " on Column " + ColmId + " was clicked!");
                    selectedChipColumn = ColmId;
                });

                //set color of chip
                if (currChipToAdd.getOwner().equals(currGame.getP1())) chipUI.setStyle(chipUI.getStyle() + " -fx-background-color : " + p1Color+ ";");
                else chipUI.setStyle(chipUI.getStyle() + " -fx-background-color : " + p2Color+ ";");

                columns[i].getChildren().add(chipUI);
            }
        }
    }


    /*
    This method is for the roll_it Button to roll dices
     */
    private static final Image dice_1 = new javafx.scene.image.Image("/images/dice1.jpeg");
    private static final Image dice_2 = new javafx.scene.image.Image("/images/dice2.jpeg");
    private static final Image dice_3 = new javafx.scene.image.Image("/images/dice3.jpeg");
    private static final Image dice_4 = new javafx.scene.image.Image("/images/dice4.jpeg");
    private static final Image dice_5 = new javafx.scene.image.Image("/images/dice5.jpeg");
    private static final Image dice_6 = new javafx.scene.image.Image("/images/dice6.jpeg");
    private static int iv1Val = 0;
    private static int iv2Val = 0;
    private static int iv3Val = 0;
    private static int iv4Val = 0;
    public void rollDice(){
        currGame.rollDices();
        //Dice values
        int num1 = currGame.getDices()[0].getNum();
        int num2 = currGame.getDices()[1].getNum();
        iv1Val = num1;
        iv2Val = num2;

        LogBox.getItems().add("Rolled " + num1 + " and " + num2);
        //reset opacities
        imageview_1.setOpacity(1.0);
        imageview_2.setOpacity(1.0);
        /*
        //show the result of rolling dices in the terminal
        for (int i = 0; i < currGame.getMoves().size(); i++) {
            System.out.println("The " + i + " dice's result is: " + currGame.getMoves().get(i));
        }*/

        //according to the result of rolling dices, choose images to show in the imageViewer

        switch (num1){
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

        switch (num2){
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
        if(num1==num2){
            imageview_3.setOpacity(1.0);
            imageview_3.setImage(imageview_1.getImage());
            imageview_4.setOpacity(1.0);
            imageview_4.setImage(imageview_1.getImage());
            iv3Val = num1;
            iv4Val = num1;
        }
        else{
            imageview_3.setImage(null);
            imageview_4.setImage(null);
            iv3Val = 0;
            iv3Val = 0;
        }
        roll_Dice.setDisable(true);
    }
}