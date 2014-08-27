/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.pressureUnits;

/**
 *
 * @author Whatever
 */
public interface IPressureUnit {
    public double toPascalPressure();
    public void setPressure(double pressure);
    public double getPressureValue();
    public double getMaxPressure();
    
    public String getUnit();
}
