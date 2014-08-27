package ui.events;

import ui.events.EventObserver;
import ui.events.EventSource;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 * @author wijnand.schepens@hogent.be
 */
public abstract class EventSourcePanel extends JPanel implements EventSource 
{
	protected volatile Point lastDragPoint = null;
	protected volatile Point pressPoint = null;
	protected Set<EventObserver> observers = new LinkedHashSet<>();
	
	public EventSourcePanel()
	{
		this.addMouseListener(new MouseAdapter() {

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
		
		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e)
			{
				drag(e.getPoint(), new ModifierKeys(e));
			}
		});
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e)
			{
				//keyPress(e.getKeyCode());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				keyPress(e.getKeyCode(), new ModifierKeys(e));
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		});
	}

	
	@Override
	public void addEventObserver(EventObserver obs)
	{
		observers.add(obs);
	}

	@Override
	public void removeEventObserver(EventObserver obs)
	{
		observers.remove(obs);
	}

	@Override
	public Set<EventObserver> getEventObservers()
	{
		return observers;
	}

	
	protected void click(Point p, ModifierKeys modKeys)
	{
		for (EventObserver obs : observers)
			obs.click(p, modKeys);
	}

	protected void drag(Point p, ModifierKeys modKeys)
	{
		if (lastDragPoint == null)
			for (EventObserver obs : observers)
				obs.dragStart(pressPoint, modKeys);
		for (EventObserver obs : observers)
			obs.drag(pressPoint, lastDragPoint == null ? pressPoint : lastDragPoint, p, modKeys);
		lastDragPoint = p;
	}

	protected void keyPress(int code, ModifierKeys modKeys)
	{
		for (EventObserver obs : observers)
			obs.keyPress(code, modKeys);
	}

	protected void press(Point p, ModifierKeys modKeys)
	{
		pressPoint = p;
	}

	protected void release(Point p, ModifierKeys modKeys)
	{
		if (lastDragPoint != null)
			for (EventObserver obs : observers)
				obs.dragStop(pressPoint, p, modKeys);
		pressPoint = null;
		lastDragPoint = null;
	}

	
}
