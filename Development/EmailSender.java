import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

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
  private JLabel cc;
  
  //JTextFields
  private JTextField subjectBox;
  private JTextField fromBox;
  private JTextField toBox;
  private JTextField ccBox;
  
  //JTextAreas
  private JTextArea messageBox;
  
  //JScrollPane
  private JScrollPane scroll;
  
  //PrintWriter
  private PrintWriter draft;
  
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
    cc = new JLabel("CC:");
    
    fromBox = new JTextField(300);
    toBox = new JTextField(300);
    subjectBox = new JTextField(300);
    ccBox = new JTextField(300);
    
    messageBox = new JTextArea(300, 200);
    
    scroll = new JScrollPane();

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
    
    //Create panel containing cc area
    JPanel ccArea = new JPanel(new GridLayout(2, 1));
    ccArea.add(cc);
    ccArea.add(ccBox);
    
    
    //Add the toArea,fromArea, and subjectArea panels into one panel
    JPanel inputs = new JPanel(new GridLayout(4, 1));
    inputs.add(fromArea);
    inputs.add(toArea);
    inputs.add(ccArea);
    inputs.add(subjectArea);
    
    
    //Make the Message area
    JPanel messageArea = new JPanel(new GridLayout(2, 1));
    messageArea.add(message);
    scroll.add(messageBox);
    messageArea.add(messageBox);
    
    //Set up other buttons
    JPanel buttonArea = new JPanel();
    buttonArea.add(send);
    buttonArea.add(clear);
    
    
    //Set main panel properties
    setLayout(new GridLayout(3, 1));
    
    
    //Add everything
	add(scroll);
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
      Credentials credWindow = new Credentials(fromBox.getText(),
			       new Credentials.OnContactListener() {
	//This code is executed when the frame is closed and a value for s is
	//returned.
	public void onData(String s) {
	  //Split the credential string returned into an array where
	  //creds[0] = username and creds[1] = password.
	  String[] creds = s.split(" ");
	  
	  //Create a GmailSender object with the appropriate credentials.
	  GmailSender gmail = new GmailSender(creds[0], creds[1]);
	  
	  //Declare variables
	  boolean sent;
	  
	  //Send the email to multiple recipients
	  if(ccBox.getText() != "" && ccBox.getText() != null) {
	    String[] ccContacts = ccBox.getText().split(", ");
	    sent = gmail.sendMail(ccContacts, subjectBox.getText(),
				  messageBox.getText());
	  }
	  
	  //Send the email to one recipient
	  else {
	    sent = gmail.sendMail(toBox.getText(), subjectBox.getText(),
				  messageBox.getText());
	  }
	  
	  //For security reasons, clear the password
	  password = "";
	  
	  //Inform the user that their email has been sent or failed to send
	  if(sent) {
	    JOptionPane.showMessageDialog(null, "Email has been sent!");
	  }
	  else {
	    try {
	      draft = new PrintWriter("output.txt");
	      draft.println("From: " + fromBox.getText());
	      draft.println("To: " + toBox.getText());
	      draft.println("CC: " + ccBox.getText());
	      draft.println("Subject: " + subjectBox.getText());
	      draft.println("Message: " + messageBox.getText());
	      draft.close();
	      JOptionPane.showMessageDialog(null, "Message failed to send.",
					  "Message draft has been saved as " +
					  "draft.txt.", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    catch(FileNotFoundException e) {
	      JOptionPane.showMessageDialog(null, "File not found!");
	    }
	  }
	  
	  //Clear the text boxes
	  toBox.setText("");
	  fromBox.setText("");
	  subjectBox.setText("");
	  messageBox.setText("");
	  ccBox.setText("");
	}
      });
      
      //Window settings
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
      ccBox.setText("");
      JOptionPane.showMessageDialog(this, "Fields have been reset.");
    }
    
    //If to is pressed
    else if (event.getSource() == to) {
      ContactUI contactPanel = new ContactUI(new ContactUI.OnContactListener()
      {

	//This code is executed when the frame is closed and a value for s is
	//returned.
	public void onData(String s){
	  if(s != null) {
		if (toBox.getText().equals(""))
	      toBox.setText(s);
		else
		  toBox.setText(toBox.getText() + ", " + s);
	  }
	}
      });
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