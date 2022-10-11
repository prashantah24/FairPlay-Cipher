import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.Descriptor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

class userDisplay extends JFrame {

	JPanel pan;
	JLabel jl1, jl2;
	JTextField key, plaintext;
	JButton encryption, exit;

	public userDisplay() {
		pan = new JPanel();
		pan.setLayout(null);

		// JLabel
		jl1 = new JLabel("KEY");
		jl2 = new JLabel("PlainText");

		jl1.setBounds(50, 20, 100, 50);
		jl2.setBounds(50, 80, 100, 50);

		pan.add(jl1);
		pan.add(jl2);

		// JTextField
		key = new JTextField(300);
		plaintext = new JTextField(300);

		key.setBounds(130, 20, 300, 50);
		plaintext.setBounds(130, 80, 300, 50);

		pan.add(key);
		pan.add(plaintext);

		// JButton
		encryption = new JButton("Encrypt..");
		exit = new JButton("Exit");

		// Button ActionListener
		// select encryption
		encryption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String keystr = key.getText();
				String textstr = plaintext.getText();
				if (keystr.equals("")) {

					JOptionPane.showMessageDialog(pan, "Enter Your Key");
				} else if (textstr.equals("")) {

					JOptionPane.showMessageDialog(pan, "Enter the String for encryption.");
				} else {
					dispose();
					MainProgram.setmain(keystr, textstr);
				}

			}
		});

		// close the screen, end the program
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		encryption.setBounds(50, 180, 100, 50);
		exit.setBounds(330, 180, 100, 50);

		pan.add(encryption);
		pan.add(exit);

		add(pan);

		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PlayFair Encryption..");

		setVisible(true);

	}

}

class resultDisplay extends JFrame {
	JPanel pan;
	JLabel restitle, encryresult, decryresult,reskey, resjl1, resjl2,resjl3;
	String resenc, resdec;
	JButton resexit;
	JTable table;

	public resultDisplay(String key,String enc, String dec,String row) {
		pan = new JPanel();
		pan.setLayout(null);

		restitle = new JLabel("Result");
		restitle.setFont(new Font("SanSerif", Font.BOLD, 20));

		resjl1 = new JLabel("Encrypted String");
		resjl2 = new JLabel("Decrypted String");
		resjl3 = new JLabel("KEY");

		pan.add(restitle);
		pan.add(resjl1);
		pan.add(resjl2);
		pan.add(resjl3);

		restitle.setBounds(220, 20, 100, 50);
		resjl1.setBounds(50, 100, 300, 50);
		resjl2.setBounds(50, 140, 300, 50);
		resjl3.setBounds(50, 60, 300, 50);

		encryresult = new JLabel(enc);
		decryresult = new JLabel(dec);
		reskey = new JLabel(key);
			
		encryresult.setBounds(160, 100, 300, 50);
		decryresult.setBounds(160, 140, 300, 50);
		reskey.setBounds(160, 60, 300, 50);

		pan.add(encryresult);
		pan.add(decryresult);
		pan.add(reskey);

		resexit = new JButton("Close");
		resexit.setBounds(370, 300, 100, 50);
		pan.add(resexit);

		resexit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		Object rowData[][] = {{ "1", "2", "3", "4", "5" },{ "1", "2", "3", "4", "5" },{ "1", "2", "3", "4", "5" },{ "1", "2", "3", "4", "5" },{ "1", "2", "3", "4", "5" }};
		Object colName[] = { "1", "2", "3", "4", "5" };

		for (int i = 0, k = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				rowData[i][j] = row.charAt(k);
				k++;
			}
		}
		
		table = new JTable(rowData, colName);
		
		table.setFont(new Font("SanSerif", Font.BOLD, 15));
		table.setRowHeight(30);
		
		table.setBounds(50, 180, 150, 150);
		pan.add(table);

		add(pan);

		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PlayFair Encryption..");

		setVisible(true);

	}

}

public class MainProgram extends JFrame {

	public static char plate[][] = new char[5][5];
	public static boolean flag = false; // print the number of characters
	public static String z = "";
	public static String row;
	
	public static void main(String[] args) {
		userDisplay user = new userDisplay();
	}

	public static void setmain(String keystr, String textstr) {
		String decryresult;
		String encryresult;
		String encdisplay;
		
		String key = keystr;
		String text = textstr;

		String blank = "";
		int blankcount = 0;

		plate(key); // Set the passphrase to be used for encryption

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ' ') //remove extra spaces in between
			{
				text = text.substring(0, i) + text.substring(i + 1, text.length());
				blank += 10;
			} else {
				blank += 0;
			}
			if (text.charAt(i) == 'z') // Handled by changing z to q.
			{
				text = text.substring(0, i) + 'q' + text.substring(i + 1, text.length());
				z += 1;
			} else {
				z += 0;
			}
		}

		//encryption
		encryresult = encryption(key, text);
		encdisplay = encryresult;
		
		System.out.println(encryresult + " ");
		// remove spaces
		for (int i = 0; i < encryresult.length(); i++) {
			if (encryresult.charAt(i) == ' ') 
				encryresult = encryresult.substring(0, i) + encryresult.substring(i + 1, encryresult.length());
		}
		//decryption
		decryresult = decryption(key, encryresult, z);

		//A space is inserted between letters through blank, which is an indication that there was a space. 
		for (int i = 0; i < decryresult.length(); i++) {
			if (blank.charAt(i) == '1') {
				decryresult = decryresult.substring(0, i) + " " + decryresult.substring(i, decryresult.length());
			}
		}

		System.out.println(encryresult + " " + decryresult);

		//The result is sent to the result screen.
		resultDisplay result = new resultDisplay(key,encdisplay, decryresult,row);

	}

	public static String encryption(String key, String text) {
		ArrayList<char[]> plaintext = new ArrayList<char[]>();//The value set for plaintext is entered.
		ArrayList<char[]> encrytext = new ArrayList<char[]>();// Encrypted value is entered.
		int a1 = 0, a2 = 0, b1 = 0, b2 = 0;
		String entext = "";

		for (int i = 0; i < text.length(); i += 2) 		// Separate two letters and put them in the ArrayList.
		{
			char[] temparr = new char[2];
			temparr[0] = text.charAt(i);
			try {
				if (text.charAt(i) == text.charAt(i + 1)) 	// If the same character is repeated, insert x.
				{
					temparr[1] = 'x';
					i--;	
				} else {
					temparr[1] = text.charAt(i + 1);
				}
			} catch (StringIndexOutOfBoundsException e) {
				temparr[1] = 'x';
				flag = true;		// flag that the string ends in odd numbers
			}
			plaintext.add(temparr);
		}

		for (int i = 0; i < plaintext.size(); i++) {
			System.out.print(plaintext.get(i)[0] + "" + plaintext.get(i)[1] + " ");
		}
		System.out.println();

		for (int i = 0; i < plaintext.size(); i++) {

			char[] tmpArr = new char[2];
			for (int j = 0; j < plate.length; j++) // check location
			{
				for (int k = 0; k < plate[j].length; k++) {
					if (plate[j][k] == plaintext.get(i)[0]) {	//Check the password p in plaintext and put it in a1 (column) and b1 (row), respectively.
						a1 = j;
						b1 = k;
					}
					if (plate[j][k] == plaintext.get(i)[1]) {
						a2 = j;
						b2 = k;
					}
				}
			}

			if (a1 == a2) //same line
			{
				tmpArr[0] = plate[a1][(b1 + 1) % 5];
				tmpArr[1] = plate[a2][(b2 + 1) % 5];
			} else if (b1 == b2) // same column
			{
				tmpArr[0] = plate[(a1 + 1) % 5][b1];
				tmpArr[1] = plate[(a2 + 1) % 5][b2];
			} else // Different rows and columns
			{
				tmpArr[0] = plate[a2][b1];
				tmpArr[1] = plate[a1][b2];
			}

			encrytext.add(tmpArr);		// Put the encryption result.

		}

		for (int i = 0; i < encrytext.size(); i++) {
			entext += encrytext.get(i)[0] + "" + encrytext.get(i)[1] + " ";	// Put the encryption result.
		}

		return entext;
	}

	public static String decryption(String key, String text, String z) {
		ArrayList<char[]> cryptogram = new ArrayList<char[]>();// password
		ArrayList<char[]> decrytext = new ArrayList<char[]>(); // decoded value
		int a1 = 0, a2 = 0, b1 = 0, b2 = 0; 
		String detext = "";

		int lenthflag = 1;

		for (int i = 0; i < text.length(); i += 2) {	// Separate two letters and put them in the ArrayList.
			char[] temparr = new char[2];
			temparr[0] = text.charAt(i);
			temparr[1] = text.charAt(i + 1);
			cryptogram.add(temparr);
		}

		for (int i = 0; i < cryptogram.size(); i++) {

			char[] tmpArr = new char[2];
			for (int j = 0; j < plate.length; j++) {
				for (int k = 0; k < plate[j].length; k++) {
					if (plate[j][k] == cryptogram.get(i)[0]) {	//Check the password p position of the password and put it in a1 (column) and b1 (row), respectively.
						a1 = j;
						b1 = k;
					}
					if (plate[j][k] == cryptogram.get(i)[1]) {
						a2 = j;
						b2 = k;
					}
				}
			}

			//decryption
			if (a1 == a2) 			// same line
			{
				tmpArr[0] = plate[a1][(b1 + 4) % 5];
				tmpArr[1] = plate[a2][(b2 + 4) % 5];
			} else if (b1 == b2) 	//same column
			{
				tmpArr[0] = plate[(a1 + 4) % 5][b1];
				tmpArr[1] = plate[(a2 + 4) % 5][b2];
			} else 					// if both rows and columns are different
			{
				tmpArr[0] = plate[a2][b1];
				tmpArr[1] = plate[a1][b2];
			}

			decrytext.add(tmpArr);	// Put the decoded value.

		}

		for (int i = 0; i < decrytext.size(); i++) // Remove the x in between the duplicate characters.
		{
			if (i != decrytext.size() - 1 && decrytext.get(i)[1] == 'x'
					&& decrytext.get(i)[0] == decrytext.get(i + 1)[0]) {
				detext += decrytext.get(i)[0];
			} else {
				detext += decrytext.get(i)[0] + "" + decrytext.get(i)[1];
			}
		}

		for (int i = 0; i < z.length(); i++)// Find the z position and return it to q
		{
			if (z.charAt(i) == '1')
				detext = detext.substring(0, i) + 'z' + detext.substring(i + 1, detext.length());

		}

		if (flag) // Remove the x inserted when the string is odd.
			detext = detext.substring(0, detext.length() - 1);

		return detext;
	}

	public static void plate(String key) {
		String platekey = ""; // Save the duplicated string

		key += "abcdefghijklmnopqrstuvwxyz"; // Add alphabet to make password plate
		
		// The last letter does not appear on the cipher board -> Change z to q to make 25 letters.
		key = key.replace('z', 'q');
		System.out.println(key);

		// remove duplicate key
		for(int i = 0;i<key.length();i++) {
			if(!platekey.contains(String.valueOf(key.charAt(i)))) {	//Check if the string contains the character
				platekey += String.valueOf(key.charAt(i)); // if not, add to platekey
			}
		}
		 
		System.out.println(platekey);
		
		// Initialize the key that has been removed from the password plate
		for (int i = 0, k = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				plate[i][j] = platekey.charAt(k);
				k++;
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(plate[i][j] + " ");
			}
			System.out.println("");
		}
		row=platekey;
			
	}

}