/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import gui.UI;
import javax.swing.SwingUtilities;

/**
 *
 * @author juhokyyh
 */
public class Main {
    public static void main(String[] args) {
        UI kl = new UI(600, 600);
        SwingUtilities.invokeLater(kl);
    }
}
