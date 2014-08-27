/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.draw;

import java.awt.Point;
import java.awt.event.*;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JComponent;
import ui.events.EventObserver;
import ui.events.ModifierKeys;

/**
 *
 * @author Whatever
 */
public class EventSource {
    protected Set<EventObserver> observers = new LinkedHashSet<>();
    protected volatile Point lastDragPoint = null;
    protected volatile Point pressPoint = null;
    
    private JComponent comp;

    public EventSource(JComponent comp) {
        this.comp = comp;
        
        
        this.comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e)
			{
				press(e.getPoint(), new ModifierKeys(e));
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				release(e.getPoint(), new ModifierKeys(e));
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				click(e.getPoint(), new ModifierKeys(e));
			}
		});
		
		this.comp.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e)
			{
				drag(e.getPoint(), new ModifierKeys(e));
			}
		});
		
		this.comp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				keyPress(e.getKeyCode(), new ModifierKeys(e));
			}
		});
    }
    
    public void addEventObserver(EventObserver obs) {
        observers.add(obs);
    }

    protected void click(Point p, ModifierKeys modKeys) {
        for (EventObserver obs : observers) {
            obs.click(p, modKeys);
        }
    }

    protected void drag(Point p, ModifierKeys modKeys) {
        if (lastDragPoint == null) {
            for (EventObserver obs : observers) {
                obs.dragStart(pressPoint, modKeys);
            }
        }
        for (EventObserver obs : observers) {
            obs.drag(pressPoint, lastDragPoint == null ? pressPoint : lastDragPoint, p, modKeys);
        }
        lastDragPoint = p;
    }

    public Set<EventObserver> getEventObservers() {
        return observers;
    }

    protected void keyPress(int code, ModifierKeys modKeys) {
        for (EventObserver obs : observers) {
            obs.keyPress(code, modKeys);
        }
    }

    protected void press(Point p, ModifierKeys modKeys) {
        pressPoint = p;
    }

    protected void release(Point p, ModifierKeys modKeys) {
        if (lastDragPoint != null) {
            for (EventObserver obs : observers) {
                obs.dragStop(pressPoint, p, modKeys);
            }
        }
        pressPoint = null;
        lastDragPoint = null;
    }

    public void removeEventObserver(EventObserver obs) {
        observers.remove(obs);
    }
    
}
