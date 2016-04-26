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

    private static final int SCROLL_SPEED = 15;
    private static final int DEFAULT_BUTTON_HEIGHT = 30;
    private static final int DEFAULT_BUTTON_WIDTH = 200;
    private int windowWidth;
    private int windowHeight;
    private JFrame window;
    protected Map<String, Field> fields;    
    private JLabel result;
    private JLabel pagetitle;   
    private CreateReference createReferenceAction;
    private Translate translateAction;
    private SelectType selectTypeAction;
    private UpdateReferences updateReferencesAction;
    private ReferenceBase base;  
    private JTextField filename;
    private JTextField filter;

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
        container.setLayout(new GroupLayout(container));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, windowWidth, windowHeight);
        container.add(tabs);

        constructAddReferenceTab(tabs);
        constructListingTab(tabs);
        tabs.addChangeListener(updateReferencesAction);

    }

    private void constructAddReferenceTab(JTabbedPane tabs) {
        Container addReferencePage = new Container();
        
        tabs.addTab("Add reference", addReferencePage);

        this.pagetitle = createLabel("Create a new reference", 20, 10, 300, 30, addReferencePage);
        
        Container fieldArea = new Container();       
        
        JScrollPane scrollPane = new JScrollPane(fieldArea);
        scrollPane.setBounds(20, 60, 500, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        addReferencePage.add(scrollPane);         

        JComboBox typeList = new JComboBox(ReferenceCollection.getTypes());
        typeList.setName("dropdown");
        typeList.setBounds(440, 10, 150, 30);      
        this.selectTypeAction = new SelectType(fieldArea, this.base, this.fields, this.pagetitle, typeList);
        typeList.addActionListener(this.selectTypeAction);
        addReferencePage.add(typeList);

        this.result = createLabel("Fields with * are required", 20, 600, 400, 30, addReferencePage);
        this.result.setName("result");
             
        createLabel("Filename:", 260, 460, 160, 30, addReferencePage);
        this.filename = createTextField("references", 260, 490, 160, 20, addReferencePage);
        this.filename.setHorizontalAlignment(SwingConstants.RIGHT);
        createLabel(".bib", 420, 485, 60, 30, addReferencePage);      
        
        
        this.createReferenceAction = new CreateReference(this.fields, this.base, this.result, typeList);
        this.translateAction = new Translate(base, this.filename, this.result);

        createButton("Create a reference", 20, 520, createReferenceAction, addReferencePage);
        createButton("Create a BibTex file", 260, 520, translateAction,  addReferencePage);

    }

    private void constructListingTab(JTabbedPane tabs) {
        Container listingPage = new Container();

        tabs.addTab("Listing", listingPage);

        createLabel("Reference listing:", 20, 10, 300, 30, listingPage);
        
        
        Container listing = new Container();

        createLabel("Reference listing", 20, 10, 300, 30, listingPage);

        listing.setName("listing");
        listing.setEnabled(false);
        listing.setBounds(0, 0, 300, 300);
        listingPage.add(listing);
        JScrollPane scrollPane = new JScrollPane(listing);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        scrollPane.setBounds(20, 60, 500, 400);
        listingPage.add(scrollPane);
        
        createLabel("Filter:", 220, 20, 50, 30, listingPage);
        this.filter = createTextField("", 280, 20, 200, 30, listingPage);
        this.filter.setName("filter");
        updateReferencesAction = new UpdateReferences(base, listing, filter);
        createButton("Find", 480, 20, 100, 30, updateReferencesAction, listingPage).setName("find");
    }

    

    public void setResult(String string) {
        this.result.setText(string);
    }
    
    public JButton createButton(String name, int x, int y, int width, int height, ActionListener a , Container container){
        JButton button = new JButton(name);
        button.setName(name); //nimi tarvitaan testeihin
        button.setBounds(x, y, width, height);
        button.addActionListener(a);
        container.add(button);
        button.addActionListener(updateReferencesAction);
        return button;
    }
    
    public JButton createButton(String name, int x, int y, ActionListener a, Container container) {
        return createButton(name, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, a, container);
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

    public JFrame getWindow() {
        return window;
    }
}
