package shareholder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShareholderTest
{
	public static void main(String[] args)
	{
		try
		{
			ImageIcon icon = new ImageIcon("C:\\Users\\Jerry\\workspace2\\rainbowfrog.gif");
			Scanner a = new Scanner(new File("C:\\Users\\Jerry\\workspace2\\names.txt"));
			ArrayList<Person> people = new ArrayList<Person>();
			
			boolean done = a.hasNext();
			
			while(done)
			{
				Person temp = new Person();
				String line = a.nextLine();
				String[] read = line.split(",");
				temp.setFirst(read[1].trim());
				temp.setLast(read[0].trim());
				people.add(temp);
				done = a.hasNext();
			}
			a.close();
			
			Collections.sort(people);
			
			String[] op = {"authorize name","add shareholder","remove shareholder"};
			
			boolean doneFinal = false;
			
			while(!doneFinal)
			{
				int res = JOptionPane.showOptionDialog(null, "Select an option.", "Shareholders", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, op, null);
				
				if(res == 0)	//	AUTHORIZE SHAREHOLDER
				{
					JTextField x = new JTextField(5);
					JTextField y = new JTextField(5);
					JPanel b = new JPanel();
					b.add(new JLabel("First Name: "));
					b.add(x);
					b.add(new JLabel("Last Name: "));
					b.add(y);
					
					int res2 = JOptionPane.showConfirmDialog(null, b, "Shareholder Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
					if(res2 == JOptionPane.OK_OPTION)
					{
						String inputf = x.getText().trim();
						String inputl = y.getText().trim();
						Person temp_test = new Person();
						temp_test.setLast(inputl);
						temp_test.setFirst(inputf);
						
						boolean fin = false;
						int z = Collections.binarySearch(people, temp_test);
						if(z >= 0)
							fin = true;
							
						if(fin)
							JOptionPane.showMessageDialog(null, "Welcome " + inputf + " " + inputl + ".");
						else if(inputf.length() == 0 || inputl.length() == 0)
							JOptionPane.showMessageDialog(null, "Enteries are invalid.");
						else
							JOptionPane.showMessageDialog(null, inputf + " " + inputl + " is not authorized.");
					}
				}
				else if(res == 1)	//	ADD SHAREHODLER
				{
					JTextField x = new JTextField(5);
					JTextField y = new JTextField(5);
					JPanel b = new JPanel();
					b.add(new JLabel("First Name: "));
					b.add(x);
					b.add(new JLabel("Last Name: "));
					b.add(y);
					
					int res2 = JOptionPane.showConfirmDialog(null, b, "Shareholder Modifier", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
					if(res2 == JOptionPane.OK_OPTION)
					{	
						String inputf = x.getText().trim();
						String inputl = y.getText().trim();
												if(inputf.length() != 0 && inputl.length() != 0)
						{
							String line = inputl + ", " + inputf;
							BufferedWriter write2 = new BufferedWriter(new FileWriter("C:\\Users\\Jerry\\workspace2\\names.txt", true));
							write2.write(line);
							write2.newLine();
							write2.close();
						}
						else
							JOptionPane.showMessageDialog(null, "The entries are invalid.");
					}
				}
				else if(res == 2)	//	REMOVE SHAREHOLDER
				{
					JTextField x = new JTextField(5);
					JTextField y = new JTextField(5);
					JPanel b = new JPanel();
					b.add(new JLabel("First Name: "));
					b.add(x);
					b.add(new JLabel("Last Name: "));
					b.add(y);
					
					int res2 = JOptionPane.showConfirmDialog(null, b, "Shareholder Modifier", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);		
					if(res2 == JOptionPane.OK_OPTION)
					{
						String inputf = x.getText().trim();
						String inputl = y.getText().trim();
						
						if(!inputf.trim().equals("") && !inputl.trim().equals(""))
						{
							File og = new File("C:\\Users\\Jerry\\workspace2\\names.txt");
							File og2 = new File("C:\\Users\\Jerry\\workspace2\\names_temp.txt");
							
							String line = inputl + ", " + inputf;
							Scanner read = new Scanner(og);
							BufferedWriter write = new BufferedWriter(new FileWriter(og2));
							boolean z = read.hasNextLine();
							
							while(z)
							{
								String cur = read.nextLine().trim();
								if(!line.equals(cur))
								{
									write.write(cur);
									write.newLine();
								}
								z = read.hasNextLine();
							}
							
							write.close();
							read.close();
							og.delete();
							og2.renameTo(og);
						}
						else
							JOptionPane.showMessageDialog(null, "The entries are invalid.");
					}
				}
				else
					doneFinal = true;
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
