/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.model.impl;

import drukmeter.model.DrukModel;
import java.util.Observable;

/**
 *
 * @author vongenae
 */
public class PascalDrukModel extends Observable implements DrukModel {

    private static final String EENHEID = "Pa";
    private static final double MAX_DRUK = 1700000; // maximale druk in Pa
    private double druk = 101325; // druk in pascal

    @Override
    public double getDruk() {
        return druk;
    }

    @Override
    public void setDruk(double druk) {
        this.druk = druk;
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public void verhoog() {
        druk++;
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public void verlaag() {
        druk--;
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public double getMaximaleDruk() {
        return MAX_DRUK;
    }

    @Override
    public String getEenheid() {
        return EENHEID;
    }
}
