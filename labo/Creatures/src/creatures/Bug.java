/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures;

import creatures.drawBehavior.IDrawBehavior;
import creatures.moveBehavior.IMoveBehavior;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Whatever
 */
public class Bug implements ICreature {
    private IDrawBehavior drawMode;
    private IMoveBehavior moveMode;

    public Bug(IDrawBehavior drawMode, IMoveBehavior moveMode) {
        this.drawMode = drawMode;
        this.moveMode = moveMode;
    }

    @Override
    public void draw(Graphics2D g, int cellWidth, int cellHeight) {
        drawMode.draw(g, cellWidth, cellHeight);
    }

    @Override
    public void move(Board board) {
        moveMode.move(board, this);
    }
    
    
    public IDrawBehavior getVorm(){
        return this.drawMode;
    }
    public void setVorm(IDrawBehavior vorm){
        this.drawMode = vorm;
    }
    public IMoveBehavior getMoveMode(){
        return this.moveMode;
    }
    public void setMoveMode(IMoveBehavior m){
        this.moveMode = m;
    }
}
