import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Credentials extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  //JButtons
  private JButton ok;
  private JButton cancel;
  
  //JLabels
  private JLabel user;
  private JLabel pass;
  private JLabel instructions;
  
  //JTextFields
  private JTextField userBox;
  private JTextField passBox;
  
  //Strings
  private String username;
  private String password;
  private String creds;
  
  //OnContactListener
  private OnContactListener listener;
  
  
  /************
  *Constructor*
  *************/
  public Credentials(OnContactListener l) {
    //Initialize components
    listener = l;
    
    username = ""; password = "";
    
    ok = new JButton("OK");
    ok.addActionListener(this);
    cancel = new JButton("Cancel");
    cancel.addActionListener(this);
    
    user = new JLabel("Username:");
    pass = new JLabel("Password:");
    instructions = new JLabel("Please enter your GMail credentials:");
    
    userBox = new JTextField(20);
    passBox = new JTextField(20);
    
    
    //Set up username area
    JPanel userArea = new JPanel(new GridLayout(2, 1));
    userArea.add(user);
    userArea.add(userBox);
    
    
    //Set up password area
    JPanel passArea = new JPanel(new GridLayout(2, 1));
    passArea.add(pass);
    passArea.add(passBox);
    
    
    //Set up button area
    JPanel buttonArea = new JPanel();
    buttonArea.add(ok);
    buttonArea.add(cancel);
    
    
    //Set up window
    setLayout(new GridLayout(4, 1));
    
    
    //Add panels to main window
    add(instructions);
    add(userArea);
    add(passArea);
    add(buttonArea);
  }
  
  
  /******************
  *OnContactListener*
  *******************/
  public interface OnContactListener {
    public void onData(String s);
  }
  
  
  /***************
  *ActionListener*
  ****************/
  public void actionPerformed(ActionEvent event) {
    //If user presses OK button
    if (event.getSource() == ok) {
      //If both username and password fields are empty
      if(userBox.getText() == "" && passBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Username and password cannot be blank!");
      }
      
      //If username field is empty
      else if(userBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Username cannot be blank!");
      }
      
      //If password field is empty
      else if(passBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Password cannot be blank!");
      }
      
      //If there exists information for both the username and password fields
      else {
	username = userBox.getText();
	password = passBox.getText();
	creds = username + " " + password;
	complete = true;
	listener.onData(creds);
	dispose();
      }
    }
    
    //If the user presses the cancel button
    else if (event.getSource() == cancel) {
      dispose();
    }
    
    //Fail-safe
    else {
      JOptionPane.showMessageDialog(this, "An unknown error has occurred.");
    }
  }
}