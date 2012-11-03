import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  //Buttons
  private JButton to;
  private JButton send;
  private JButton clear;
  
  //Labels
  private JLabel from;
  private JLabel subject;
  private JLabel message;
  
  //JTextFields
  private JTextField subjectBox;
  private JTextField fromBox;
  private JTextField toBox;
  
  //JTextAreas
  private JTextArea messageBox;
  
  //Strings
  private String username;
  private String password;
  private String[] creds;
  
  
  /************
  *Constructor*
  *************/
  public EmailSender() {
    //Initialize components
    to = new JButton("To:");
    to.addActionListener(this);
    clear = new JButton("Clear");
    clear.addActionListener(this);
    send = new JButton("Send");
    send.addActionListener(this);
    
    from = new JLabel("From:");
    subject = new JLabel("Subject:");
    message = new JLabel("Message:");
    
    fromBox = new JTextField(300);
    toBox = new JTextField(300);
    subjectBox = new JTextField(300);
    
    messageBox = new JTextArea(300, 200);

    username = ""; password = "";
    
    
    //Create panel containing to area
    JPanel toArea = new JPanel(new GridLayout(2,1));
    toArea.add(to);
    toArea.add(toBox);
    
    
    //Create panel containing from area
    JPanel fromArea = new JPanel(new GridLayout(2, 1));
    fromArea.add(from);
    fromArea.add(fromBox);
    
    
    //Create panel containing subject area
    JPanel subjectArea = new JPanel(new GridLayout(2, 1));
    subjectArea.add(subject);
    subjectArea.add(subjectBox);
    
    
    //Add the toArea,fromArea, and subjectArea panels into one panel
    JPanel inputs = new JPanel(new GridLayout(3, 1));
    inputs.add(toArea);
    inputs.add(fromArea);
    inputs.add(subjectArea);
    
    
    //Make the Message area
    JPanel messageArea = new JPanel(new GridLayout(2, 1));
    messageArea.add(message);
    messageArea.add(messageBox);
    
    
    //Set up other buttons
    JPanel buttonArea = new JPanel();
    buttonArea.add(send);
    buttonArea.add(clear);
    
    
    //Set main panel properties
    setLayout(new GridLayout(3, 1));
    
    
    //Add everything
    add(inputs);
    add(messageArea);
    add(buttonArea);
  }
  
  /***************
  *ActionListener*
  ****************/
  public void actionPerformed(ActionEvent event) {
    //If send is pressed
    if (event.getSource() == send) {
      Credentials credWindow = new Credentials(new Credentials.OnContactListener() {
	//This code is executed when the frame is closed and a value for s is
	//returned.
	public void onData(String s) {
	  System.out.println("s: " + s);
	  String[] creds = s.split(" ");
	  System.out.println("Username: " + creds[0]);
	  System.out.println("Password: " + creds[1]);
	  
	}
      });
      credWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      credWindow.setTitle("Enter Credentials");
      credWindow.setPreferredSize(new Dimension(300, 200));
      credWindow.pack();
      credWindow.setVisible(true);
    }
    
    //If clear is pressed
    else if (event.getSource() == clear) {
      toBox.setText("");
      fromBox.setText("");
      subjectBox.setText("");
      messageBox.setText("");
      JOptionPane.showMessageDialog(this, "Fields have been reset.");
    }
    
    //If to is pressed
    else if (event.getSource() == to) {
      JFrame contactWindow = new JFrame();
      ContactUI contactPanel = new ContactUI(new ContactUI.OnContactListener()
      {
	//This code is executed when the frame is closed and a value for s is
	//returned.
	public void onData(String s){
	  //blah
	}
      });
      contactWindow.add(contactPanel);
      contactWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contactWindow.setTitle("Contact List");
      contactWindow.pack();
      contactWindow.setVisible(true);
    }
    
    //Fail-safe
    else {
      JOptionPane.showMessageDialog(this, "An unknown error has occurred.");
    }
  }
  
  
  /*****
  *Main*
  ******/
  public static void main(String[] args) {
    EmailSender mainWindow = new EmailSender();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setTitle("Send Email");
    mainWindow.setPreferredSize(new Dimension(800, 600));
    mainWindow.pack();
    mainWindow.setVisible(true);
  }
}