CS232_Project_3 README
Version 1.00

1. Parts

There are five classes to the EmailSender.

Contact - Used to create objects that store name and e-mail address.
ContactList - Used to store a list of Contact objects. Contains functions
to add, remove, and modify stored contacts in the list.
ContactUI - GUI interface to ContactList.
Credentials - GUI interface for a user to enter their GMail credentials
so that the e-mail can be sent from their account
EmailSender - Main GUI interface that allows a user to type and send an 
email. Also allows the user to call the ContactUI to select a user from 
their address book.

2. Instructions

To use the program, you can either use the classes in the Precompiled 
Classes folder, or you can compile the program yourself.

To run the precompiled program, cd to the Precompiled Classes folder and 
type 'java EmailSender'

You can manually edit the contacts.txt with a text editor to add contacts, 
if you follow the formatting. Otherwise, you can use the GUI interface to 
add a contact.

In order to use the EmailSender, you will need to add two things to your 
classpath. They are included in the Classpath folder. To set the classpath 
in Linux, run the command 'export set 
CLASSPATH=/path/to/activation.jar:.:/path/to/mail.jar:.'

3. Credits

Matt Silvey - Team Leader, EmailSender, Credentials, Contact
Tyler Hughes - ContactList, ContactUI, contributed to other classes
Crystal Williams - Contact, Contributed to ContactUI and ContactList, 
tester
Jackie Blue - Original draft of EmailSender, tester
Aaron Rudolph - Original draft of ContactUI
