
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyWords 
{
	public static void main(String[] args) throws IOException {

		int j, noOfAuthors = 868;
		int chk=0;
		Map<String , Integer> m = new HashMap();
		for (j = 218; j <= noOfAuthors; j++) 
		{
			File folder = new File("C:\\Users\\Admin\\Desktop\\BE Proj\\traindata\\"+j);
			File file;
			String FILE_PATH = "", buffer;
			if (folder.isDirectory()) 
			{

				for (final File fileEntry : folder.listFiles()) 
				{
					FILE_PATH = fileEntry.getAbsolutePath();
					System.out.println(FILE_PATH);
					file = new File(FILE_PATH);
					Scanner fileScanner = new Scanner(file);
					//String usedFeatures = "";
					//int noOfWords = 0;
					buffer = "";
					while (fileScanner.hasNextLine()) 
					{
						String nextLine = fileScanner.nextLine().toLowerCase();
						char ch = '“';
						//System.out.println(nextLine);
						String[] str = nextLine.split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+'”'+' ');
						Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
						Porter stemmer = new Porter();
						for (String keyword : str)
						{
							if(keyword.length() > 0)
							{
								keyword = stemmer.stripAffixes(keyword);
								Matcher mat = p.matcher(keyword);
								boolean b = mat.find();

								if(b == false && keyword.length() >= 2)
								{
									if(m.containsKey(keyword))
									{
										
										m.put(keyword,m.get(keyword) + 1);
									}
									else
									{
										m.put(keyword, 1);
								
									}
								}

							}
						}
					}
				}
			}
			if(j == 433)
			{
				j = 652;
			}
		}
	//write to the file
		PrintWriter writer = new PrintWriter("C:\\Users\\Shashank\\Documents\\BE project\\Waste\\The Final Project\\Final\\vocab.txt");
		
		
		Pair [] pairs = new Pair[m.size()];
		int k = 0;
		for(String key : m.keySet())
		{
			pairs[k] = new Pair();
			pairs[k].fre = m.get(key);
			pairs[k].key = key;
			k++;
		}
		
		Arrays.sort(pairs);
		
		for(k = 0 ; k < pairs.length; k++)
		{
			if(pairs[k].fre >= 10)
			{
				writer.print(pairs[k].key + " " + pairs[k].fre);
				writer.print("\n");
			}
			
		}
		writer.close();
	}
	
	public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) 
	{
		   List mapKeys = new ArrayList(passedMap.keySet());
		   List mapValues = new ArrayList(passedMap.values());
		   Collections.sort(mapValues);
		   Collections.sort(mapKeys);

		   LinkedHashMap sortedMap = new LinkedHashMap();

		   Iterator valueIt = mapValues.iterator();
		   while (valueIt.hasNext()) {
		       Object val = valueIt.next();
		       Iterator keyIt = mapKeys.iterator();

		       while (keyIt.hasNext()) {
		           Object key = keyIt.next();
		           String comp1 = passedMap.get(key).toString();
		           String comp2 = val.toString();

		           if (comp1.equals(comp2)){
		               passedMap.remove(key);
		               mapKeys.remove(key);
		               sortedMap.put((String)key, (Integer)val);
		               break;
		           }

		       }

		   }
		   return sortedMap;
		}
	
}


