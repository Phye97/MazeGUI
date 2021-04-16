package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicReference;


public class MazeProjectGUI extends Application {
    Stage window;
    Scene sceneEasy, sceneMedium, sceneHard;

    //Buttons
    Button easy = new Button("Easy");
    Button medium = new Button("Medium");
    Button hard = new Button("Hard");
    Button enterName = new Button("This is my name.");
    Button enterSize = new Button("Confirm");

    //Labels and TextFields
    Label nameQuestion = new Label();//adds a message for the user on the menu screen.
    Label easyTitle = new Label();
    Label mediumTitle = new Label();
    Label hardTitle = new Label();
    Label difficultyQuestion = new Label();
    Label mazeSizeQuestion = new Label("What size would you like the maze to be?");
    //Label easyTitle = new Label();
    //Label easyTitle = new Label();

    TextField nameInput = new TextField();
    TextField mazeSizeInput = new TextField();
    //TextField nameInput = new TextField();
    //TextField nameInput = new TextField();

    //Variables
    private final int entranceX = Maze.getEntranceX();
    private final int entranceY = Maze.getEntranceY();
    private final int exitX = Maze.getExitX();
    private final int exitY = Maze.getExitY();
    private int sideLength;
    public String userName;

    //Layouts
    FlowPane layoutName = new FlowPane();
    FlowPane layoutDifficulty = new FlowPane();
    VBox layoutEasy = new VBox(20);
    VBox layoutMedium = new VBox(20);
    VBox layoutHard = new VBox(20);
    GridPane grid = new GridPane();

    SecureRandom random = new SecureRandom();

    Rectangle[][] walls = new Rectangle[50][50];
    Maze maze = new Maze();




    public void start(Stage primaryStage) throws Exception {
        //Building the menu window
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Menu");
        window.show();



        //Scenes
        Scene nameIntro = new Scene(layoutName, 1200, 900);
        Scene difficulty = new Scene(layoutDifficulty, 1200, 900);
        sceneEasy = new Scene(grid, 1200, 900);
        sceneMedium = new Scene(layoutMedium, 1200, 900);
        sceneHard = new Scene(layoutHard, 1200, 900);

        //Background
        //Image img = new Image("https://wallpaperaccess.com/full/4279556.jpg");
       /* BackgroundImage darkForestImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
                */

        //Name intro screen
        window.setScene(nameIntro);//Sets the initial scene to ask the name of the player
        //ImageView entrancePath = new ImageView("https://wallpaperaccess.com/full/4279556.jpg");
        //entrancePath.setPreserveRatio(true);
        //nameIntro.setFill("https://wallpaperaccess.com/full/4279556.jpg");
        nameQuestion.setText("The Boogieman is out and about tonight. Tell me... what is your name?");
        enterName.setOnAction(e -> {
            userName = nameInput.getText();
            window.setScene(difficulty);
        });//saves the username to the program and moves the program to the difficulty screen.
        layoutName.getChildren().addAll(nameQuestion, nameInput, enterName);//adds stuff to the name screen
        layoutName.setAlignment(Pos.CENTER);

        //Difficulty selection screen
        difficultyQuestion.setText(userName + ", your path through the maze can be easy, or difficult. The choice is yours.");
        enterSize.setOnAction(f -> {
                    String sideLengthStr = mazeSizeInput.getText();
                    sideLength = Integer.parseInt(sideLengthStr);
                });
        layoutDifficulty.getChildren().addAll(mazeSizeQuestion, mazeSizeInput, enterSize, difficultyQuestion, easy, medium, hard);//adds the buttons to the menu screen
        layoutDifficulty.setAlignment(Pos.CENTER);





        //Maze Screen
        grid.setHgap(1);
        grid.setVgap(1);
        char [][] randomMaze = Maze.getMaze();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Rectangle w = new Rectangle();
                w.setWidth(50);
                w.setHeight(50);

                walls[i][j] = w;
                grid.add(walls[i][j], i, j);
            }
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if (randomMaze[i][j] == '.'){
                    walls[i][j].setFill(Color.WHITE);
                }



            }
        }


        //Sets the buttons to change the scene based on whichever difficulty level they choose.
        easy.setOnAction(e -> {
            System.out.println("So you've chosen the easy path? Don't disappoint me by getting caught by the boogieman!");
            window.setScene(sceneEasy);
            //generateMazeImage();
        });
        medium.setOnAction(f -> {
            System.out.println("The boogieman hides around every corner, be fast and be careful!");
            window.setScene(sceneMedium);
        });
        hard.setOnAction(g -> {
            System.out.println("You are very confident in your abilities, aren't you? Good luck, try not to get lost..." +
                    " you may never find your way out!.");
            window.setScene(sceneHard);
        });




    }
    

    public static void main(String[] args) {
        launch(args);
    }
}






