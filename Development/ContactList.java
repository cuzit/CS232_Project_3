import java.io.*;
import java.util.*;

public class ContactList
{
	Scanner scan;
	FileWriter writer;
	
	public ContactList()
	{
		scan = new Scanner(new File("contacts.txt"));
		writer = new FileWriter(new File("contacts.txt"), true);
	}
	
	public Boolean add(Contact contact)
	{
		writer.write(contact.getFullName() + " " + contact.getEmail() + "\n");
		writer.close();
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
		writer.close();
		
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
			}
		}
	}
	
	public String toString()
	{
		String list;
		while (scan.hasNextLine())
		{
			list += scan.nextLine();
		}
		
		return list;
	}
}