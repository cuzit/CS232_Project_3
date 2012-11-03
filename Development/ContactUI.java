import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
        
        public class ContactUI extends JPanel implements ActionListener
        {
            // Declare all of the variables
			private JPanel contactListPanel;
			private JPanel buttonListPanel;
			private JPanel inputPanel;
			private JPanel userInputPanel;
			private JPanel contactPanel;
			private JPanel closeBtnPanel;
			private JScrollPane scrollPane;
			private JList listview;
			private JButton[] button;
			private JLabel[] label;
			private JTextField[] userInput;
            private String name;
            private String email;
			private DefaultListModel listModel;
			private Scanner scan;
			private ContactList contact;
            
            public ContactUI()
            {
				contactPanel = new JPanel();
				contactListPanel = new JPanel();
				buttonListPanel = new JPanel();
				inputPanel = new JPanel();
				userInputPanel = new JPanel();
				closeBtnPanel = new JPanel();
				scrollPane = new JScrollPane();
				listModel = new DefaultListModel();
				
				
				scan = new Scanner(contact.toString());
				
				while (scan.hasNextLine())
				{
					String temp = scan.nextLine();
					list.add(scan.nextLine());
				}
				
				listview = new JList(list);
				
				scrollPane.setPreferredSize(new Dimension(300, 200));
				
				button = new JButton[5];
				label = new JLabel[2];
				userInput = new JTextField[2];
				
				for (int i = 0; i < button.length; i++)
				{
					button[i] = new JButton();
					button[i].addActionListener(this);
				}
				
				for (int j = 0; j < label.length; j++)
				{
					label[j] = new JLabel();
				}
				
				for (int k = 0; k < userInput.length; k++)
				{
					userInput[k] = new JTextField(20);
				}
              
				button[0].setText("Add");
				button[1].setText("Delete");
				button[2].setText("Save");
				button[3].setText("OK");
				button[4].setText("Cancel");
				
				label[0].setText("Name:");
				label[1].setText("Email:");
				
				contactPanel.setLayout(new BorderLayout());
				contactPanel.add(buttonListPanel, BorderLayout.NORTH);
				contactPanel.add(contactListPanel, BorderLayout.CENTER);
				
				buttonListPanel.setLayout(new FlowLayout());
				buttonListPanel.add(button[0]);
				buttonListPanel.add(button[1]);
				buttonListPanel.add(button[2]);
				
				contactListPanel.setLayout(new BorderLayout());
				contactListPanel.add(buttonListPanel, BorderLayout.NORTH);
				contactListPanel.add(scrollPane, BorderLayout.CENTER);

				scrollPane.add(listview);
				
				userInputPanel.setLayout(new GridLayout(6, 1));
				userInputPanel.add(label[0]);
				userInputPanel.add(userInput[0]);
				userInputPanel.add(label[1]);
				userInputPanel.add(userInput[1]);
				
				inputPanel.setLayout(new BorderLayout());
				inputPanel.add(userInputPanel, BorderLayout.NORTH);
				inputPanel.add(closeBtnPanel, BorderLayout.CENTER);
				
				closeBtnPanel.add(button[3]);
				closeBtnPanel.add(button[4]);
				
				add(contactPanel);
				add(inputPanel);
            }
			
			public void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == button[0])
				{
				
				}
				
				else if (event.getSource() == button[1])
				{
				
				}
				
				else if (event.getSource() == button[2])
				{
				
				}
				
				else if (event.getSource() == button[3])
				{
				
				}
				
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