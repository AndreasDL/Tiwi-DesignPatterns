/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Commands;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import ui.draw.SelectionDrawPanel;
import ui.drawables.Drawable;

/**
 *
 * @author Whatever
 */
public class DeleteCommand implements ICommand{
    private HashMap<Drawable,Point> figs;
    private SelectionDrawPanel pnl;
    
    public DeleteCommand(SelectionDrawPanel pnl , HashMap<Drawable,Point> figs){
        this.figs = figs;
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
