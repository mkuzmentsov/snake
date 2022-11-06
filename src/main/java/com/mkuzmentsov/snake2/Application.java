package com.mkuzmentsov.snake2;

import com.mkuzmentsov.snake2.board.Color;
import com.mkuzmentsov.snake2.board.Direction;
import com.mkuzmentsov.snake2.board.GameBoard;
import com.mkuzmentsov.snake2.cell.Cell;
import com.mkuzmentsov.snake2.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public void start(Stage stage) {
        GameBoard gameBoard = new GameBoard(15);
        GridPane rootPane = new GridPane();

        GridPane boardPane = new GridPane();
        drawBoard(boardPane, gameBoard);

        GridPane controlsPane = new GridPane();

        Label scoreLabel = new Label("Score: 0");
        Button startButton = new Button("Start");
        controlsPane.add(scoreLabel, 0, 0);
        controlsPane.add(startButton, 0, 1);

        rootPane.add(boardPane, 0, 0);
        rootPane.add(controlsPane, 1, 0);

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);

        Game game = new Game(gameBoard);
        game.addTickListener(() -> {
            drawBoard(boardPane, gameBoard);
            return null;
        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.UP) {
                gameBoard.setSnakeDirection(Direction.UP);
                ke.consume();
            } else if (ke.getCode() == KeyCode.RIGHT) {
                gameBoard.setSnakeDirection(Direction.RIGHT);
                ke.consume();
            } else if (ke.getCode() == KeyCode.DOWN) {
                gameBoard.setSnakeDirection(Direction.DOWN);
                ke.consume();
            } else if (ke.getCode() == KeyCode.LEFT) {
                gameBoard.setSnakeDirection(Direction.LEFT);
                ke.consume();
            }
        });

        stage.show();
    }

    private void drawBoard(GridPane boardPane, GameBoard gameBoard) {
        boardPane.getChildren().clear();
        for (int x = 0; x < gameBoard.getCells().length - 1; x++) {
            for (int y = 0; y < gameBoard.getCells()[x].length - 1; y++) {
                Shape tf;

                Cell p = gameBoard.getCells()[x][y];

                if (p.getShape() == com.mkuzmentsov.snake2.board.Shape.RECTANGLE) {
                    tf = new Rectangle();

                    ((Rectangle) tf).setHeight(10);
                    ((Rectangle) tf).setWidth(10);
                } else {
                    tf = new Circle();
                    ((Circle) tf).setRadius(5);
                }

                if (p.getColor() == Color.BLACK) {
                    tf.setFill(javafx.scene.paint.Color.BLACK);
                } else {
                    tf.setFill(javafx.scene.paint.Color.WHITESMOKE);
                }

                GridPane.setRowIndex(tf, gameBoard.getCells()[x].length - y);
                GridPane.setColumnIndex(tf, x);
                boardPane.getChildren().add(tf);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}