import java.io.*;
import java.util.*;

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
			writer.write(contact.getFullName() + " " + contact.getEmail() + "\n");
			writer.close();
			
			return true;
		}
		
		catch (IOException ioe)
		{
			System.out.println("The contact could not be added. Please try again.");
			
			return false;
		}
	}
	
	public Boolean modify(Contact oldInfo, Contact changedInfo)
	{		
		while (scan.hasNextLine())
		{
			String temp = scan.nextLine();
			if (temp == oldInfo.toString())
			{
				temp = changedInfo.toString();
				return true;
			}
		}
		
		// Since writer was instantiated in the Constructor
		// it needs to be closed
		try
		{
			writer.close();
		}
		
		catch (IOException ioe)
		{
			System.out.println("The contact could not be added. Please try again.");
		}
			
		return false;
	}
	
	public Boolean remove(Contact contact)
	{
		String temp = scan.nextLine();
		while(scan.hasNextLine())
		{
			if (temp == contact.toString())
			{
				temp = "";
				return true;
			}
		}
		
		return false;
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