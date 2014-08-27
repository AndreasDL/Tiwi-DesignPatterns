/*
 * WijzerPlaat.java
 *
 * Created on 8 juni 2011, 9:16
 */
package drukmeter;

import drukmeter.model.DrukModel;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vongenae
 */
public class WijzerPlaat extends javax.swing.JPanel implements Observer {

    private DrukModel model;
    private static final int DIM = 200;
    private static final int RAND = 20;
    private static final int START_HOEK = 225;
    private static final int BOOG = 270;
    private static final int STRAAL = (DIM - 2 * RAND) / 2;

    public WijzerPlaat(DrukModel model) {
        this.model = model;
        setPreferredSize(new Dimension(DIM, DIM));
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, DIM, DIM);

        g2.setColor(Color.BLACK);

        // zwarte schaal
        g2.drawArc(RAND, RAND, DIM - 2 * RAND, DIM - 2 * RAND, -45, BOOG);

        g2.setColor(Color.RED);
        // rode cirkel
        g2.fillOval(DIM / 2 - DIM / 20, DIM / 2 - DIM / 20, DIM / 10, DIM / 10);

        // wijzer
        double druk = model.getDruk();

        Point2D eindPunt = berekenPunt(druk, model.getMaximaleDruk(), STRAAL * 0.9);
        g2.drawLine(DIM / 2, DIM / 2, (int) eindPunt.getX(), (int) eindPunt.getY());

    }

    private Point2D berekenPunt(double waarde, double max, double straal) {
        double hoek = Math.toRadians(START_HOEK - (waarde / max * BOOG));
        return new Point2D.Float((float) (DIM / 2 + straal * Math.cos(hoek)),
                (float) (DIM / 2 - straal * Math.sin(hoek)));
    }
}
