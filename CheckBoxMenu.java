package test1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/*
 * CheckBoxMenu.java requires 
 * directory: 
 * 
 */
public class CheckBoxMenu extends JPanel
                          implements ItemListener{
    JCheckBox addToQueue;
    JCheckBox relationships;
    JCheckBox reports;
    JCheckBox ScheduledActions;
    JCheckBox qaCriteria;
    JCheckBox notifications;
    JCheckBox smokeTest;
    JCheckBox permissions;
    JCheckBox mediaManager;
    JCheckBox accessTokens;
    JCheckBox filterTests;
    JCheckBox siteSettings;
    JCheckBox dynamicObs;
    JCheckBox chrome;
    JCheckBox firefox;
    JCheckBox safari;
    JCheckBox internetExplorer;
    JCheckBox osx;
    JCheckBox windows81;
    JCheckBox windows7;

    JButton submit;
    JButton cancel;
    
    int index = 0;
    int index2 = 0;
    int index3 = 0;

    public static volatile boolean hold = true;

    /*

     */

    private StringBuffer choicesTests;
    private StringBuffer choicesBrowsers;
    private StringBuffer choicesOS;
    public String choices;
    public String choices2;
    public String browsers;
    public String tests;
    public String oss;
    public boolean run = true;

    public CheckBoxMenu() {

    	
    	//super(new BorderLayout());
        
    	
        
  
    	
    	
        //Create the check boxes.
        addToQueue = new JCheckBox("Add To Queue");
        addToQueue.setMnemonic(KeyEvent.VK_A);
        addToQueue.setSelected(true);

        relationships = new JCheckBox("Relationships");
        relationships.setMnemonic(KeyEvent.VK_B);
        relationships.setSelected(true);

        reports = new JCheckBox("Reports");
        reports.setMnemonic(KeyEvent.VK_C);
        reports.setSelected(true);

        ScheduledActions = new JCheckBox("Scheduled Actions");
        ScheduledActions.setMnemonic(KeyEvent.VK_D);
        ScheduledActions.setSelected(true);
        
        qaCriteria = new JCheckBox("QA Criteria");
        qaCriteria.setMnemonic(KeyEvent.VK_E);
        qaCriteria.setSelected(true);
        
        notifications = new JCheckBox("Notifications");
        notifications.setMnemonic(KeyEvent.VK_F);
        notifications.setSelected(true);
        
        
        siteSettings = new JCheckBox("Site Settings");
        siteSettings.setMnemonic(KeyEvent.VK_G);
        siteSettings.setSelected(true);
        
        permissions = new JCheckBox("Permissions");
        permissions.setMnemonic(KeyEvent.VK_H);
        permissions.setSelected(true);
        
        smokeTest = new JCheckBox("Nav Imaging");
        smokeTest.setMnemonic(KeyEvent.VK_I);
        smokeTest.setSelected(true);
        
        mediaManager = new JCheckBox("Media Manager");
        mediaManager.setMnemonic(KeyEvent.VK_J);
        mediaManager.setSelected(true);
        
        filterTests = new JCheckBox("Filter Tests");
        filterTests.setMnemonic(KeyEvent.VK_K);
        filterTests.setSelected(true);
        
        accessTokens = new JCheckBox("Access Tokens");
        accessTokens.setMnemonic(KeyEvent.VK_L);
        accessTokens.setSelected(true);
        
        dynamicObs = new JCheckBox("Dynamic Objects");
        dynamicObs.setMnemonic(KeyEvent.VK_T);
        dynamicObs.setSelected(true);
        
        //Create Check Boxes for browsers
        
        chrome = new JCheckBox("Chrome");
        chrome.setMnemonic(KeyEvent.VK_H);
        chrome.setSelected(false);

        firefox = new JCheckBox("Firefox");
        firefox.setMnemonic(KeyEvent.VK_I);
        firefox.setSelected(false);

        safari = new JCheckBox("Safari");
        safari.setMnemonic(KeyEvent.VK_J);
        safari.setSelected(false);

        internetExplorer = new JCheckBox("Internet Explorer");
        internetExplorer.setMnemonic(KeyEvent.VK_K);
        internetExplorer.setSelected(false);
        
        //Create Check Boxes for OSs
        
        osx = new JCheckBox("OS X");
        osx.setMnemonic(KeyEvent.VK_L);
        osx.setSelected(false);
        
        windows81 = new JCheckBox("Windows 8.1");
        windows81.setMnemonic(KeyEvent.VK_M);
        windows81.setSelected(false);
        
        
        windows7 = new JCheckBox("Windows 7");
        windows7.setMnemonic(KeyEvent.VK_N);
        windows7.setSelected(false);
        
        
        
        //Create the submit button
        submit = new JButton("Run Tests");
        
        //Create a cancel button
        cancel = new JButton("Cancel");



        //Register a listener for the check boxes.
        addToQueue.addItemListener(this);
        relationships.addItemListener(this);
        reports.addItemListener(this);
        ScheduledActions.addItemListener(this);
        qaCriteria.addItemListener(this);
        notifications.addItemListener(this);
        siteSettings.addItemListener(this);
        smokeTest.addItemListener(this);
        mediaManager.addItemListener(this);
        permissions.addItemListener(this);
        accessTokens.addItemListener(this);
        filterTests.addItemListener(this);
        dynamicObs.addItemListener(this);
  
        chrome.addItemListener(this);
        firefox.addItemListener(this);
        safari.addItemListener(this);
        internetExplorer.addItemListener(this);
        osx.addItemListener(this);
        windows81.addItemListener(this);
        windows7.addItemListener(this);
        
        submit.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
      	
        	    	
            	    browsers = setChoice().substring(13,17).replaceAll("[^a-zA-Z0-9]", "");
            	    tests = setChoice().substring(0,13).replaceAll("[^a-zA-Z0-9]", "");
            	    oss = setChoice().substring(17, 20).replaceAll("[^a-zA-Z0-9]", "");
            	    try {
						TestConfig config = new TestConfig(browsers, tests, oss);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	           	    
        	  } 
        	} );
        
	    cancel.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) {

      	  }
      	  });	

        //Indicates which options were selected for tests
        choicesTests = new StringBuffer("abcdefghijklt-------------");
 
        //Indicates which options were selected for Browsers
        choicesBrowsers = new StringBuffer("----");
        
        //Indicates which options were selected for Browsers
        choicesOS = new StringBuffer("---");
        
        //Set up conent titles
        JLabel tests = new JLabel();
        JLabel browsers = new JLabel();
        JLabel os = new JLabel();
        tests.setText("<html><h2>Choose tests to run &nbsp; &nbsp;   </h2></html>");
        browsers.setText("<html><h2>Choose Browsers to test</h2></html>");
        os.setText("<html><h2>Select operating systems to test</h2></html>");
        
        //Put the check boxes in a column in a panel
        JPanel checkPanel = new JPanel(new GridLayout(9,1));
        checkPanel.add(tests);
        checkPanel.add(addToQueue);
        checkPanel.add(relationships);
        checkPanel.add(reports);
        checkPanel.add(ScheduledActions);
        checkPanel.add(qaCriteria);
        checkPanel.add(notifications);
        checkPanel.add(siteSettings);
        checkPanel.add(permissions);
        checkPanel.add(filterTests);
        checkPanel.add(smokeTest);
        checkPanel.add(mediaManager);
        checkPanel.add(accessTokens);
        checkPanel.add(dynamicObs);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submit);
        buttonPanel.add(cancel);


        
        //Put the check boxes in a column in a panel
        JPanel checkPanel2 = new JPanel(new GridLayout(9,1));
        checkPanel2.add(browsers);
        checkPanel2.add(chrome);
        checkPanel2.add(firefox);
        checkPanel2.add(safari);
        checkPanel2.add(internetExplorer);
        checkPanel2.add(os);
        checkPanel2.add(osx);
        checkPanel2.add(windows81);
        checkPanel2.add(windows7);

        
        JPanel pane = new JPanel(new GridBagLayout());
       
        pane.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints a = new GridBagConstraints();
        a.gridx = 0;
        a.gridy = 0;
        a.weightx = 0.0;
        a.weighty = 0.0;
        a.fill = GridBagConstraints.HORIZONTAL;
        
      
        pane.add(checkPanel , a);
        
        //GridBagConstraints c = new GridBagConstraints();
        a.gridx = 0;
        a.gridy = 2;
        a.weightx = 0.0;
        a.weighty = 0.0;
        a.anchor = GridBagConstraints.PAGE_END;

        pane.add(buttonPanel, a);
        
        //GridBagConstraints b = new GridBagConstraints();
        a.gridx = 1;
        a.gridy = 0;
        a.weightx = 0.0;
        a.weighty = 0.0;
        a.fill = GridBagConstraints.HORIZONTAL;

        pane.add(checkPanel2 , a);
        
        //add(pane);

        JFrame frame = new JFrame("Vii Network QA");
        frame.add(pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
     
        frame.setSize(600,600);
        frame.setLayout(new GridBagLayout());
        //Create and set up the content pane.
        //JComponent newContentPane = new CheckBoxMenu();
        
       // newContentPane.setOpaque(true); //content panes must be opaque
       // frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);


        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        /*while(hold == true){
            try {
               Thread.sleep(200);
            } catch(InterruptedException e) {
            }
        }
        */
    }


    public String setChoice(){
    	//hold = false;
    	System.out.println(hold);
    	choices = choicesTests.toString();
    	//choices2 = choicesBrowsers.toString();
    	System.out.println(choices);
    	return choices;
    	
    }
    
    public String getTests(){
    	return choices;
    }
    
    public String getBrowsers(){
    	return choices2;
    }    
    
    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {

        char c = '-';
        char d = '-';
        char f = '-';
        Object source = e.getItemSelectable();

        if (source == addToQueue) {
            index = 0;
            c = 'a';
        } else if (source == relationships) {
            index = 1;
            c = 'b';
        } else if (source == reports) {
            index = 2;
            c = 'c';
        } else if (source == ScheduledActions) {
            index = 3;
            c = 'd';
        }
        else if (source == qaCriteria) {
            index = 4;
            c = 'e';
        }
        else if (source == notifications) {
            index = 5;
            c = 'f';
        }
        else if (source == siteSettings) {
            index = 6;
            c = 'g';
        }
        else if (source == permissions) {
            index = 7;
            c = 'h';
        }
        else if (source == smokeTest) {
            index = 8;
            c = 'i';
        }
        else if (source == mediaManager) {
            index = 9;
            c = 'j';
        }
        else if (source == filterTests) {
            index = 10;
            c = 'k';
        }
        else if (source == accessTokens) {
            index = 11;
            c = 'l';
        }
        else if (source == dynamicObs) {
            index = 12;
            c = 't';
        }
        
        if (source == chrome) {
            index = 13;
            if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'm';
        } else if (source == firefox) {
            index = 14;
            if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'n';
        } else if (source == safari) {
            index = 15;
            if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'o';
        } else if (source == internetExplorer) {
            index = 16;
            if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'p';
        }
        else if (source == osx) {
            index = 17;
            if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'q';
        }
        else if (source == windows81) {
            index = 18;
          if(e.getStateChange() == ItemEvent.SELECTED)
            c = 'r';
        }
        else if (source == windows7) {
            index = 19;
         if(e.getStateChange() == ItemEvent.SELECTED)
            c = 's';
        }
      
        
        
        
        
        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            c = '-';
        }
     


        System.out.println(e.getStateChange());

        //Apply the change to the string.
        choicesTests.setCharAt(index, c);
        choicesBrowsers.setCharAt(index2, d);
        choicesOS.setCharAt(index3, f);
  
    }


    
    public StringBuffer testSelection(){
    	return choicesTests;
    }
    
    public StringBuffer browserSelection(){
    	return choicesBrowsers;
    }
    public StringBuffer osSelection(){
    	return choicesOS;
    }

}

