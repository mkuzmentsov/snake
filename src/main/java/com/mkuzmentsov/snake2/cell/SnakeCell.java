package com.mkuzmentsov.snake2.cell;

import com.mkuzmentsov.snake2.board.Color;
import com.mkuzmentsov.snake2.board.Shape;
import com.mkuzmentsov.snake2.cell_visitor.ICellVisitor;

public class SnakeCell implements Cell {
    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Shape getShape() {
        return Shape.RECTANGLE;
    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    public void visit(ICellVisitor iCellVisitor) {
        iCellVisitor.visitSnakeCell();
    }
}
