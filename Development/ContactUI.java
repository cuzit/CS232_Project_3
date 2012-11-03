import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
        
        public class ContactUI extends JPanel implements ActionListener
        {
            // Declare all of the variables
			private JPanel[] panel;
			private JScrollPane scrollPane;
			private JList listview;
			private JButton[] button;
			private JLabel[] label;
			private JTextField[] userInput;
            private String name;
            private String email;
			private DefaultListModel listModel;
			private Scanner scan;
			private ContactList contactList;
            
            public ContactUI()
            {
				contactList = new ContactList();
				panel = new JPanel[6];
				scrollPane = new JScrollPane();
				listModel = new DefaultListModel();
				
				if (contactList.toString() != null)
				{
					String contact = contactList.toString();
					scan = new Scanner(contact);
					
					while (scan.hasNextLine())
					{
						String temp = scan.nextLine();
						listModel.addElement(temp);
					}
					
					listview = new JList(listModel);
				}
				
				scrollPane.setPreferredSize(new Dimension(300, 200));
				
				button = new JButton[5];
				label = new JLabel[2];
				userInput = new JTextField[2];
				
				// Instantiate all of the JPanels
				for (int i = 0; i < panel.length; i++)
				{
					panel[i] = new JPanel();
				}
				
				// Instantiate all of the JButtons
				for (int j = 0; j < button.length; j++)
				{
					button[j] = new JButton();
					button[j].addActionListener(this);
				}
				
				// Instantiate all of the JLabels
				for (int k = 0; k < label.length; k++)
				{
					label[k] = new JLabel();
				}
				
				// Instantiate all of the JTextFields
				for (int l = 0; l < userInput.length; l++)
				{
					userInput[l] = new JTextField(20);
				}
              
				// Set the text for all of the JButtons
				button[0].setText("Add");
				button[1].setText("Delete");
				button[2].setText("Save");
				button[3].setText("OK");
				button[4].setText("Cancel");
				
				// Set the text for all of the JLables
				label[0].setText("Name:");
				label[1].setText("Email:");
				
				panel[0].setLayout(new BorderLayout());
				panel[0].add(panel[1], BorderLayout.NORTH);
				panel[0].add(panel[2], BorderLayout.CENTER);
				
				panel[1].setLayout(new FlowLayout());
				panel[1].add(button[0]);
				panel[1].add(button[1]);
				panel[1].add(button[2]);
				
				panel[2].setLayout(new BorderLayout());
				//contactListPanel.add(panel[1], BorderLayout.NORTH);
				panel[2].add(scrollPane, BorderLayout.CENTER);

				scrollPane.add(listview);
				
				panel[4].setLayout(new GridLayout(6, 1));
				panel[4].add(label[0]);
				panel[4].add(userInput[0]);
				panel[4].add(label[1]);
				panel[4].add(userInput[1]);
				
				panel[3].setLayout(new BorderLayout());
				panel[3].add(panel[4], BorderLayout.NORTH);
				panel[3].add(panel[5], BorderLayout.CENTER);
				
				panel[5].add(button[3]);
				panel[5].add(button[4]);
				
				add(panel[0]);
				add(panel[3]);
            }
			
			public void actionPerformed(ActionEvent event)
			{
				// Add button
				if (event.getSource() == button[0])
				{
					contactList.add(userInput[0].getText(), userInput[1].getText());
					listModel.addElement(userInput[0].getText() + " " + userInput[1].getText());
					
					// Reset the fields
					userInput[0].setText("");
					userInput[1].setText("");
				}
				
				// Delete Button
				else if (event.getSource() == button[1])
				{
					contactList.remove(userInput[0].getText(), userInput[1].getText());
					
					// Reset the fields
					userInput[0].setText("");
					userInput[1].setText("");
				}
				
				// Save Button
				else if (event.getSource() == button[2])
				{
					
					
					// Reset the fields
					userInput[0].setText("");
					userInput[1].setText("");
				}
				
				// OK Button
				else if (event.getSource() == button[3])
				{
				
				}
				
				// Cancel Button
				else if (event.getSource() == button[4])
				{
				
				}
			}
			
			public static void main(String[] args)
			{
				JFrame window = new JFrame();
				ContactUI cl = new ContactUI();
				window.add(cl);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setTitle("Contact List");
				window.pack();
				window.setVisible(true);
			}
        }