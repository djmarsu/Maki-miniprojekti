package gui;

import gui.actionlisteners.CreateBook;
import gui.actionlisteners.Translate;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import referencechampion.Book;
import referencechampion.ReferenceBase;

/**
 * @author alrial
 */
public class UI implements Runnable {

    private int windowWidth;
    private int windowHeight;
    private JFrame window;
    protected Map<String, Field> fields;
    private int fieldPosX = 20;
    private int fieldPosY = 60; 
    private JLabel result;
    private CreateBook createbook;
    private Translate translate;
    private ReferenceBase base;

    public UI(int width, int height, ReferenceBase base) {
     this.windowWidth = width;
     this.windowHeight = height;
     this.base = base;
     this.translate = new Translate(base);
    }

    @Override
    public void run() {
        constructWindow();

    }
    
    private void constructWindow(){
        window = new JFrame("Reference Champion");


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(windowWidth, windowHeight);
        window.setResizable(false);
        contructWindowComponents(window.getContentPane());
        window.setPreferredSize(dimension);

        window.pack();
        window.setVisible(true);
    }

    private void contructWindowComponents(Container container) {
        container.setBackground(Color.DARK_GRAY);
        container.setLayout(new GroupLayout(container));       
        
        JTabbedPane tabs = new JTabbedPane();    
      
        Container addReferencePage = new Container();   
        
        tabs.addTab("Add reference", addReferencePage); 

        tabs.setBounds(0, 0, windowWidth, windowHeight);
        container.add(tabs);
        
        JLabel pagetitle = new JLabel("Create a new book");
        pagetitle.setBounds(20, 10, 300, 30);
        addReferencePage.add(pagetitle);
               

        this.fields = FieldCreator.createFields(new Book().getFields(), addReferencePage);
        this.createbook = new CreateBook(this.fields , this.base);
        setFieldsPosition(fieldPosX, fieldPosY, 40);              
        
        
        JButton createReference = new JButton("Create a reference");  
        createReference.setBounds(20, 520, 200, 30);
        createReference.addActionListener(createbook);
        addReferencePage.add(createReference);      
        
        JButton createBibTex = new JButton("Create a BibTex file");  
        createBibTex.setBounds(260, 520, 200, 30);
        createBibTex.addActionListener(translate);
        addReferencePage.add(createBibTex);
        
        
        this.result = new JLabel("Event messages come here");  
        this.result.setBounds(20, 600, 200, 30);
        addReferencePage.add(this.result);
        
        
    }
    
    
    private void setFieldsPosition(int x, int y, int gap){
        for (Field field: this.fields.values()) {
            field.setPosition(x, y);
            y += gap;
        }  
    }
    
    public void setResult(String string){
        this.result.setText(string);
    }
    
    
}
