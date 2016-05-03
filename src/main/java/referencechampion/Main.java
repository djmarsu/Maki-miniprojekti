package referencechampion;

import gui.UI;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws IOException {

        UI kl = new UI(600, 700, new ReferenceBase());
        SwingUtilities.invokeLater(kl);      
    }
}
