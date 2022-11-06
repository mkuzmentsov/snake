package com.mkuzmentsov.snake2.cell_visitor;

public interface ICellVisitor {
    void visitEmptyCell();
    void visitWallCell();
    void visitMealCell();
    void visitSnakeCell();
}
