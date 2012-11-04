import java.io.*;
import java.util.*;
import javax.swing.*;

public class ContactList
{
	Scanner scan;
	FileWriter writer;
	File contacts;
	
	public ContactList()
	{
		try
		{
			contacts = new File("contacts.txt");
			if (!contacts.exists())
			{
				contacts.createNewFile();
			}
			
			scan = new Scanner(new File("contacts.txt"));
			writer = new FileWriter(new File("contacts.txt"), true);
		}
		
		catch (FileNotFoundException fnfException)
		{
			System.out.println("The file contacts.txt could not be found.");
		}
		
		catch (IOException ioe)
		{
			System.out.println("IOException: The file could not be created.");
		}
	}
	
	public Boolean add(Contact contact)
	{
		try
		{
			writer.write(contact.getFullName() + " " + contact.getEmail() + "\r\n");
			writer.close();
			
			return true;
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be added.\nPlease try again.", "Error", 0);
			System.out.println("The contact could not be added. Please try again.");
			
			return false;
		}
	}
	
	public Boolean modify(String oldInfo, String changedInfo)
	{		
		String output = "";
		
		while (scan.hasNextLine())
		{
			
			String temp = scan.nextLine();
			if (temp == oldInfo)
			{
				output += changedInfo + "\n";
			}
			
			else
			{
				output += temp + "\n";
			}
		}
		
		// Since writer was instantiated in the Constructor
		// it needs to be closed
		try
		{
			writer.write(output);
			writer.close();
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be modified.\nPlease try again.", "Error", 0);
			System.out.println("The contact could not be modified. Please try again.");
			return false;
		}
			
		return true;
	}
	
	public Boolean remove(String contact)
	{
		String output = "";
		while(scan.hasNextLine())
		{
			String temp = scan.nextLine();
			System.out.println(temp);
			if (temp == contact)
			{
				System.out.println(contact);
				output += "";
				
			}
			
			else
			{
				output += temp;
				System.out.println(temp);
			}
		}
		
		try
		{
			writer.write(output);
			writer.close();
		}
		
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "The contact could not be removed.\nPlease try again.", "Error", 0);
			System.out.println("The contact could not be removed. Please try again.");
			return false;
		}
		
		return true;
	}
	
	public List<String> getContacts()
	{
		List<String> data = new ArrayList<String>();
		while (scan.hasNextLine())
		{
			data.add(scan.nextLine());
		}
		return data;
	}
}