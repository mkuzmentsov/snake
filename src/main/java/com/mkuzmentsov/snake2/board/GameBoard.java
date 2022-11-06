package com.mkuzmentsov.snake2.board;

import com.mkuzmentsov.snake2.cell.*;
import com.mkuzmentsov.snake2.cell_visitor.CellVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {

    CellVisitor cellVisitor = new CellVisitor(this);
    private final BoardCell[][] cells;

    private final List<BoardCell> snakeCells;
    private BoardCell mealCell;

    private Direction snakeDirection = Direction.UP;

    public GameBoard(int size) {
        cells = new BoardCell[size][size];

        snakeCells = new ArrayList<>(size);
        snakeCells.add(new BoardCell(new SnakeCell(), 1 , 1));

        generateBaseBoard();
        generateMealCell();
    }

    public BoardCell getNextCell() {
        BoardCell p = snakeCells.get(0);
        BoardCell newCell = null;
        switch (snakeDirection) {
            case UP -> newCell = cells[p.x()][p.y() + 1];
            case RIGHT -> newCell = cells[p.x() + 1][p.y()];
            case DOWN -> newCell = cells[p.x()][p.y() - 1];
            case LEFT -> newCell = cells[p.x() - 1][p.y()];
        };

        return newCell;
    }

    public void tick() {
        BoardCell nextCell = getNextCell();

        nextCell.visit(cellVisitor);
    }

    private void clearSnakeCells() {
        snakeCells.forEach(sc -> {
            cells[sc.x()][sc.y()] = new BoardCell(new EmptyCell(), sc.x(), sc.y());
        });
    }

    private void fillSnakeCells() {
        snakeCells.forEach(sc -> {
            cells[sc.x()][sc.y()] = new BoardCell(new SnakeCell(), sc.x(), sc.y());
        });
    }

    public void moveSnake() {
        BoardCell newHead = getNextCell();

        clearSnakeCells();

        snakeCells.add(0, newHead);
        snakeCells.remove(snakeCells.size() - 1);

        fillSnakeCells();
    }

    public void eatMeal() {
        BoardCell newHead = getNextCell();

        clearSnakeCells();

        snakeCells.add(0, newHead);

        fillSnakeCells();

        generateMealCell();
        System.out.println(mealCell.x() + " " + mealCell.y());
    }

    public void generateBaseBoard() {
        int size = cells.length - 1;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (isWall(x, y, size)) {
                    cells[x][y] = new BoardCell(new WallCell(), x, y);
                } else {
                    cells[x][y] = new BoardCell(new EmptyCell(), x, y);
                }
            }
        }
    }

    private boolean isWall(int x, int y, int size) {
        return x == 0 || y == 0 || x == size - 1 || y == size - 1;
    }

    public void generateMealCell() {
        List<BoardCell> emptyCells = Arrays.stream(cells).flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(BoardCell::canEnter).toList();
        int random = ThreadLocalRandom.current().nextInt(0, emptyCells.size() - 1);

        BoardCell currentCell = emptyCells.get(random);
        this.mealCell = new BoardCell(new MealCell(), currentCell.x(), currentCell.y());

        cells[currentCell.x()][currentCell.y()] = this.mealCell;
    }

    public void setSnakeDirection(Direction direction) {
        this.snakeDirection = direction;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
