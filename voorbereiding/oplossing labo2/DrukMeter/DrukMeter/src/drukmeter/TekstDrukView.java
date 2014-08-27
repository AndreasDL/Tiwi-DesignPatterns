/*
 * TekstDrukView.java
 *
 * Created on 7 juni 2011, 16:17
 */
package drukmeter;

import drukmeter.model.DrukModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author vongenae
 */
public class TekstDrukView extends javax.swing.JPanel implements Observer {

    private DrukModel model;
    private DecimalFormat format = new DecimalFormat("#.###");

    public TekstDrukView(DrukModel model, String titel) {
        this.model = model;

        setBorder(javax.swing.BorderFactory.createTitledBorder(titel));

        setLayout(new GridBagLayout());

        labelEenheidDruk = new JLabel(model.getEenheid() + ":");
        voegtoe(labelEenheidDruk, 1, 1, 1);
        tekstVeldDruk = new JTextField(formatteer(model.getDruk()), 15);
        voegtoe(tekstVeldDruk, 2, 1, 2);


        voegtoe(new JLabel("max:"), 1, 2, 1);
        tekstMaxDruk = new JTextField(formatteer(model.getMaximaleDruk()), 15);
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
                model.verhoog();
            }
        });

        verlaagKnop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.verlaag();
            }
        });

        tekstVeldDruk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double druk = Double.parseDouble(tekstVeldDruk.getText());
                model.setDruk(druk);
            }
        });
    }

    private String formatteer(double druk) {
        return format.format(druk);
    }

    @Override
    public void update(Observable o, Object arg) {
        tekstVeldDruk.setText(formatteer(model.getDruk()));
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
}
