package referencechampion;

import gui.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Kayttoliittyma kl = new Kayttoliittyma(600, 600);
        SwingUtilities.invokeLater(kl);
    }
}
