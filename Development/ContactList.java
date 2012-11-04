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
	
	public Boolean add(String name, String email)
	{
		try
		{
			writer.write(name + " " + email + "\r\n");
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
	
	public Boolean remove(String name, String email)
	{
		String output = "";
		while(scan.hasNextLine())
		{
			String temp = scan.nextLine();
			if (temp == name + " " + email)
			{
				output += "";
				
			}
			
			else
			{
				output += temp;
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
	
	public String toString()
	{
		String list = "";
		while (scan.hasNextLine())
		{
			list += scan.nextLine();
		}
		
		return list;
	}
}