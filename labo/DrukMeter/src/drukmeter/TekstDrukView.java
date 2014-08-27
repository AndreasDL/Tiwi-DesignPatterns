/*
 * TekstDrukView.java
 *
 * Created on 7 juni 2011, 16:17
 */
package drukmeter;

import drukmeter.pressureUnits.IPressureUnit;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author vongenae
 */
public class TekstDrukView extends javax.swing.JPanel implements DrukObserver{ 
    
    private DecimalFormat format = new DecimalFormat("#.###");
    private DrukModel model;

    public TekstDrukView(String titel,DrukModel model) {

        setBorder(javax.swing.BorderFactory.createTitledBorder(titel));

        setLayout(new GridBagLayout());

        this.model = model;
        
        labelEenheidDruk = new JLabel("Pa :");
        voegtoe(labelEenheidDruk, 1, 1, 1);
        tekstVeldDruk = new JTextField(formatteer(model.getPressure().getPressureValue()), 15);
        voegtoe(tekstVeldDruk, 2, 1, 2);


        voegtoe(new JLabel("max:"), 1, 2, 1);
        tekstMaxDruk = new JTextField(formatteer(model.getPressure().getMaxPressure()), 15);
        tekstMaxDruk.setEditable(false);
        voegtoe(tekstMaxDruk, 2, 2, 2);

        verhoogKnop = new JButton("Verhoog");
        voegtoe(verhoogKnop, 2, 3, 1);

        verlaagKnop = new JButton("Verlaag");
        voegtoe(verlaagKnop, 3, 3, 1);
        
        initialiseerEvents();
    }

    private void initialiseerEvents() {
        verhoogKnop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.increasePressure();
            }
        });

        verlaagKnop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.decreasePressure();
            }
        });

        tekstVeldDruk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double druk = Double.parseDouble(tekstVeldDruk.getText());
                // druk aanpassen
                model.setPressure(druk);
            }
        });
    }

    private String formatteer(double druk) {
        return format.format(druk);
    }

    private void voegtoe(JComponent comp, int x, int y, int w) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridwidth = w;
        this.add(comp, gbc);
    }
    private JLabel labelEenheidDruk;
    private JTextField tekstVeldDruk;
    private JTextField tekstMaxDruk;
    private JButton verhoogKnop;
    private JButton verlaagKnop;

    @Override
    public void update(Observable o, Object arg) {
       labelEenheidDruk.setText(model.getPressure().getUnit());
       tekstVeldDruk.setText(formatteer(model.getPressure().getPressureValue()));
       tekstMaxDruk.setText(formatteer(model.getPressure().getMaxPressure()));
    }
}
