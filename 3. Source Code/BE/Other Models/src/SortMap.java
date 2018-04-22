import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SortMap
{
    public static boolean ASC = true;
    public static boolean DESC = false;

    public static void main(String[] args) throws FileNotFoundException
    {
    	Map<String, Integer> unsortMap = new HashMap<String, Integer>();
    	File file = new File("C:\\Users\\Shashank\\Documents\\BE project\\Waste\\The Final Project\\Final\\vocab.txt");
    	Scanner in = new Scanner(file);
    	while(in.hasNextLine())
    	{
    		String nextLine = in.nextLine();
    		String[] str = nextLine.split(" ");
    		System.out.println(nextLine);
    		unsortMap.put(str[0],Integer.parseInt(str[1]));
    		
    	}
        // Creating dummy unsorted map
        //unsortMap.put("B", 55);
       // unsortMap.put("A", 80);
        //unsortMap.put("D", 20);
        //unsortMap.put("C", 70);

        System.out.println("Before sorting......");
        printMap(unsortMap);

        System.out.println("After sorting ascending order......");
        //Map<String, Integer> sortedMapAsc = sortByComparator(unsortMap, ASC);
        printMap(unsortMap);


    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<String, Integer> m) throws FileNotFoundException
    {
    	PrintWriter writer = new PrintWriter("C:\\Users\\Shashank\\Documents\\BE project\\Waste\\The Final Project\\Final\\Sortvocab.txt");
		
			for(String key : m.keySet())
			{
				if(m.get(key) >= 10  )
				{
					writer.write(key + " " + m.get(key) + "\n");
				}
			}
    }
}