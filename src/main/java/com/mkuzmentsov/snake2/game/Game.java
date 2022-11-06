package com.mkuzmentsov.snake2.game;

import com.mkuzmentsov.snake2.board.GameBoard;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private final GameBoard gameBoard;

    private AtomicInteger score = new AtomicInteger(0);

    private List<Supplier<Void>> onTickListeners = new ArrayList<>();

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            tick();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void addTickListener(Supplier<Void> onTick) {
        this.onTickListeners.add(onTick);
    }

    public static void initialize() {

    }

    public void tick() {
        gameBoard.tick();
        this.onTickListeners.forEach(Supplier::get);
    }

    public void start() {

    }

    public void pause() {

    }

    public void end() {

    }
}
