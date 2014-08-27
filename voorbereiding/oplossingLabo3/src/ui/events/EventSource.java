package ui.events;

import java.util.Set;

/**
 * @author wijnand.schepens@hogent.be
 */
public interface EventSource 
{
	public void addEventObserver(EventObserver observer);
	
	public void removeEventObserver(EventObserver observer);
	
	public Set<EventObserver> getEventObservers();	
}
