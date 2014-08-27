/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.pressureUnits;

/**
 *
 * @author Whatever
 */
public class PascalPressure implements IPressureUnit {
    private double pressure;

    public PascalPressure() {
        this.pressure = 101325;
    }
    
    @Override
    public double toPascalPressure() {
        return pressure;
    }
    @Override
    public double getPressureValue() {
        return pressure;
    }

    @Override
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
    
    @Override
    public String getUnit() {
        return "Pa";
    }

    @Override
    public double getMaxPressure() {
        return 1700000;
    }
    
}
