/*
 * MainJFrame.java
 *
 * Created on 7 juni 2011, 16:38
 */
package drukmeter;

import drukmeter.pressureUnits.PascalPressure;
import drukmeter.pressureUnits.IPressureUnit;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author vongenae, wschepens
 */
public class MainFrame extends JFrame {

    private JComboBox<String> keuzeEenheid;
    private JPanel viewPaneel;

    public MainFrame(String[] factoryNames) {
        super("Drukmeter");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        try {
            JPanel hoofdPaneel = new JPanel();
            setContentPane(hoofdPaneel);
            hoofdPaneel.setLayout(new BorderLayout());

            viewPaneel = new JPanel();
            hoofdPaneel.add(viewPaneel, BorderLayout.CENTER);
            init();

            JToolBar toolbar = new JToolBar();
            keuzeEenheid = new JComboBox<>(factoryNames);
            keuzeEenheid.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String keuze = (String) keuzeEenheid.getSelectedItem();
                    // view aanpassen
                }
            });
            toolbar.add(keuzeEenheid);
            hoofdPaneel.add(toolbar, BorderLayout.NORTH);
            keuzeEenheid.setSelectedIndex(0);
            pack();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {

        viewPaneel.removeAll();
        DrukModel model = new DrukModel();

        TekstDrukView tekstView = new TekstDrukView("Druk in Pascal",model);
        viewPaneel.add(tekstView);
        model.addObserver(tekstView);

        SchijfDrukView schijfView = new SchijfDrukView(model);
        viewPaneel.add(schijfView);
        model.addObserver(schijfView);

        revalidate();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                }
                String[] eenheden = {"Pa", "atm", "psi"};
                new MainFrame(eenheden).setVisible(true);
            }
        });
    }
}
