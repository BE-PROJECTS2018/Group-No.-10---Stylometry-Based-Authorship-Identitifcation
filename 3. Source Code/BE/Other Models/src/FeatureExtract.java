import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class FeatureExtract {

	// static String FILE_PATH = "";
	private static Scanner input = null;

	public static Map<String, Integer> TypeToken(String buffer) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		char ch = '“';
		String[] str = buffer.split("\\.|;|,|[\\r\\n]+|\"|\\?|\\s|!|\'|:|" + ch+'|'+'”');// |\\,|\"|;|-|:|!|&|(|)|\\?");
		int i;
		for (i = 0; i < str.length; i++) {
			
			if (map.containsKey(str[i])) {
				int temp = map.get(str[i]) + 1;
				map.put(str[i], temp);
			} else {
				if (str[i].length() > 0)
					map.put(str[i], 1);
			}
		}

		return map;
	}

	public static int ExtractSentences(String dataSet) {
		int noOfSentences = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if ((dataSet.charAt(i) == '.' || dataSet.charAt(i) == '?' || dataSet
					.charAt(i) == '!')
					&& (i > 0 && dataSet.charAt(i - 1) != '.')) {
				noOfSentences = noOfSentences + 1;
			}
		}
		return noOfSentences;
	}
	
	public static int numberOfCommas(String dataSet) {
		int noOfCommas = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) == ',') {
				noOfCommas = noOfCommas + 1;
			}
		}
		return noOfCommas;
	}
	
	public static int numberOfVerys(Map<String,Integer> mp) {
		int noOfVerys = 0;
		
		//System.out.println("Map is "+mp);
		if(mp.containsKey("very"))
		{
			//System.out.println("very 1 ");
			noOfVerys = mp.get("very");
		}
		if(mp.containsKey("Very"))
		{
			//System.out.println("Very 2 ");
			noOfVerys = noOfVerys + mp.get("Very");
		}
		if(mp.containsKey("VERY"))
		{
			//System.out.println("VERY 3 ");
			noOfVerys = noOfVerys + mp.get("VERY");
		}
		return noOfVerys;
	}
	
	public static int numberOfIfs(Map<String,Integer> mp) {
		int noOfIfs = 0;
		
		if(mp.containsKey("if"))
		{
			noOfIfs = mp.get("if");
		}
		if(mp.containsKey("If"))
		{
			noOfIfs = noOfIfs + mp.get("If");
		}
		if(mp.containsKey("IF"))
		{
			noOfIfs = noOfIfs + mp.get("IF");
		}
		return noOfIfs;
	}
	
	public static int numberOfMusts(Map<String,Integer> mp) {
		int noOfMusts = 0;
		
		if(mp.containsKey("must"))
		{
			noOfMusts = mp.get("must");
		}
		if(mp.containsKey("Must"))
		{
			noOfMusts = noOfMusts + mp.get("Must");
		}
		if(mp.containsKey("MUST"))
		{
			noOfMusts = noOfMusts + mp.get("MUST");
		}
		return noOfMusts;
	}
	
	public static int numberOfMores(Map<String,Integer> mp) {
		int noOfMores = 0;
		
		if(mp.containsKey("more"))
		{
			noOfMores = mp.get("more");
		}
		if(mp.containsKey("More"))
		{
			noOfMores = noOfMores + mp.get("More");
		}
		if(mp.containsKey("MORE"))
		{
			noOfMores = noOfMores + mp.get("MORE");
		}
		return noOfMores;
	}
	
	public static int numberOfMights(Map<String,Integer> mp) {
		int noOfMights = 0;
		
		if(mp.containsKey("might"))
		{
			noOfMights = mp.get("might");
		}
		if(mp.containsKey("Might"))
		{
			noOfMights = noOfMights + mp.get("Might");
		}
		if(mp.containsKey("MIGHT"))
		{
			noOfMights = noOfMights + mp.get("MIGHT");
		}
		return noOfMights;
	}
	
	public static int numberOfThiss(Map<String,Integer> mp) {
		int noOfThis = 0;
		
		if(mp.containsKey("this"))
		{
			noOfThis = mp.get("this");
		}
		if(mp.containsKey("This"))
		{
			noOfThis = noOfThis + mp.get("This");
		}
		if(mp.containsKey("THIS"))
		{
			noOfThis = noOfThis + mp.get("THIS");
		}
		return noOfThis;
	}
	
	public static int numberOfbuts(Map<String,Integer> mp) {
		int noOfbuts = 0;
		
		if(mp.containsKey("but"))
		{
			noOfbuts = mp.get("but");
		}
		if(mp.containsKey("But"))
		{
			noOfbuts = noOfbuts + mp.get("But");
		}
		if(mp.containsKey("BUT"))
		{
			noOfbuts = noOfbuts + mp.get("BUT");
		}
		return noOfbuts;
	}
	
	public static int numberOfHowevers(Map<String,Integer> mp) {
		int noOfHowevers = 0;
		
		if(mp.containsKey("however"))
		{
			noOfHowevers = mp.get("however");
		}
		if(mp.containsKey("However"))
		{
			noOfHowevers = noOfHowevers + mp.get("However");
		}
		if(mp.containsKey("HOWEVER"))
		{
			noOfHowevers = noOfHowevers + mp.get("HOWEVER");
		}
		return noOfHowevers;
	}
	
	public static int numberOfAnds(Map<String,Integer> mp) {
		int noOfAnds = 0;
		
		if(mp.containsKey("and"))
		{
			noOfAnds = mp.get("and");
		}
		if(mp.containsKey("And"))
		{
			noOfAnds = noOfAnds + mp.get("And");
		}
		if(mp.containsKey("AND"))
		{
			noOfAnds = noOfAnds + mp.get("AND");
		}
		return noOfAnds;
	}
	
	public static int numberOfThats(Map<String,Integer> mp) {
		int noOfThats = 0;
		
		if(mp.containsKey("that"))
		{
			noOfThats = mp.get("that");
		}
		if(mp.containsKey("That"))
		{
			noOfThats = noOfThats + mp.get("That");
		}
		if(mp.containsKey("THAT"))
		{
			noOfThats = noOfThats + mp.get("THAT");
		}
		return noOfThats;
	}
	
	public static int numberOfSemicolons(String dataSet) {
		int noOfSemicolons = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) == ';') {
				noOfSemicolons = noOfSemicolons + 1;
			}
		}
		return noOfSemicolons;
	}
	
	public static int numberOfHyphen(String dataSet) {
		int noOfHyphens = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) == '-') {
				noOfHyphens = noOfHyphens + 1;
			}
		}
		return noOfHyphens;
	}
	
	public static int numberOfQuotations(String dataSet) {
		int noOfQuotation1 = 0,noOfQuotation2 = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) == '“') {
				noOfQuotation1 = noOfQuotation1 + 1;
			}
			else if(dataSet.charAt(i) == '"')
			{
				noOfQuotation2 = noOfQuotation2 + 1;
			}
		}
		return (noOfQuotation1 + (noOfQuotation2/2));
	}
	
	public static int numberOfExclamations(String dataSet) {
		int noOfExclamation = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) == '!') {
				noOfExclamation = noOfExclamation + 1;
			}
		}
		return noOfExclamation;
	}
	
	public static float meanWordLength(String[] words)
	{
		float mean;
		int noOfWords = 0,totalWL = 0;
		
		int i = 0;
		while (i < words.length) 
	    {
	        noOfWords ++;
	        totalWL = totalWL + words[i].length();
	        i++;
	    }
		mean = (float)totalWL/noOfWords;
		
		return mean;
	}
	public static int numberOfCapitalLetters(String dataSet) {
		int noOfCap = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (Character.isUpperCase(dataSet.charAt(i))) {
				noOfCap = noOfCap + 1;
			}
		}
		return noOfCap;
	}
	public static int numberOfSmallLetters(String dataSet) {
		int noOfSmall = 0;
		for (int i = 0; i < dataSet.length(); i++) {
			if (Character.isLowerCase(dataSet.charAt(i))) {
				noOfSmall = noOfSmall + 1;
			}
		}
		return noOfSmall;
	}

	public static float TypeToken(Map<String, Integer> wordsCountMap) 
	{
		int totalWords = 0;
		
		Iterator <Entry<String, Integer> >  it = wordsCountMap.entrySet().iterator();
		
		while(it.hasNext())
		{
			totalWords = totalWords + it.next().getValue();
		}
		
		return  (float)wordsCountMap.size()/totalWords;
	}
}
