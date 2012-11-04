import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EmailSender extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  private JButton to;
  private JLabel from;
  private JTextArea messageBox;
  private JTextField subjectBox;
  private JButton clear;
  private String username;
  private String password;
  private JTextField fromBox;
  private JTextField toBox;
  private JLabel subject;
  private JLabel message;
  private JButton send;
  
  /************
  *Constructor*
  *************/
  public EmailSender() {
    //Initialize components
    to = new JButton("To:");
    to.addActionListener(this);
    clear = new JButton("Clear");
    clear.addActionListener(this);
    from = new JLabel("From:");
    subjectBox = new JTextField(300);
    messageBox = new JTextArea(300, 200);
    username = ""; password = "";
    fromBox = new JTextField(300);
    toBox = new JTextField(300);
    subject = new JLabel("Subject:");
    message = new JLabel("Message:");
    send = new JButton("Send");
    send.addActionListener(this);
    
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
    if (event.getSource() == send) {
      JFrame credWindow = new JFrame();
      Credentials credPanel = new Credentials(new Credentials.OnContactListener() {
	public void onData(String s) {
	}
	
      });
      credWindow.add(credPanel);
      credWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      credWindow.setTitle("Enter Credentials");
      credWindow.setPreferredSize(new Dimension(300, 200));
      credWindow.pack();
      credWindow.setVisible(true);
      
    }
    
    else if (event.getSource() == clear) {
      toBox.setText("");
      fromBox.setText("");
      subjectBox.setText("");
      messageBox.setText("");
      JOptionPane.showMessageDialog(this, "Fields have been reset.");
    }
    
    else if (event.getSource() == to) {
      JFrame contactWindow = new JFrame();
      ContactUI contactPanel = new ContactUI(new ContactUI.OnContactListener()
      {
		public void onData(String s){
		}
      });
      contactWindow.add(contactPanel);
      contactWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contactWindow.setTitle("Contact List");
      contactWindow.pack();
      contactWindow.setVisible(true);
    }
    
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