
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Index {
	
	static Integer [] vocabArray = new Integer[114522];
	public static void main(String[] args) throws IOException {

		int j, noOfAuthors = 10;
		int chk=0;
		Map<String , Integer> vocabMap = new HashMap(); 
		
		initialiseVocabMap(vocabMap);
		
		for (j = 0; j <= noOfAuthors; j++) 
		{
			File folder = new File("C:\\Users\\Shashank\\Documents\\BE project\\Waste\\The Final Project\\Final\\traindata\\"+j);
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
					String usedFeatures = "";
					int noOfWords = 0;
					buffer = "";
					
					while (fileScanner.hasNextLine()) 
					{
						String nextLine = fileScanner.nextLine();
						System.out.println(nextLine);
						char ch = '“';
						//System.out.println(nextLine);
						String[] str = nextLine.split("\\.|;|,|[\\r\\n]+|[\\n]+|[\\r]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+'”' +" ");
						buffer = buffer + nextLine;
						if (noOfWords < 2000) 
						{
							noOfWords = noOfWords + str.length;
							
						} 
						else 
						{
							
							String [] wordsInPara = buffer.split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+"”" + " ");
							
							int numTokens = getNumberOfTokens(wordsInPara,buffer, vocabMap);
							System.out.println(numTokens);
							
							initialiseVocabArray(wordsInPara, vocabMap,buffer,vocabArray);
							
							Map<String, Integer> out = FeatureExtract
									.TypeToken(buffer);
							int NoOfSentences = FeatureExtract
									.ExtractSentences(buffer);
							int i, TotalWords = 0,size;

							ArrayList<Integer> WordFreq = new ArrayList<Integer>(
									out.values());
							size = out.size();
							
							for (i = 0; i < WordFreq.size(); i++)
								TotalWords += WordFreq.get(i);
								
							
							float typeToken = (float)size/TotalWords;

							float meanSentenceLength = TotalWords/(float)NoOfSentences;
							
							int noOfCommas = FeatureExtract.numberOfCommas(buffer);
							
							float meanWordLength = FeatureExtract.meanWordLength(out);
							
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
							int noOfCapitalLetters = FeatureExtract.numberOfCapitalLetters(buffer);
							int noOfSmallLetters = FeatureExtract.numberOfSmallLetters(buffer);
							
							chk = chk + noOfMights;
							
							usedFeatures = "";
														
							usedFeatures = usedFeatures + typeToken + ",";
							usedFeatures = usedFeatures + meanSentenceLength + ",";
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
							usedFeatures = usedFeatures + noOfCapitalLetters + ",";
							usedFeatures = usedFeatures + noOfSmallLetters + ",";
							
							for(int k = 0; k < vocabArray.length; k++)
							{
								usedFeatures = usedFeatures + vocabArray[k]/numTokens + ",";
							}
							
							String filename = "text.txt";
							FileWriter fw = new FileWriter(filename, true);
							fw.write(usedFeatures + j);
							fw.write("\n");
							fw.close();

							buffer = "";
							noOfWords = 0;
						}
					}
					if(noOfWords > 800)
					{
						char ch = '“';
						String [] wordsInPara = buffer.split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+" ");
						System.out.println(Arrays.toString(wordsInPara));
						int numTokens = getNumberOfTokens(wordsInPara, buffer, vocabMap);
						System.out.println(numTokens);
						
						initialiseVocabArray(wordsInPara,vocabMap,buffer,vocabArray);
						
						Map<String, Integer> out = FeatureExtract
								.TypeToken(buffer);
						int NoOfSentences = FeatureExtract
								.ExtractSentences(buffer);
						int i, TotalWords = 0,size;

						ArrayList<Integer> WordFreq = new ArrayList<Integer>(
								out.values());
						size = out.size();
						
						for (i = 0; i < WordFreq.size(); i++)
							TotalWords += WordFreq.get(i);

						
						float typeToken = (float)size/TotalWords;
						float meanSentenceLength = TotalWords/(float)NoOfSentences;
						int noOfCommas = FeatureExtract.numberOfCommas(buffer);
						float meanWordLength = FeatureExtract.meanWordLength(out);
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
						
						//System.out.println(out);
						
						chk = chk + noOfThis;
						
						usedFeatures = "";
						
						usedFeatures = usedFeatures + typeToken + ",";
						usedFeatures = usedFeatures + meanSentenceLength + ",";
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
						
						
						for(int k = 0; k < vocabArray.length; k++)
						{
							float vocabRatio = (float)vocabArray[k]/numTokens;
							usedFeatures = usedFeatures + vocabRatio + ",";
						}
						
						String filename = "text.txt";
						FileWriter fw = new FileWriter(filename, true);
						fw.write(usedFeatures + j);
						fw.write("\n");
						fw.close();

						buffer = "";
						noOfWords = 0;

					}
					//System.out.println("Total no of very in file "+j+" = "+chk);
					chk = 0;
				}

			}
		}

	}

	private static int getNumberOfTokens(String[] wordsInPara, String buffer, Map<String, Integer> vocabMap) 
	{
		// TODO Auto-generated method stub
		int retVal = 0;
	
		//String[] str = buffer.toLowerCase().split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+'”'+' ');
		for (String keyword : wordsInPara)
		{
			

			if(vocabMap.containsKey(keyword))
			{
				retVal ++;
			}
		}

		return retVal;
	}

	private static void initialiseVocabArray(String[] wordsInPara, Map<String, Integer> vocabMap, String buffer, Integer[] vocabArray) 
	{
		
		for(int i = 0 ; i < vocabArray.length; i++)
		{
			vocabArray[i] = 0;
		}
		// TODO Auto-generated method stub
		char ch = '“';
		//String[] str = buffer.toLowerCase().split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+'”'+' ');
		for(String key : wordsInPara)
		{
			if(vocabMap.containsKey(key))
			{
				vocabArray[vocabMap.get(key)] = vocabArray[vocabMap.get(key)] + 1;
			}
		}
	}
	
	private static void initialiseVocabMap(Map<String, Integer> vocabMap) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		String vocabFilename = "C:\\Users\\Shashank\\Documents\\BE project\\Waste\\The Final Project\\Final\\vocab.txt";
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
}
