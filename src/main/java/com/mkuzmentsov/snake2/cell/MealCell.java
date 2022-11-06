package com.mkuzmentsov.snake2.cell;

import com.mkuzmentsov.snake2.board.Color;
import com.mkuzmentsov.snake2.board.Shape;
import com.mkuzmentsov.snake2.cell_visitor.ICellVisitor;

public class MealCell implements Cell {
    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Shape getShape() {
        return Shape.CIRCLE;
    }

    @Override
    public boolean canEnter() {
        return true;
    }

    @Override
    public void visit(ICellVisitor iCellVisitor) {
        iCellVisitor.visitMealCell();
    }
}
