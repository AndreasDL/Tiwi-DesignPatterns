package ui.Commands;

import java.awt.Point;
import ui.draw.DrawPanel;
import ui.draw.SelectionDrawPanel;
import ui.drawables.Drawable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Whatever
 */
public class MoveCommand implements ICommand{
    private Point start;
    private Point stop;
    private Drawable fig;
    private SelectionDrawPanel pnl;

    public MoveCommand(SelectionDrawPanel pnl,Point start, Point stop, Drawable fig) {
        this.start = start;
        this.stop = stop;
        this.fig = fig;
        this.pnl = pnl;
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
