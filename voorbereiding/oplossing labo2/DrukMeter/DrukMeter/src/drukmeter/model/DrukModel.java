/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.model;

import java.util.Observer;

/**
 *
 * @author vongenae
 */
public interface DrukModel {

    double getDruk();

    void setDruk(double druk);

    void verhoog();

    void verlaag();

    double getMaximaleDruk();
    
    String getEenheid();

    void addObserver(Observer o);

    void deleteObserver(Observer o);
}
