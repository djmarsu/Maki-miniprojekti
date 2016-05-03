/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author emivo
 */
public class AddAuthor implements ActionListener {
    private SelectType selection;

    public AddAuthor(SelectType selection) {
        this.selection = selection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selection.appendAuthorField();
    }
    
}
