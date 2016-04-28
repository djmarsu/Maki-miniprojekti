package gui;

import gui.actionlisteners.CreateReference;
import gui.actionlisteners.SelectType;
import gui.actionlisteners.Translate;
import gui.actionlisteners.UpdateReferences;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
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
    private static final int RELATIONAL_WIDTH = 600;
    private static final int RELATIONAL_HEIGTH = 700;
    private final int windowWidth;
    private int windowHeight;
    private JFrame window;
    protected Map<String, Field> fields;  
    protected Container listing;
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
    public ReferenceBase getBase() {
        return base;
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

        this.pagetitle = createLabel("Create a new reference", relX(20),  relY(10), relX(300),  relY(30), addReferencePage);
        
        Container fieldArea = new Container();       
        
        JScrollPane scrollPane = new JScrollPane(fieldArea);
        scrollPane.setBounds(relX(20), relY(60), relX(500), relY(400));
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        addReferencePage.add(scrollPane);         

        JComboBox typeList = new JComboBox(ReferenceCollection.getTypes());
        typeList.setName("dropdown");
        typeList.setBounds(relX(440), relY(10), relX(150), relY(30));      
        this.selectTypeAction = new SelectType(fieldArea, this.base, this.fields, this.pagetitle, typeList, this);
        typeList.addActionListener(this.selectTypeAction);
        selectTypeAction.actionPerformed(null);
        addReferencePage.add(typeList);

        this.result = createLabel("Fields with * are required", relX(20), relY(600), relX(400), relY(30), addReferencePage);
        this.result.setName("result");
             
        createLabel("Filename:", relX(260), relY(460), relX(160), relY(30), addReferencePage);
        this.filename = createTextField("references", relX(260), relY(490), relX(160), relY(20), addReferencePage);
        this.filename.setHorizontalAlignment(SwingConstants.RIGHT);
        createLabel(".bib", relX(420), relY(485), relX(60), relY(30), addReferencePage);      
        
        
        this.createReferenceAction = new CreateReference(this.fields, this.base, this.result, typeList);
        this.translateAction = new Translate(base, this.filename, this.result);

        createButton("Create a reference", relX(20), relY(520), createReferenceAction, addReferencePage);
        createButton("Create a BibTex file", relX(260), relY(520), translateAction,  addReferencePage);

    }

    private void constructListingTab(JTabbedPane tabs) {
        Container listingPage = new Container();

        tabs.addTab("Listing", listingPage);
        tabs.setName("Listing");

        createLabel("Reference listing", relX(20), relY(10), relX(300), relY(30), listingPage);
        
        
        listing = new Container();
        listing.setName("listings");



        listing.setName("listing");
        listing.setEnabled(false);
        listing.setBounds(0, 0, relX(300), relY(300));
        listingPage.add(listing);
        JScrollPane scrollPane = new JScrollPane(listing);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        scrollPane.setBounds(relX(20), relY(60), relX(500), relY(400));
        listingPage.add(scrollPane);
        
        createLabel("Filter:", relX(280), relY(30), relX(50), relY(20), listingPage);
        this.filter = createTextField("", relX(280), relY(20), relX(200), relY(30), listingPage);
        this.filter.setName("search");
        updateReferencesAction = new UpdateReferences(base, listing, filter, window, this);
        createButton("Find", relX(480), relY(20), relX(100), relY(30), updateReferencesAction, listingPage).setName("find");
    }

    

    public void setResult(String string) {
        this.result.setText(string);
    }
    
    public JButton createButton(String name, int x, int y, int width, int height, ActionListener a , Container container){
        JButton button = new JButton(name);
        button.setName(name); //nimi tarvitaan testeihin
        button.setBounds(relX(x), relY(y), relX(width), relY(height));
        button.addActionListener(a);
        container.add(button);
        button.addActionListener(updateReferencesAction);
        return button;
    }
    
    public JButton createButton(String name, int x, int y, ActionListener a, Container container) {
        return createButton(name, relX(x), relY(y), relX(DEFAULT_BUTTON_WIDTH), relY(DEFAULT_BUTTON_HEIGHT), a, container);
    }
    
    
    
    public JLabel createLabel(String name, int x, int y, int width, int length, Container container){
        JLabel label = new JLabel(name);
        label.setBounds(relX(x), relY(y), relX(width), relY(length));
        container.add(label);
        return label;
    }
    
    public JTextField createTextField(String name, int x, int y, int width, int length, Container container){
        JTextField textField = new JTextField(name);
        textField.setBounds(relX(x), relY(y), relX(width), relY(length));    
        container.add(textField);
        return textField;
    }
    
    public Container getListing(){
        return listing;
    }

    public JFrame getWindow() {
        return window;
    }
    
    public int relX(int x){
        return (int)(windowWidth*((double)x/RELATIONAL_WIDTH));
    }
    
    public int relY(int y){
        return (int)(windowHeight*((double)y/RELATIONAL_HEIGTH));
    }

}
