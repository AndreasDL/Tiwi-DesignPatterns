/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter;

import drukmeter.pressureUnits.IPressureUnit;
import drukmeter.pressureUnits.PascalPressure;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Whatever
 */
public class DrukModel extends Observable{
    private IPressureUnit pressure;

    public DrukModel() {
        this.pressure = new PascalPressure();
    }    
    
    public IPressureUnit getPressure(){
        return pressure;
    }
    
    public void setPressure(double pressureValue){
        pressure.setPressure(pressureValue);
        setChanged();
        this.notifyObservers();
    }
    
    public void increasePressure(){
        pressure.setPressure(pressure.getPressureValue() + 500);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void decreasePressure(){
        pressure.setPressure(pressure.getPressureValue() - 500);
        setChanged();
        this.notifyObservers();
    }
     
    
}
