package ui.events;


import java.awt.Point;

/**
 * @author wijnand.schepens@hogent.be
 */
public interface EventObserver 
{
	void click(Point p, ModifierKeys modKeys);
	
	void dragStart(Point p, ModifierKeys modKeys);
	
	void drag(Point begin, Point last, Point end, ModifierKeys modKeys);
	
	void dragStop(Point begin, Point end, ModifierKeys modKeys);
	
	void keyPress(int code, ModifierKeys modKeys);
}
