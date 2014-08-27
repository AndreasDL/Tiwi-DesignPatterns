/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drukmeter.model.impl;

import drukmeter.model.DrukModel;
import drukmeter.model.DrukModelFactory;

/**
 *
 * @author vongenae
 */
public class PsiDrukModelFactory implements DrukModelFactory {

    @Override
    public DrukModel maakDrukModel() {
        return new PsiDrukModel(new PascalDrukModel());
    }

    @Override
    public String maakTitel() {
        return "Druk in psi";
    }
    
}
