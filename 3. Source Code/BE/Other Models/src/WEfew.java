import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WEfew {

	/**
	 * @param args
	 */
	
	private static String[] cleanWords(String[] words) 
	{
		int count = 0;
		//patter detects any  thing not a alphabet.
		Pattern p = Pattern.compile("[^a-zA-Z]", Pattern.CASE_INSENSITIVE);
		
		//keep on appending the valid words into the list.
		//This list will be usefull in generating the string array.
		List<String> list = new ArrayList<String>();
		
		for(int i = 0 ; i < words.length; i++)
		{
			Matcher mat = p.matcher(words[i]);
			boolean b = mat.find();
			if(!b)
			{
				//we neglect all the words of length 1 except 'a'.
				if(words[i].length() == 1 && words[i].equals("a"))
				{
					list.add(words[i]);
				}
				else if(words[i].length() > 1)
				{
					list.add(words[i]);
				}
			}
		}
		
		String[] retVal = new String[list.size()];
		Iterator<String> it = list.iterator();
		
		int i = 0;
		while(it.hasNext())
		{
			retVal [i] = it.next();
			i++;
			
		}
		
		
		return retVal;
	}
	public static void main(String[] args) throws IOException 
	{
		int numNumericFeatures = 100;
		//@ATTRIBUTE   typetoken NUMERIC
		FileWriter writer = new FileWriter(new File("authorName.txt"));
		for(int i = 0 ; i < numNumericFeatures; i++)
		{
			System.out.println("@ATTRIBUTE   "+i+" NUMERIC");
			writer.write("@ATTRIBUTE   "+i+" NUMERIC");
			writer.write('\n');
		}
		writer.close();
	}

}
