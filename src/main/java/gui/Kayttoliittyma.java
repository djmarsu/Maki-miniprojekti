package gui;


import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author emivo
 */
public class Kayttoliittyma implements Runnable {

    private int lenx;
    private int leny;
    private JFrame ikkuna;

    public Kayttoliittyma(int lenx, int leny) {
     this.lenx = lenx;
     this.leny = leny;
    }

    @Override
    public void run() {
        ikkuna = new JFrame("Ikkuna");


        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        rakennaIkkunanKomponentit();
        ikkuna.setPreferredSize(new Dimension(lenx, leny));

        ikkuna.pack();
        ikkuna.setVisible(true);
//        ikkuna.setResizable(false); 

    }

    private void rakennaIkkunanKomponentit() {

    }

}
