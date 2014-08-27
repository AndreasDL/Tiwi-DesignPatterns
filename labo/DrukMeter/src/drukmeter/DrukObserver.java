/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Whatever
 */
public interface DrukObserver extends Observer {
    @Override
    public void update(Observable o, Object arg);
}
