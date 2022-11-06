package com.mkuzmentsov.snake2.cell;

import com.mkuzmentsov.snake2.board.Color;
import com.mkuzmentsov.snake2.board.Direction;
import com.mkuzmentsov.snake2.board.Shape;
import com.mkuzmentsov.snake2.cell_visitor.ICellVisitor;

public interface Cell {

    Color getColor();
    Shape getShape();

    boolean canEnter();

    void visit(ICellVisitor iCellVisitor);
}
