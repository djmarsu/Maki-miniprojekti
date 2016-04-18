package gui;

import gui.actionlisteners.CreateReference;
import gui.actionlisteners.SelectType;
import gui.actionlisteners.Translate;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

    public UI(int width, int height, ReferenceBase base) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.base = base;
        this.translateAction = new Translate(base);
        this.fields = new HashMap<>();
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

        this.pagetitle = new JLabel("Create a new reference");
        this.pagetitle.setBounds(20, 10, 300, 30);
        addReferencePage.add(this.pagetitle);
        
        Container fieldArea = new Container();       
        
        JScrollPane scrollPane = new JScrollPane(fieldArea);
        scrollPane.setBounds(20, 60, 500, 400);
        addReferencePage.add(scrollPane);         

        JComboBox typeList = new JComboBox(ReferenceCollection.getTypes());
        typeList.setBounds(470, 10, 120, 30);      
        this.selectTypeAction = new SelectType(fieldArea, this.base, this.fields, this.pagetitle, typeList);
        typeList.addActionListener(this.selectTypeAction);
        addReferencePage.add(typeList);

        this.result = new JLabel("");
        this.result.setBounds(20, 600, 400, 30);
        addReferencePage.add(this.result);
        
        
        this.createReferenceAction = new CreateReference(this.fields, this.base, this.result, typeList);
        JButton createReference = new JButton("Create a reference");
        createReference.setBounds(20, 520, 200, 30);
        createReference.addActionListener(createReferenceAction);
        addReferencePage.add(createReference);

        JButton createBibTex = new JButton("Create a BibTex file");
        createBibTex.setBounds(260, 520, 200, 30);
        createBibTex.addActionListener(translateAction);
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
        updateList.addActionListener(createReferenceAction);
        listingPage.add(updateList);
    }

    

    public void setResult(String string) {
        this.result.setText(string);
    }


}
