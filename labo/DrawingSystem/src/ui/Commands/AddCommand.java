/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Commands;

import java.awt.Point;
import ui.draw.SelectionDrawPanel;
import ui.drawables.Drawable;

/**
 *
 * @author Whatever
 */
public class AddCommand implements ICommand{
    private Drawable fig;
    private Point location;
    private SelectionDrawPanel pnl;
    
    public AddCommand(SelectionDrawPanel pnl,Drawable fig, Point location){
        this.fig = fig;
        this.location = location;
        this.pnl = pnl;
    }

    @Override
    public void undo() {
        pnl.remove(fig);
    }

    @Override
    public void execute() {
        pnl.add(fig, location);
    }
    
}
