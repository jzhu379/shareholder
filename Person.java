package shareholder;

public class Person implements Comparable<Person>
{
	private String last;
	private String first;
	
	public Person()
	{
	}
	
	public void setLast(String a)
	{
		last = a;
	}
	
	public void setFirst(String a)
	{
		first = a;
	}
	
	public String getLast()
	{
		return last;
	}
	
	public String getFirst()
	{
		return first;
	}
	
	public int compareTo(Person a)
	{
		return (last + first).compareToIgnoreCase(a.getLast() + a.getFirst());
	}
	
	@Override
	public String toString()
	{
		return last + ", " + first;
	}
}