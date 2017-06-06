package support;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader 
{
	ArrayList<String> lines = new ArrayList();
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	DataInputStream dis = null;
	String filename;

	public FileReader(String filename) 
	{
		this.filename = filename;
		File file = new File(filename);
		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public String nextLine()
	{
		try {
			return dis.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasNextLine()
	{
		try {
			return (dis.available() != 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	}