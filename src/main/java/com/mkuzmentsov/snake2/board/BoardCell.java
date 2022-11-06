package com.mkuzmentsov.snake2.board;

import com.mkuzmentsov.snake2.cell.Cell;
import com.mkuzmentsov.snake2.cell_visitor.ICellVisitor;

public record BoardCell(Cell wrappedCell, int x, int y) implements Cell {

    @Override
    public Color getColor() {
        return wrappedCell().getColor();
    }

    @Override
    public Shape getShape() {
        return wrappedCell().getShape();
    }

    @Override
    public boolean canEnter() {
        return wrappedCell.canEnter();
    }

    @Override
    public void visit(ICellVisitor iCellVisitor) {
        wrappedCell().visit(iCellVisitor);
    }
}
