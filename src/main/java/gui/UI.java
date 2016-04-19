package gui;

import gui.actionlisteners.CreateReference;
import gui.actionlisteners.SelectType;
import gui.actionlisteners.Translate;
import gui.actionlisteners.UpdateReferences;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import referencechampion.ReferenceBase;
import referencechampion.ReferenceCollection;

/**
 * @author alrial
 */
public class UI implements Runnable {

    private int windowWidth;
    private int windowHeight;
    private JFrame window;
    protected Map<String, Field> fields;    
    private JLabel result;
    private JLabel pagetitle;
    private CreateReference createReferenceAction;
    private Translate translateAction;
    private SelectType selectTypeAction;
    private ReferenceBase base;    
    private JTextField filename;

    public UI(int width, int height, ReferenceBase base) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.base = base;       
        this.fields = new HashMap<String, Field>();
    }

    @Override
    public void run() {
        constructWindow();
    }

    private void constructWindow() {
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

        constructAddReferenceTab(tabs);
        constructListingTab(tabs);

    }

    private void constructAddReferenceTab(JTabbedPane tabs) {
        Container addReferencePage = new Container();
        
        tabs.addTab("Add reference", addReferencePage);

        this.pagetitle = createLabel("Create a new reference", 20, 10, 300, 30, addReferencePage);
        
        Container fieldArea = new Container();       
        
        JScrollPane scrollPane = new JScrollPane(fieldArea);
        scrollPane.setBounds(20, 60, 500, 400);
        addReferencePage.add(scrollPane);         

        JComboBox typeList = new JComboBox(ReferenceCollection.getTypes());
        typeList.setName("dropdown");
        typeList.setBounds(440, 10, 150, 30);      
        this.selectTypeAction = new SelectType(fieldArea, this.base, this.fields, this.pagetitle, typeList);
        typeList.addActionListener(this.selectTypeAction);
        addReferencePage.add(typeList);

        this.result = createLabel("Fields with * are required", 20, 600, 400, 30, addReferencePage);
        this.result.setName("result");
             
        createLabel("Filename:", 260, 480, 60, 30, addReferencePage);
        this.filename = createTextField("references", 320, 480, 120, 30, addReferencePage);
        this.filename.setHorizontalAlignment(SwingConstants.RIGHT);
        createLabel(".bib", 440, 480, 60, 30, addReferencePage);      
        
        
        this.createReferenceAction = new CreateReference(this.fields, this.base, this.result, typeList, this.filename);
        this.translateAction = new Translate(base);

        createButton("Create a reference", 20, 520, 200, 30, createReferenceAction, addReferencePage);
        createButton("Create a BibTex file", 260, 520, 200, 30, translateAction,  addReferencePage);

    }

    private void constructListingTab(JTabbedPane tabs) {
        Container listingPage = new Container();

        tabs.addTab("Listing", listingPage);

        createLabel("Reference listing:", 20, 10, 300, 30, listingPage);

        JTextArea listing = new JTextArea("");
        listing.setEnabled(false);
        listing.setBounds(0, 0, 300, 300);
        listingPage.add(listing);

        JScrollPane scrollPane = new JScrollPane(listing);
        scrollPane.setBounds(10, 60, 500, 550);
        listingPage.add(scrollPane);

        createButton("Update List", 300, 20, 200, 30, new UpdateReferences(base, listing), listingPage);
    }

    

    public void setResult(String string) {
        this.result.setText(string);
    }
    
    public JButton createButton(String name, int x, int y, int width, int length, ActionListener a , Container container){
        JButton button = new JButton(name);
        button.setName(name); //nimi tarvitaan testeihin
        button.setBounds(x, y, width, length);
        button.addActionListener(a);
        container.add(button);
        return button;
    }
    
    
    
    public JLabel createLabel(String name, int x, int y, int width, int length, Container container){
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, length);
        container.add(label);
        return label;
    }
    
    public JTextField createTextField(String name, int x, int y, int width, int length, Container container){
        JTextField textField = new JTextField(name);
        textField.setBounds(x, y, width, length);       
        container.add(textField);
        return textField;
    }

    public JFrame getWindow() { //testejä varten
        return window;
    }
}
