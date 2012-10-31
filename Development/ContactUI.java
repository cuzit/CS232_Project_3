import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;
        import java.io.*;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        
        public class ContactUI
        {
            //class variables  
            private String name;
            private String email;
         
            JPanel panel;
            JFrame frame;  
        
           
            private JPanel buildPanel()
                {
                // initialize the instance variable
                panel = new JPanel();
        
                panel.setLayout(new GridLayout(2, 2));
        
                //Labels and text fields
                JLabel name = new JLabel("Name: ");
                JTextField text1 = new JTextField(50);
                JLabel email = new JLabel ("Email Address: ");
                JTextField text2 = new JTextField(50);
               
        
                panel.add(name);
                panel.add(text1);
                 panel.add(email);
                panel.add(text2);
                               
                return panel;
        
              
            }
            
            public ContactUI()
            {
                // initialize the main container
                frame = new JFrame( "Contact List Input");
        
              
                JButton save = new JButton("Save");
                save.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            PrintWriter outputFile = new PrintWriter("Contacts.txt");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ContactUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
                //Exit button & action performed
                JButton exit = new JButton("Exit");
                exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
        
                // code moved from the buildJpanel() method
                frame.setPreferredSize (new Dimension(300, 300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // add the JPanel to the Frame
                frame.add( buildPanel() );
                frame.pack();
                frame.setVisible(true);
            }
        
            //run command
            public static void main(String[] args){
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run () {
                       
                        new ContactUI();
                    }
                });
            }
        }