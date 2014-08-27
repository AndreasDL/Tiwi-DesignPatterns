/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.model.impl;

import drukmeter.model.DrukModel;
import java.util.Observer;

/**
 *
 * @author vongenae
 */
public class AtmDrukModel implements DrukModel {

    private static final String EENHEID = "atm";
    private PascalDrukModel model;
    private static final double VERHOUDING = 101325;

    public AtmDrukModel(PascalDrukModel model) {
        this.model = model;
    }

    @Override
    public double getDruk() {
        return model.getDruk() / VERHOUDING;
    }

    @Override
    public void setDruk(double druk) {
        model.setDruk(druk * VERHOUDING);
    }

    @Override
    public void verhoog() {
        setDruk(getDruk() + 1);
    }

    @Override
    public void verlaag() {
        setDruk(getDruk() - 1);
    }

    @Override
    public double getMaximaleDruk() {
        return model.getMaximaleDruk() / VERHOUDING;
    }
    
    @Override
    public String getEenheid() {
        return EENHEID;
    }

    @Override
    public void addObserver(Observer o) {
        model.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        model.deleteObserver(o);
    }
}
