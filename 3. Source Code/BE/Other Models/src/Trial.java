
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trial 
{
	static int BLOCK_LENGTH_IN_CHARS = 150000; 
	static Map<String , Integer> vocabMap = null; 
	static FileWriter featureFile = null;
	public static void main(String[] args) throws IOException 
	{

		int j, noOfAuthors = 868;
		
		//intialise the vocab map from the file.To know what this map represent please refer the method doc.
		
		initialiseVocabMap();
		//initialiseVocabMapLowFre();
		//initialiseVocabMapHighFre();
		//initialiseVocabMapMidFre();
		
		//the feature vector file.cfrc
		//its going to be very big.
		
		FileWriter featureFileWriter = new FileWriter("C:\\Users\\Admin\\Desktop\\BE Proj\\allFrequencyfeatures.txt");
		
		for (j = 218; j <= noOfAuthors; j++) 
		{
			File folder = new File("C:\\Users\\Admin\\Desktop\\BE Proj\\traindata\\"+j);
			File file;
			String FILE_PATH = "";
			if (folder.isDirectory()) 
			{
				
				for (final File fileEntry : folder.listFiles()) 
				{
					
					FILE_PATH = fileEntry.getAbsolutePath();
					
					System.out.println(FILE_PATH);
					
					file = new File(FILE_PATH);
					
					
					String usedFeatures = "";
					
					
					BufferedReader reader = new BufferedReader(new FileReader(file));
					
					String nextBlock = getNextBlock(reader);
					int datasetCount = 0;
					
					
					while(nextBlock.length() >= BLOCK_LENGTH_IN_CHARS)
					{
						usedFeatures = getAllFeatures(nextBlock);
						
						featureFileWriter.write(usedFeatures + j);
						featureFileWriter.write('\n');
						
						System.out.println("written feature for the dataset "+datasetCount);
						datasetCount++;
						
						nextBlock = getNextBlock(reader);
						
					}
					
					reader.close();
					
				}
				
			}

			if(j == 433)
			{
				j = 652;
			}
		}
		featureFileWriter.close();

	}

	/*
	 * getAllFeatures : method to calculate all the features of the text. It takes as the input block. Here block means the next sequence of 
	 * BLOCK_LENGTH_IN_CHARS chars in the novel.
	 * This method make use of many FeatureExtraction methods to find out the features. It then concatinate all of them to return a final 
	 * feature string. This feature string is separated by comma. 
	 */
	private static String getAllFeatures(String block) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		char ch = '“';
		block = block.toLowerCase();
		String buffer = block;
		
		//split the block into words.
		//Note : that this split leads to many useless words. For example split also returns 's', '0','9' and other numbers as words.
		//Hence we need to clean it.
		//thats what we do next.
		String [] words = block.split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+"”" + " ");
		
		//clean remove all the words whose size if 2 or less. It also remove all the words which contains any char other then alphabet.
		//Note : This method does not remove the duplicates.
		words = cleanWords(words);
		
		//apply stemming
		//Note : This method does not remove the duplicates.
		words = stemming(words);
	
		//For many features we require frequency map. That is map of words and how many times they occur in the block. 
		Map<String, Integer> out = getMap(words);
		
		
		//extracting the features.
		int noOfCommas = FeatureExtract.numberOfCommas(block);
		float meanWordLength = FeatureExtract.meanWordLength(words);
		int noOfSemicolon = FeatureExtract.numberOfSemicolons(buffer);
		int noOfQuotation = FeatureExtract.numberOfQuotations(buffer);
		int noOfExclamation = FeatureExtract.numberOfExclamations(buffer) ;
		int noOfHyphens = FeatureExtract.numberOfHyphen(buffer) ;
		
		
		
		int noOfVerys = FeatureExtract.numberOfVerys(out) ;
		
		int noOfifs = FeatureExtract.numberOfIfs(out);
		int noOfMores = FeatureExtract.numberOfMores(out);
		int noOfThis = FeatureExtract.numberOfThiss(out);
		int noOfButs = FeatureExtract.numberOfbuts(out);
		int noOfThats = FeatureExtract.numberOfThats(out);
		int noOfMights = FeatureExtract.numberOfMights(out);
		int noOfMusts = FeatureExtract.numberOfMusts(out);
		int noOfHowevers = FeatureExtract.numberOfHowevers(out);
		int noOfAnds = FeatureExtract.numberOfAnds(out);
		
		float typeToken = FeatureExtract.TypeToken(out);
		
		
		//concatinate all the features.
		String usedFeatures = "";
		
		usedFeatures = usedFeatures + typeToken + ",";
		usedFeatures = usedFeatures + noOfCommas + ",";
		usedFeatures = usedFeatures + meanWordLength + ",";
		usedFeatures = usedFeatures + noOfSemicolon + ",";
		usedFeatures = usedFeatures + noOfQuotation + ",";
		usedFeatures = usedFeatures + noOfExclamation + ",";
		usedFeatures = usedFeatures + noOfHyphens + ",";
		
		usedFeatures = usedFeatures + noOfVerys + ",";
		usedFeatures = usedFeatures + noOfifs + ",";
		usedFeatures = usedFeatures + noOfMores + ",";
		usedFeatures = usedFeatures + noOfThis + ",";
		usedFeatures = usedFeatures + noOfButs + ",";
		usedFeatures = usedFeatures + noOfThats + ",";
		usedFeatures = usedFeatures + noOfMights + ",";
		usedFeatures = usedFeatures + noOfMusts + ",";
		usedFeatures = usedFeatures + noOfHowevers + ",";
		usedFeatures = usedFeatures + noOfAnds + ",";
		
		
		//call getVocabFeature to get the vocab features.
		//This feature is essentially a feature vector of size of the vocab list in all the articles take together.
		//The value at each index the array stores the number of times the vocab at that index has occurred in the current block.This is 0 if the block does 
		//not contains the vocab word at that index.
		usedFeatures = usedFeatures +  getVocabFeatures(out); 
		
		return usedFeatures;
	}

	/*
	 * getVocabFeatures : This method inputs the frequency word map.This map has the key of the type string and value of the type integer.
	 * The integer value represents how many times the word has occurred in the block.
	 * It makes use of vocabMap. vocabMap is a map whose key is string and value is integer. The key represents all the vocab in the all the articles
	 * taken togther. The value represents the index of the vocab in the vocabArray. This vocabArray is a frequency array that stores the frequency of 
	 * the vocab in the block.vocabArray will be very huge for each data set.
	 */
	private static String getVocabFeatures(Map<String, Integer> freqWordMap) throws FileNotFoundException
	{
		String retVal = "";
		
		//intially all the values in this array is 0.
		int [] vocabArray = new int[vocabMap.size()];
		
		
		for(String key : freqWordMap.keySet())
		{
			if(vocabMap.containsKey(key))
			{
				//update the vocabArray 
				vocabArray[vocabMap.get(key)] = freqWordMap.get(key);
			}
		}
		for(int i = 0 ; i < vocabArray.length; i++)
		{
			//concatinate the vocab array.
			retVal = retVal  + vocabArray[i] + ","; 
		}
		
		
		return retVal;
	}

	/*
	 * getMap : This method is used to convert the words into frequency map.The input is an array of the type string. This array just contains the 
	 * words. That is the words are not unique. They may have repitition in the array.
	 * But the retured map contains the frequency of the words.
	 */
	private static Map<String, Integer> getMap(String[] words) 
	{
		
		HashMap<String, Integer> map = new HashMap<String, Integer>(); 
		for(String key : words)
		{
			if(map.containsKey(key))
			{
				map.put(key, map.get(key) + 1);
			}
			else
			{
				map.put(key, 1);
			}
		}
		return map;
	}

	/*
	 * method to stem the words. Input the is the array of strings. the porter code is called on each word.
	 */
	private static String[] stemming(String[] words) 
	{
		Porter stemmer = new Porter();
		// TODO Auto-generated method stub
		for(int i = 0 ; i < words.length; i++ )
		{
			words[i] = stemmer.stripAffixes(words[i]);
		}
		return words;
		
	}

	/* 
	 * 
	 * cleanWords : This method inputs an array of word. These words contains useless characters such as digits and single alphabet letters.
	 * So we remove any word that contains any chars other then alphabet. Also we remove any word whose length is 1 or less.
	 * Except we keep the word 'a'.  
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




	private static String getNextBlock(BufferedReader reader) throws IOException 
	{
		String retVal = "";
		
		int c;
		int count = 0;
		char []chars = new char[BLOCK_LENGTH_IN_CHARS];
		c = reader.read(chars);
		for(int i = 0 ; i < c ; i++)
		{
			retVal = retVal + chars[i];
		}
		
		
		return retVal;
	}
		/*
		 * initialiseVocabMap : This method is used for reading all the list of vocab generated by KeyWords.java file.
		 * This file is named as vocab.txt which contains list of all the keywords or vocabs.
		 * The vocabMap map is of the a map of String to integer map.
		 * Where the string represents the vocab and the integer represens the index of the vocab.
		 * This index is then used to produce the feature vector of the data set.
		 */
	private static void initialiseVocabMap() throws FileNotFoundException 
	{
		
		vocabMap = new HashMap<String, Integer>();
		String vocabFilename = "C:\\Users\\Admin\\Desktop\\BE Proj\\vocab.txt";
		int i = 0;
		Scanner fileScanner = new Scanner(new File(vocabFilename));
		
		while(fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			String vocab = line.split(" ")[0];
			vocabMap.put(vocab, i);
			i++;
		}
	}
	
		/*
		 * initialiseVocabMap : This method is used for reading all the list of vocab generated by KeyWords.java file.
		 * This file is named as vocab.txt which contains list of all the keywords or vocabs.
		 * The vocabMap map is of the a map of String to integer map.
		 * Where the string represents the vocab and the integer represens the index of the vocab.
		 * This index is then used to produce the feature vector of the data set.
		 */
	private static void initialiseVocabMapHighFre() throws FileNotFoundException 
	{
		if (vocabMap == null)
			vocabMap = new HashMap<String, Integer>();
		
		String vocabFilename = "C:\\Users\\Admin\\Desktop\\BE Proj\\vocab.txt";
		int i = 0;
		Scanner fileScanner = new Scanner(new File(vocabFilename));
		
		while(fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			String [] strArray = line.split(" ");
			String vocab = strArray[0];
			int fre = Integer.valueOf(strArray[1]);
			
			//for high frequency words take min as 5000
			if(fre > 1000)
			{
				vocabMap.put(vocab, i);
				i++;
			}
		}
		fileScanner.close();
	}
	private static void initialiseVocabMapLowFre() throws FileNotFoundException 
	{
		if(vocabMap == null)
			vocabMap = new HashMap<String, Integer>();
		
		String vocabFilename = "C:\\Users\\Admin\\Desktop\\BE Proj\\vocab.txt";
		int i = 0;
		Scanner fileScanner = new Scanner(new File(vocabFilename));
		
		while(fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			String [] strArray = line.split(" ");
			String vocab = strArray[0];
			int fre = Integer.valueOf(strArray[1]);
			
			//for high frequency words take min as 5000
			if(fre < 500)
			{
				vocabMap.put(vocab, i);
				i++;
			}
		}
		fileScanner.close();
	}

	private static void initialiseVocabMapMidFre() throws FileNotFoundException 
	{
		if(vocabMap == null)
			vocabMap = new HashMap<String, Integer>();
		
		String vocabFilename = "C:\\Users\\Admin\\Desktop\\BE Proj\\vocab.txt";
		int i = 0;
		Scanner fileScanner = new Scanner(new File(vocabFilename));
		
		while(fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			String [] strArray = line.split(" ");
			String vocab = strArray[0];
			int fre = Integer.valueOf(strArray[1]);
			
			//for high frequency words take min as 5000
			if(fre > 500 && fre < 1000)
			{
				vocabMap.put(vocab, i);
				i++;
			}
		}
		fileScanner.close();
	}

}
