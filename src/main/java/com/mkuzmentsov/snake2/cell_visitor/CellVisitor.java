package com.mkuzmentsov.snake2.cell_visitor;

import com.mkuzmentsov.snake2.UnrecoverableGameException;
import com.mkuzmentsov.snake2.board.GameBoard;

public class CellVisitor implements ICellVisitor {

    private final GameBoard gameBoard;

    public CellVisitor(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void visitEmptyCell() {
        gameBoard.moveSnake();
    }

    @Override
    public void visitWallCell() {
        throw new UnrecoverableGameException("Can't enter wall cell");
    }

    @Override
    public void visitMealCell() {
        gameBoard.eatMeal();
    }

    @Override
    public void visitSnakeCell() {
        throw new UnrecoverableGameException("Can't enter snake cell");
    }
}
