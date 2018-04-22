
public class Pair implements Comparable<Pair>
{
	public String key;
	public int fre;
	@Override
	public int compareTo(Pair arg0) 
	{
		
		return ( (int)(this.fre - arg0.fre));
	}
}
