import java.io.*;

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
	
	public Boolean modify(Contact contact)
	{		
		while (scan.hasNextLine())
		{
			if (scan.nextLine() == contact.toString())
			{
				//scan.next
				return true;
			}
		}
		
		return false;
		
	}
	
	public Boolean remove(Contact contact)
	{
		
	}
	
	public String toString()
	{
	
	}
}