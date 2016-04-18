package gui;

import gui.actionlisteners.CreateReference;
import gui.actionlisteners.Translate;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import referencechampion.ReferenceBase;
import referencechampion.ReferenceCollection;
import referencechampion.ReferenceEntity;

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
    private CreateReference createReferenceAction;
    private Translate translateAction;
    private ReferenceBase base;

    public UI(int width, int height, ReferenceBase base) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.base = base;
        this.translateAction = new Translate(base);
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

        JLabel pagetitle = new JLabel("Create a new <type>");
        pagetitle.setBounds(20, 10, 300, 30);
        addReferencePage.add(pagetitle);

        JComboBox typelist = new JComboBox(ReferenceCollection.getTypes());
        typelist.setBounds(470, 60, 120, 30);       

        addReferencePage.add(typelist);

        this.result = new JLabel("");
        this.result.setBounds(20, 600, 400, 30);
        addReferencePage.add(this.result);
        this.fields = FieldCreator.createFields(ReferenceCollection.getBook(), addReferencePage);       
        setFieldsPosition(fieldPosX, fieldPosY, 40, ReferenceCollection.getBook());
        
        this.createReferenceAction = new CreateReference(this.fields, this.base, this.result);
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

    private void setFieldsPosition(int x, int y, int gap, List<String> names) {
        for (String s : names) {
            this.fields.get(s).setPosition(x, y);
            y += gap;
        }
    }

    public void setResult(String string) {
        this.result.setText(string);
    }

}
