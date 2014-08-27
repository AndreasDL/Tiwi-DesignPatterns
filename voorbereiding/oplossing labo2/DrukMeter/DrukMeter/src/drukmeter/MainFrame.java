/*
 * MainJFrame.java
 *
 * Created on 7 juni 2011, 16:38
 */
package drukmeter;

import drukmeter.model.DrukModel;
import drukmeter.model.DrukModelFactory;
import drukmeter.model.DrukModelFactoryProvider;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author vongenae, wschepens
 */
public class MainFrame extends JFrame {

    private JComboBox<String> factoryComboBox;
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

            JToolBar toolbar = new JToolBar();
            factoryComboBox = new JComboBox<>(factoryNames);
            factoryComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String factoryName = (String) factoryComboBox.getSelectedItem();
                    setFactory(factoryName);
                }
            });
            toolbar.add(factoryComboBox);
            hoofdPaneel.add(toolbar, BorderLayout.NORTH);
            factoryComboBox.setSelectedIndex(0);
            pack();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setFactory(String factoryName) {
        DrukModelFactoryProvider.getInstance().loadDrukModelFactory(factoryName);

        DrukModelFactory factory = DrukModelFactoryProvider.getInstance().getDrukModelFactory();
        DrukModel model = factory.maakDrukModel();

        viewPaneel.removeAll();

        TekstDrukView tekstView = new TekstDrukView(model, factory.maakTitel());
        model.addObserver(tekstView);
        viewPaneel.add(tekstView);

        WijzerPlaat schijfView = new WijzerPlaat(model);
        model.addObserver(schijfView);
        viewPaneel.add(schijfView);

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
                String[] factoryNames = {
                    "drukmeter.model.impl.PascalDrukModelFactory",
                    "drukmeter.model.impl.AtmDrukModelFactory",
                    "drukmeter.model.impl.PsiDrukModelFactory"};
                new MainFrame(factoryNames).setVisible(true);
            }
        });
    }
}
