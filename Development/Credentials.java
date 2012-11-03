import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Credentials extends JFrame implements ActionListener {
  /**********
  *Variables*
  ***********/
  private JButton ok;
  private JButton cancel;
  private JLabel user;
  private JLabel pass;
  private JLabel instructions;
  private JTextField userBox;
  private JTextField passBox;
  private String username;
  private String password;
  private boolean complete;
  private OnContactListener listener;
  private String creds;
  
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
    complete = false;
    
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
    if (event.getSource() == ok) {
      if(userBox.getText() == "" && passBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Username and password cannot be blank!");
      }
      
      else if(userBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Username cannot be blank!");
      }
      
      else if(passBox.getText() == "") {
	JOptionPane.showMessageDialog(this, "Password cannot be blank!");
      }
      
      else {
	username = userBox.getText();
	password = passBox.getText();
	creds = username + " " + password;
	complete = true;
	listener.onData(creds);
	dispose();
      }
    }
    
    else if (event.getSource() == cancel) {
      dispose();
    }
    
    else {
      JOptionPane.showMessageDialog(this, "An unknown error has occurred.");
    }
  }
  
  /********************
  *Getters and Setters*
  *********************/
  public String getUsername() {
    return username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public boolean getComplete() {
    return complete;
  }
}