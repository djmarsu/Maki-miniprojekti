package gui;

import gui.actionlisteners.CreateBook;
import gui.actionlisteners.Translate;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
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
        constructWindowComponents(window.getContentPane());
        window.setPreferredSize(dimension);

        window.pack();
        window.setVisible(true);
    }

    private void constructWindowComponents(Container container) {
        container.setBackground(Color.DARK_GRAY);
        container.setLayout(new GroupLayout(container));       
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, windowWidth, windowHeight);
        container.add(tabs);
      
        constructAddBookTab(tabs);
        constructListingTab(tabs);        
        
    }
    
    private void constructAddBookTab(JTabbedPane tabs) {
        Container addReferencePage = new Container();   
        
        tabs.addTab("Add reference", addReferencePage); 
        
        JLabel pagetitle = new JLabel("Create a new book");
        pagetitle.setBounds(20, 10, 300, 30);
        addReferencePage.add(pagetitle);
               
        this.result = new JLabel("");  
        this.result.setBounds(20, 600, 200, 30);
        addReferencePage.add(this.result);
        this.fields = FieldCreator.createFields(new Book().getFields(), addReferencePage);
        this.createbook = new CreateBook(this.fields, this.base, this.result);
        setFieldsPosition(fieldPosX, fieldPosY, 40);            
        
        
        JButton createReference = new JButton("Create a reference");  
        createReference.setBounds(20, 520, 200, 30);
        createReference.addActionListener(createbook);
        addReferencePage.add(createReference);      
        
        JButton createBibTex = new JButton("Create a BibTex file");  
        createBibTex.setBounds(260, 520, 200, 30);
        createBibTex.addActionListener(translate);
        addReferencePage.add(createBibTex);
        
    }
    
    private void constructListingTab(JTabbedPane tabs) {
        Container listingPage = new Container();   
        
        tabs.addTab("Listing", listingPage); 
        
        JLabel pagetitle = new JLabel("Reference listing:");
        pagetitle.setBounds(20, 10, 300, 30);
        listingPage.add(pagetitle);
        
        JTextArea listing = new JTextArea("");
        listing.setEnabled(false);
        listing.setBounds(0, 0, 300, 300);
        listingPage.add(listing);
 
        JScrollPane scrollPane = new JScrollPane(listing);
        scrollPane.setBounds(10, 60, 500, 550);
        listingPage.add(scrollPane);
        
        JButton updateList = new JButton("Update List");  
        updateList.setBounds(300, 20, 200, 30);
        updateList.addActionListener(createbook);
        listingPage.add(updateList);      
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
